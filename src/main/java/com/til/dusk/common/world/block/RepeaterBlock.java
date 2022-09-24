package com.til.dusk.common.world.block;

import com.til.dusk.common.capability.tile_entity.RepeaterTileEntity;
import com.til.dusk.common.register.other.TileEntityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author til
 */
public class RepeaterBlock extends DirectionalBlock implements EntityBlock {
    public final ManaLevel manaLevel;


    public RepeaterBlock(ManaLevel manaLevel) {
        super(Properties.of(Material.GLASS)
                .sound(SoundType.GLASS)
                .strength(2f * manaLevel.level, 2f * manaLevel.level)
                .lightLevel(blockState -> 15)
                .noCollission());
        this.manaLevel = manaLevel;

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockBlockStateBuilder) {
        blockBlockStateBuilder.add(FACING, BlockStateProperties.WATERLOGGED);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new RepeaterTileEntity(TileEntityRegister.repeaterTileEntityRegister.blockBlockEntityTypeMap.get(this), blockPos, blockState);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        Direction direction = blockPlaceContext.getClickedFace();
        BlockState blockstate = blockPlaceContext.getLevel().getBlockState(blockPlaceContext.getClickedPos().relative(direction.getOpposite()));
        return blockstate.is(this) && blockstate.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()) : this.defaultBlockState().setValue(FACING, direction);
    }
}
