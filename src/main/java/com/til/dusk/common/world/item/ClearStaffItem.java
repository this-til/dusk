package com.til.dusk.common.world.item;

import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.util.Lang;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.LazyOptional;

/**
 * @author til
 */
public class ClearStaffItem extends ItemBasics implements ModItem.IHasCustomColor {
    public ClearStaffItem() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
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
        LazyOptional<IControl> lazyOptional = blockEntity.getCapability(CapabilityRegister.iControl.capability);
        if (!lazyOptional.isPresent()) {
            player.sendSystemMessage(Lang.getLang(Lang.getKey("错误，目标方块没有控制器")));
            return InteractionResult.SUCCESS;
        }
        lazyOptional.orElse(null).unBundlingAll();
        player.sendSystemMessage(Lang.getLang(Lang.getKey("已经清除控制器数据")));
        return InteractionResult.SUCCESS;
    }
}
