package com.til.dusk.common.world;


import com.til.dusk.Dusk;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.tag_tool.TagTool;
import com.til.dusk.util.Lang;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;
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
                        if (iControl.hasBundling(blockEntity, bindType)) {
                            player.sendSystemMessage(iControl.unBindling(blockEntity, bindType));
                        } else {
                            player.sendSystemMessage(iControl.binding(blockEntity, bindType));
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
