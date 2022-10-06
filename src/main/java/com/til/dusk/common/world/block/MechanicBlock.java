package com.til.dusk.common.world.block;

import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.tile_entity.ITileEntityType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import javax.annotation.Nullable;

/**
 * @author til
 */
public abstract class MechanicBlock extends Block implements ITileEntityType, SimpleWaterloggedBlock {

    public final ManaLevel manaLevel;

    public MechanicBlock(ManaLevel manaLevel) {
        super(Properties.of(Material.GLASS)
                .sound(SoundType.GLASS)
                .strength(2f * manaLevel.level, 2f * manaLevel.level)
                .lightLevel(blockState -> 15)
                .noCollission()
                .noOcclusion());
        this.manaLevel = manaLevel;
        this.registerDefaultState(this.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    public abstract void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, IPosTrack iPosTrack);

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockBlockStateBuilder) {
        blockBlockStateBuilder.add(BlockStateProperties.WATERLOGGED);
    }

    @Override
    public BlockState updateShape(BlockState blockState1, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos1, BlockPos blockPos2) {
        if (blockState1.getValue(BlockStateProperties.WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos1, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(blockState1, direction, blockState2, levelAccessor, blockPos1, blockPos2);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        LevelAccessor levelaccessor = blockPlaceContext.getLevel();
        BlockPos blockpos = blockPlaceContext.getClickedPos();
        return this.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState p_152047_) {
        return PushReaction.BLOCK;
    }
}
