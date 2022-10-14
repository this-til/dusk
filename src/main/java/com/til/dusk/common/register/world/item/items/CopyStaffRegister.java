package com.til.dusk.common.register.world.item.items;

import com.google.gson.annotations.Expose;
import com.til.dusk.Dusk;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.register.key.EventKey;
import com.til.dusk.common.register.key.KeyRegister;
import com.til.dusk.common.register.other.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.particle_register.ParticleRegister;
import com.til.dusk.common.register.world.item.ItemPackRegister;
import com.til.dusk.common.register.world.item.StaffItemRegister;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.common.world.item.ItemBasics;
import com.til.dusk.util.Lang;
import com.til.dusk.util.Pos;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.prefab.ColorPrefab;
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

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

/**
 * @author til
 */
public class CopyStaffRegister extends StaffItemRegister {

    public CopyStaffRegister() {
        super("copy_staff");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "复制法杖");
        lang.add(LangType.EN_CH, "Copy Staff");
    }

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        recipeConsumer.accept(ShapedRecipeBuilder.shaped(pack.item())
                .define('A', diamondMakeInstructions.pack.itemTag())
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
        return new CopyStaffItem(new Item.Properties().stacksTo(1).tab(Dusk.TAB)){
            @Override
            public @NotNull Component getName(@NotNull ItemStack stack) {
                return Component.translatable(name.toLanguageKey());
            }
        };
    }

    /***
     * 复制法杖
     * @author til
     */
    public static class CopyStaffItem  extends Item  {

        public final Random random = new Random();

        public CopyStaffItem(Properties properties) {
            super(properties);
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
                    if (blockEntity == null || !blockEntity.getCapability(CapabilityRegister.iControl.capability).isPresent()) {
                        serverPlayer.sendSystemMessage(Component.translatable(Lang.getKey("错误，原绑定控制器丢失")));
                    }
                    Pos sPos = new Pos(controlBlockPos);
                    ParticleRegister.block.add(serverPlayer, ColorPrefab.CONTROL_TAG, 10, null, sPos);
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
            LazyOptional<IControl> iControlLazyOptional = blockEntity.getCapability(CapabilityRegister.iControl.capability);
            if (!iControlLazyOptional.isPresent()) {
                player.sendSystemMessage(Lang.getLang(Lang.getKey("错误，目标方块没有控制器")));
                return InteractionResult.SUCCESS;
            }
            ItemStack itemStack = useOnContext.getItemInHand();
            CompoundTag compoundTag = itemStack.getTag();
            if (compoundTag == null) {
                compoundTag = new CompoundTag();
                itemStack.setTag(compoundTag);
            }
            if (useOnContext.getPlayer().isShiftKeyDown()) {
                AllNBTPack.BLOCK_POS.set(compoundTag, blockPos);
                AllNBTPack.COLOR.set(compoundTag, new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()).getRGB());
            } else {
                BlockPos controlBlockPos = AllNBTPack.BLOCK_POS.get(compoundTag);
                if (controlBlockPos.equals(blockPos)) {
                    player.sendSystemMessage(Lang.getLang(Lang.getKey("错误，目标方块和要复制的方块是同一方块")));
                    return InteractionResult.SUCCESS;
                }
                BlockEntity controlBlockEntity = level.getBlockEntity(controlBlockPos);
                if (controlBlockEntity != null) {
                    LazyOptional<IControl> originalLazyOptional = controlBlockEntity.getCapability(CapabilityRegister.iControl.capability);
                    if (originalLazyOptional.isPresent()) {
                        IControl original = originalLazyOptional.orElse(null);
                        IControl target = iControlLazyOptional.orElse(null);
                        List<BindType> bindTypeList = target.getCanBindType();
                        for (BindType bindType : original.getCanBindType()) {
                            if (!bindTypeList.contains(bindType)) {
                                continue;
                            }
                            for (IPosTrack entity : original.getAllTileEntity(bindType)) {
                                target.bind(entity, bindType);
                            }
                        }
                        return InteractionResult.SUCCESS;
                    }
                }
                itemStack.setTag(new CompoundTag());
                player.sendSystemMessage(Lang.getLang(Lang.getKey("错误，原绑定控制器丢失")));
            }
            return InteractionResult.SUCCESS;
        }

    }
}