package com.til.dusk.common.world;


import com.til.dusk.Dusk;
import com.til.dusk.common.capability.AllCapability;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.handle.Handle;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.MessageRegister;
import com.til.dusk.util.Lang;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItem {

    public static ItemBasics bindStaff;

    protected static boolean isRegisterSubsidiary;

    @SubscribeEvent
    public static void onEvent(RegisterEvent event) {
        if (isRegisterSubsidiary) {
            return;
        }
        isRegisterSubsidiary = true;
        bindStaff = new ItemBasics("bind_staff", new Item.Properties().stacksTo(1)) {

            final Random random = new Random();

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
                BlockPos controlBlockPos = new BlockPos(compoundTag.getInt("x"), compoundTag.getInt("y"), compoundTag.getInt("z"));
                BlockEntity controlBlockEntity = level.getBlockEntity(controlBlockPos);
                if (controlBlockEntity != null) {
                    LazyOptional<IControl> iControlLazyOptional = controlBlockEntity.getCapability(AllCapability.I_CONTROL);
                    if (iControlLazyOptional.isPresent()) {
                        IControl iControl = iControlLazyOptional.orElse(null);
                        BindType bindType = BindType.BIND_TYPE.get().getValue(new ResourceLocation(compoundTag.getString("type")));
                        if (bindType == null) {
                            bindType = BindType.itemIn;
                        }
                        if (iControl.hasBundling(blockEntity, bindType)) {
                            player.sendSystemMessage(iControl.binding(blockEntity, bindType));
                        } else {
                            player.sendSystemMessage(iControl.unBindling(blockEntity, bindType));
                        }
                        return InteractionResult.SUCCESS;
                    }
                }
                if (blockEntity.getCapability(AllCapability.I_CONTROL).isPresent()) {
                    compoundTag.putInt("x", blockPos.getX());
                    compoundTag.putInt("y", blockPos.getY());
                    compoundTag.putInt("z", blockPos.getZ());
                    compoundTag.putString("type", BindType.itemIn.name.toString());
                    compoundTag.putInt("color", new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)).getRGB());
                    player.sendSystemMessage(Lang.getLang(Lang.getKey("已将目标方块设定为主绑定对象")));
                } else {
                    player.sendSystemMessage(Lang.getLang(Lang.getKey("错误，目标方块没有控制器，无法设置为主绑定对象")));
                }
                return InteractionResult.SUCCESS;
            }
        };
    }

    public static class ItemBasics extends Item {
        public final ResourceLocation name;


        public ItemBasics(ResourceLocation name, Properties properties) {
            super(properties.tab(Dusk.TAB));
            this.name = name;
            ForgeRegistries.ITEMS.register(name, this);
        }

        public ItemBasics(String name, Properties properties) {
            this(new ResourceLocation(Dusk.MOD_ID, name), properties);
        }


    }


}
