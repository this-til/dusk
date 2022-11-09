package com.til.dusk.common.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * @author til
 */
public class FacingSlabBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty HALF = BooleanProperty.create("half");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public static final VoxelShape UP_BOX = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape DOWN_BOX = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    public static final VoxelShape EAST_BOX = Block.box(8D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape WEST_BOX = Block.box(0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    public static final VoxelShape SOUTH_BOX = Block.box(0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape NORTH_BOX = Block.box(0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);

    public FacingSlabBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(HALF, Boolean.TRUE).setValue(FACING, Direction.DOWN).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState blockState) {
        return !blockState.getValue(HALF);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockBlockStateBuilder) {
        blockBlockStateBuilder.add(FACING, HALF, WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        if (!blockState.getValue(HALF)) {
            return Shapes.block();
        }
        return switch (blockState.getValue(FACING)) {
            case EAST -> EAST_BOX;
            case WEST -> WEST_BOX;
            case SOUTH -> SOUTH_BOX;
            case NORTH -> NORTH_BOX;
            case UP -> UP_BOX;
            case DOWN -> DOWN_BOX;
        };
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext p_56361_) {
        BlockPos blockpos = p_56361_.getClickedPos();
        BlockState blockstate = p_56361_.getLevel().getBlockState(blockpos);
        if (blockstate.is(this)) {
            return blockstate.setValue(HALF, Boolean.FALSE).setValue(WATERLOGGED, Boolean.FALSE);
        } else {
            FluidState fluidstate = p_56361_.getLevel().getFluidState(blockpos);
            BlockState outBlockState = this.defaultBlockState().setValue(HALF, Boolean.TRUE).setValue(WATERLOGGED, fluidstate.getType().equals(Fluids.WATER));
            Direction direction = p_56361_.getClickedFace();
            return outBlockState.setValue(FACING, direction.getOpposite());
        }
    }

    public boolean canBeReplaced(BlockState blockState, BlockPlaceContext blockPlaceContext) {
        ItemStack itemstack = blockPlaceContext.getItemInHand();
        if (!itemstack.is(this.asItem())) {
            return false;
        }
        boolean half = blockState.getValue(HALF);
        if (!half) {
            return false;
        }
        return blockPlaceContext.getClickedFace().getOpposite().equals(blockState.getValue(FACING));
    }

    @Override
    public FluidState getFluidState(BlockState p_56397_) {
        return p_56397_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_56397_);
    }

    @Override
    public boolean placeLiquid(@NotNull LevelAccessor levelAccessor, @NotNull BlockPos p_56307_, @NotNull BlockState blockState, FluidState fluidState) {
        return blockState.getValue(HALF) && SimpleWaterloggedBlock.super.placeLiquid(levelAccessor, p_56307_, blockState, fluidState);
    }

    @Override
    public boolean canPlaceLiquid(@NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull BlockState blockState, Fluid fluid) {
        return blockState.getValue(HALF) && SimpleWaterloggedBlock.super.canPlaceLiquid(blockGetter, blockPos, blockState, fluid);
    }

    @Override
    public BlockState updateShape(@NotNull BlockState blockState, @NotNull Direction direction, @NotNull BlockState blockState_, @NotNull LevelAccessor levelAccessor, @NotNull BlockPos blockPos, @NotNull BlockPos blockPos1) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(blockState, direction, blockState_, levelAccessor, blockPos, blockPos1);
    }

    @Override
    public boolean isPathfindable(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull PathComputationType pathComputationType) {
        return pathComputationType.equals(PathComputationType.WATER) && blockGetter.getFluidState(blockPos).is(FluidTags.WATER);
    }

}
