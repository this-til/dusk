package com.til.dusk.common.world;

import com.til.dusk.common.capability.tile_entity.DuskCapabilityProvider;
import com.til.dusk.common.capability.tile_entity.ITileEntityType;
import com.til.dusk.common.capability.tile_entity.RepeaterTileEntity;
import com.til.dusk.common.register.TileEntityRegister;
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
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author til
 */
public class ModBlock {

    public abstract static class MechanicBlock extends Block implements ITileEntityType {

        public final ManaLevel manaLevel;

        public MechanicBlock(ManaLevel manaLevel) {
            super(Properties.of(Material.GLASS)
                    .sound(SoundType.GLASS)
                    .strength(2f * manaLevel.level, 2f * manaLevel.level)
                    .lightLevel(blockState -> 15)
                    .noCollission());
            this.manaLevel = manaLevel;
        }

        @Override
        public abstract void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability);
    }

    public static class RepeaterBlock extends DirectionalBlock implements EntityBlock {
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
            blockBlockStateBuilder.add(FACING);
        }


        @Nullable
        @Override
        public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
            return new RepeaterTileEntity(TileEntityRegister.repeaterTileEntityRegister.blockBlockEntityTypeMap.get(this), blockPos, blockState);
        }

        @Override
        public BlockState getStateForPlacement(BlockPlaceContext p_53087_) {
            Direction direction = p_53087_.getClickedFace();
            BlockState blockstate = p_53087_.getLevel().getBlockState(p_53087_.getClickedPos().relative(direction.getOpposite()));
            return blockstate.is(this) && blockstate.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()) : this.defaultBlockState().setValue(FACING, direction);
        }
    }

}
