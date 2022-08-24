package com.til.dusk.common.world;


import com.til.dusk.Dusk;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.ParticleRegister;
import com.til.dusk.common.register.key.EventKey;
import com.til.dusk.common.register.key.KeyRegister;
import com.til.dusk.common.register.tag_tool.TagTool;
import com.til.dusk.util.Lang;
import com.til.dusk.util.Pos;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItem {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Dusk.MOD_ID);

    public static final RegistryObject<ItemBasics> BIND_STAFF = ITEMS.register("bind_staff", () -> new ItemBasics(new Item.Properties().stacksTo(1)) {
        @Override
        public @NotNull Component getName(@NotNull ItemStack stack) {
            CompoundTag compoundTag = stack.getTag();
            if (compoundTag != null) {
                BindType bindType = TagTool.bindTypeTag.get(compoundTag);
                if (bindType != null) {
                    return Component.translatable("%s[%s]", Component.translatable(this.getDescriptionId(stack)), Lang.getLang(bindType));
                }
            }
            return super.getName(stack);
        }

        @Override
        public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand interactionHand) {
            return super.use(level, player, interactionHand);
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
            BlockPos controlBlockPos = TagTool.blockPosTag.get(compoundTag);
            if (controlBlockPos.equals(blockPos)) {
                player.sendSystemMessage(Lang.getLang(Lang.getKey("错误，目标方块和要绑定的方块是同一方块")));
                return InteractionResult.SUCCESS;
            }
            BlockEntity controlBlockEntity = level.getBlockEntity(controlBlockPos);
            if (controlBlockEntity != null) {
                LazyOptional<IControl> iControlLazyOptional = controlBlockEntity.getCapability(CapabilityRegister.iControl.capability);
                if (iControlLazyOptional.isPresent()) {
                    IControl iControl = iControlLazyOptional.orElse(null);
                    BindType bindType = TagTool.bindTypeTag.get(compoundTag);
                    if (bindType == null) {
                        bindType = BindType.itemIn;
                    }
                    if (iControl.hasBind(blockEntity, bindType)) {
                        player.sendSystemMessage(iControl.unBind(blockEntity, bindType));
                    } else {
                        player.sendSystemMessage(iControl.bind(blockEntity, bindType));
                    }
                    return InteractionResult.SUCCESS;
                }
            }
            LazyOptional<IControl> iControlLazyOptional = blockEntity.getCapability(CapabilityRegister.iControl.capability);
            if (iControlLazyOptional.isPresent()) {
                IControl iControl = iControlLazyOptional.orElse(null);
                TagTool.blockPosTag.set(compoundTag, blockPos);
                List<BindType> bindTypeList = iControl.getCanBindType();
                if (!bindTypeList.isEmpty()) {
                    TagTool.bindTypeTag.set(compoundTag, bindTypeList.get(0));
                } else {
                    TagTool.bindTypeTag.set(compoundTag, BindType.itemIn);
                }
                TagTool.colorTag.set(compoundTag, new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)).getRGB());
                player.sendSystemMessage(Lang.getLang(Lang.getKey("已将目标方块设定为主绑定对象")));
            } else {
                player.sendSystemMessage(Lang.getLang(Lang.getKey("错误，目标方块没有控制器，无法设置为主绑定对象")));
            }
            return InteractionResult.SUCCESS;
        }

        @SubscribeEvent
        public void switchBindType(EventKey event) {
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
                    TagTool.colorTag.set(compoundTag, new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)).getRGB());
                } else {
                    BlockPos controlBlockPos = TagTool.blockPosTag.get(compoundTag);
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
                    BindType nowBindType = TagTool.bindTypeTag.get(compoundTag);
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
                    TagTool.bindTypeTag.set(compoundTag, bindType);
                    TagTool.colorTag.set(compoundTag, new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)).getRGB());
                    serverPlayer.sendSystemMessage(Component.translatable(Lang.getKey("已经绑定类型切换至[%s]"), Lang.getLang(bindType)));
                }
            });
        }

        @SubscribeEvent
        public void showBindState(EventKey event) {
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
                BlockPos controlBlockPos = TagTool.blockPosTag.get(compoundTag);
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
                Pos sPos = new Pos(controlBlockPos);
                ParticleRegister.block.add(serverPlayer, sPos, new Pos(), ColorPrefab.CONTROL_TAG, 10);
                for (Map.Entry<BindType, List<BlockPos>> bindTypeListEntry : iControl.getAllBind().entrySet()) {
                    for (BlockPos blockPos : bindTypeListEntry.getValue()) {
                        ParticleRegister.line.add(serverPlayer, sPos, new Pos(blockPos), bindTypeListEntry.getKey().color, 10);
                        ParticleRegister.block.add(serverPlayer, new Pos(blockPos), new Pos(), bindTypeListEntry.getKey().color, 10);
                    }
                }
            });
        }
    });

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ITEMS.register(Dusk.instance.modEventBus);
    }

    public static class ItemBasics extends Item {
        final Random random = new Random();

        public ItemBasics(Properties properties) {
            super(properties.tab(Dusk.TAB));
            MinecraftForge.EVENT_BUS.register(this);
        }
    }


}
