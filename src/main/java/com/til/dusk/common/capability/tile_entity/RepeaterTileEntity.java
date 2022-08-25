package com.til.dusk.common.capability.tile_entity;


import com.til.dusk.Dusk;
import com.til.dusk.common.world.ModBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author til
 */
public class RepeaterTileEntity extends BlockEntity {
    protected boolean lock;

    public RepeaterTileEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return getCapabilityInside(cap, null);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return getCapabilityInside(cap, side);
    }

    public <T> LazyOptional<T> getCapabilityInside(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (lock) {
            return LazyOptional.empty();
        }
        try {
            lock = true;
            LazyOptional<T> lazyOptional = super.getCapability(cap, side);

            if (lazyOptional.isPresent()) {
                return lazyOptional;
            }
            Level level = getLevel();
            if (level == null) {
                return lazyOptional;
            }
            BlockState blockState = getBlockState();
            Direction direction = blockState.getValue(ModBlock.RepeaterBlock.FACING);
            BlockPos blockPos = this.getBlockPos().offset(direction.getOpposite().getNormal());
            BlockEntity blockEntity = getLevel().getBlockEntity(blockPos);
            if (blockEntity instanceof RepeaterTileEntity) {
                return lazyOptional;
            }
            if (blockEntity != null) {
                return blockEntity.getCapability(cap, direction);
            }
        } catch (Exception e) {
            Dusk.instance.logger.error("中继器获取能力时出现错误", e);
        } finally {
            lock = false;
        }
        return LazyOptional.empty();
    }
}
