package com.til.dusk.common.register.world.item.items;

import com.til.dusk.Dusk;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.register.key.EventKey;
import com.til.dusk.common.register.key.KeyRegister;
import com.til.dusk.common.register.bind_type.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.world.item.StaffItemRegister;
import com.til.dusk.util.Lang;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.core.BlockPos;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.EventPriority;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author til
 */
public class BindStaffItemRegister extends StaffItemRegister {

    public BindStaffItemRegister() {
        super("bind_staff");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "绑定法杖");
        lang.add(LangType.EN_CH, "Bind Staff");
    }

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        recipeConsumer.accept(ShapedRecipeBuilder.shaped(pack.item())
                .define('A', diamondMakeOperation.pack.itemTag())
                .define('B', Tags.Items.GEMS)
                .pattern("  A")
                .pattern(" B ")
                .pattern("B  ")
                .unlockedBy("has_ores", ModRecipeProvider.has(Tags.Items.ORES)));
    }

    @Override
    public void defaultConfig() {
        super.defaultConfig();
    }

    @Override
    protected Item createItem() {
        return new BindStaffItem(new Item.Properties().stacksTo(1).tab(Dusk.TAB)){
            @Override
            public @NotNull Component getName(@NotNull ItemStack stack) {
                CompoundTag compoundTag = stack.getTag();
                if (compoundTag != null) {
                    BindType bindType = AllNBTPack.BIND_TYPE.get(compoundTag);
                    if (bindType != null) {
                        return Component.translatable("%s[%s]", Component.translatable(name.toLanguageKey()), Lang.getLang(bindType));
                    }
                }
                return super.getName(stack);
            }
        };
    }

    /***
     * 绑定法杖
     * @author til
     */
    public static class BindStaffItem extends Item {
        public BindStaffItem(Properties properties) {
            super(properties);
            MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, (Consumer<EventKey>) event -> {
                if (!event.keyRegister.equals(KeyRegister.switchBindType)) {
                    return;
                }
                ServerPlayer serverPlayer = event.contextSupplier.get().getSender();
                if (serverPlayer == null) {
                    return;
                }
                event.contextSupplier.get().enqueueWork(() -> {
                    ItemStack itemStack = serverPlayer.getMainHandItem();
                    if (itemStack.isEmpty()) {
                        return;
                    }
                    if (!itemStack.getItem().equals(this)) {
                        return;
                    }
                    CompoundTag compoundTag = itemStack.getTag();
                    if (compoundTag == null) {
                        compoundTag = new CompoundTag();
                        itemStack.setTag(compoundTag);
                    }
                    if (serverPlayer.isShiftKeyDown()) {
                        serverPlayer.sendSystemMessage(Component.translatable(Lang.getKey("已经清除坐标数据")));
                        compoundTag = new CompoundTag();
                        itemStack.setTag(compoundTag);
                        AllNBTPack.COLOR.set(compoundTag, -1);
                    } else {
                        BlockPos controlBlockPos = AllNBTPack.BLOCK_POS.get(compoundTag);
                        BlockEntity blockEntity = serverPlayer.getLevel().getBlockEntity(controlBlockPos);
                        if (blockEntity == null) {
                            serverPlayer.sendSystemMessage(Component.translatable(Lang.getKey("绑定控制器丢失，请重新绑定")));
                            return;
                        }
                        LazyOptional<IControl> lazyOptional = blockEntity.getCapability(CapabilityRegister.iControl.capability);
                        if (!lazyOptional.isPresent()) {
                            serverPlayer.sendSystemMessage(Component.translatable(Lang.getKey("绑定控制器丢失，请重新绑定")));
                            return;
                        }
                        IControl iControl = lazyOptional.orElse(null);
                        List<BindType> bindTypeList = iControl.getCanBindType();
                        if (bindTypeList.isEmpty()) {
                            serverPlayer.sendSystemMessage(Component.translatable(Lang.getKey("绑定控制器不接受绑定类型")));
                            return;
                        }
                        BindType bindType = bindTypeList.get(0);
                        BindType nowBindType = AllNBTPack.BIND_TYPE.get(compoundTag);
                        boolean trigger = false;
                        for (BindType type : bindTypeList) {
                            if (type.equals(nowBindType)) {
                                trigger = true;
                                continue;
                            }
                            if (trigger) {
                                bindType = type;
                                break;
                            }
                        }
                        AllNBTPack.BIND_TYPE.set(compoundTag, bindType);
                        AllNBTPack.COLOR.set(compoundTag, bindType.color.getRGB());
                        serverPlayer.sendSystemMessage(Component.translatable(Lang.getKey("已经绑定类型切换至[%s]"), Lang.getLang(bindType)));
                    }
                });
            });
            MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, (Consumer<EventKey>) event -> {
                if (!event.keyRegister.equals(KeyRegister.showBindState)) {
                    return;
                }
                ServerPlayer serverPlayer = event.contextSupplier.get().getSender();
                if (serverPlayer == null) {
                    return;
                }
                event.contextSupplier.get().enqueueWork(() -> {
                    ItemStack itemStack = serverPlayer.getMainHandItem();
                    if (itemStack.isEmpty()) {
                        return;
                    }
                    if (!itemStack.getItem().equals(this)) {
                        return;
                    }
                    CompoundTag compoundTag = itemStack.getTag();
                    if (compoundTag == null) {
                        compoundTag = new CompoundTag();
                        itemStack.setTag(compoundTag);
                    }
                    BlockPos controlBlockPos = AllNBTPack.BLOCK_POS.get(compoundTag);
                    BlockEntity blockEntity = serverPlayer.getLevel().getBlockEntity(controlBlockPos);
                    if (blockEntity == null) {
                        serverPlayer.sendSystemMessage(Component.translatable(Lang.getKey("绑定控制器丢失，请重新绑定")));
                        return;
                    }
                    LazyOptional<IControl> lazyOptional = blockEntity.getCapability(CapabilityRegister.iControl.capability);
                    if (!lazyOptional.isPresent()) {
                        serverPlayer.sendSystemMessage(Component.translatable(Lang.getKey("绑定控制器丢失，请重新绑定")));
                        return;
                    }
                    ShowStaffRegister.ShowStaffItem.showControl(serverPlayer, lazyOptional.orElse(null));
                });
            });
        }

        @Override
        public @NotNull InteractionResult useOn(@NotNull UseOnContext useOnContext) {
            Level level = useOnContext.getLevel();
            if (level.isClientSide) {
                return InteractionResult.PASS;
            }
            Player player = useOnContext.getPlayer();
            if (player == null) {
                return InteractionResult.PASS;
            }
            if (!useOnContext.getHand().equals(InteractionHand.MAIN_HAND)) {
                return InteractionResult.PASS;
            }
            BlockPos blockPos = useOnContext.getClickedPos();
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity == null) {
                player.sendSystemMessage(Lang.getLang(Lang.getKey("错误，目标方块没有方块实体")));
                return InteractionResult.SUCCESS;
            }
            ItemStack itemStack = useOnContext.getItemInHand();
            CompoundTag compoundTag = itemStack.getTag();
            if (compoundTag == null) {
                compoundTag = new CompoundTag();
                itemStack.setTag(compoundTag);
            }
            BlockPos controlBlockPos = AllNBTPack.BLOCK_POS.get(compoundTag);
            if (controlBlockPos.equals(blockPos)) {
                player.sendSystemMessage(Lang.getLang(Lang.getKey("错误，目标方块和要绑定的方块是同一方块")));
                return InteractionResult.SUCCESS;
            }
            BlockEntity controlBlockEntity = level.getBlockEntity(controlBlockPos);
            if (controlBlockEntity != null) {
                LazyOptional<IControl> iControlLazyOptional = controlBlockEntity.getCapability(CapabilityRegister.iControl.capability);
                if (iControlLazyOptional.isPresent()) {
                    IControl iControl = iControlLazyOptional.orElse(null);
                    BindType bindType = AllNBTPack.BIND_TYPE.get(compoundTag);
                    if (bindType == null) {
                        bindType = BindType.itemIn;
                    }
                    IPosTrack posTrack = blockEntity.getCapability(CapabilityRegister.iPosTrack.capability).orElse(null);
                    if (iControl.hasBind(posTrack, bindType)) {
                        player.sendSystemMessage(iControl.unBind(posTrack, bindType));
                    } else {
                        player.sendSystemMessage(iControl.bind(posTrack, bindType));
                    }
                    return InteractionResult.SUCCESS;
                }
            }
            LazyOptional<IControl> iControlLazyOptional = blockEntity.getCapability(CapabilityRegister.iControl.capability);
            if (iControlLazyOptional.isPresent()) {
                IControl iControl = iControlLazyOptional.orElse(null);
                AllNBTPack.BLOCK_POS.set(compoundTag, blockPos);
                List<BindType> bindTypeList = iControl.getCanBindType();
                if (!bindTypeList.isEmpty()) {
                    AllNBTPack.BIND_TYPE.set(compoundTag, bindTypeList.get(0));
                    AllNBTPack.COLOR.set(compoundTag, bindTypeList.get(0).color.getRGB());
                } else {
                    AllNBTPack.BIND_TYPE.set(compoundTag, BindType.itemIn);
                    AllNBTPack.COLOR.set(compoundTag, -1);
                }
                player.sendSystemMessage(Lang.getLang(Lang.getKey("已将目标方块设定为主绑定对象")));
            } else {
                player.sendSystemMessage(Lang.getLang(Lang.getKey("错误，目标方块没有控制器，无法设置为主绑定对象")));
            }
            return InteractionResult.SUCCESS;
        }

    }
}
