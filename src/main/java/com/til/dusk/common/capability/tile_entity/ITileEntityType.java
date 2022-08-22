package com.til.dusk.common.capability.tile_entity;

import com.til.dusk.common.capability.AllCapability;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.register.TileEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/***
 * 接口提供给{@link Block}用来添加能力
 * @author til
 */

public interface ITileEntityType extends EntityBlock {

    /***
     * 添加能力
     * @param event 添加能力时触发的事件
     * @param duskModCapability 能力和能力实体的对照表
     */
    void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability);


    @Nullable
    @Override
    default BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        if (blockState.getBlock() instanceof ITileEntityType iTileEntityType) {
            return new DefaultTileEntity(TileEntityRegister.defaultTileEntityRegister.blockBlockEntityTypeMap.get(iTileEntityType), blockPos, blockState);
        }
        return null;
    }

    @Nullable
    @Override
    default <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        if (!level.isClientSide) {
            return tick();
        }
        return null;
    }

    @Nullable
    default <T extends BlockEntity> BlockEntityTicker<T> tick() {
        return (level, blockPos, blockState, tilEntity) -> {
            if (tilEntity instanceof DuskCapabilityProvider.IDeposit deposit) {
                DuskCapabilityProvider duskCapabilityProvider = deposit.getDuskCapabilityProvider();
                if (duskCapabilityProvider != null) {
                    Object Oup = duskCapabilityProvider.map.get(AllCapability.I_UP);
                    if (Oup instanceof IUp up) {
                        up.up();
                        return;
                    }
                }
            }
            LazyOptional<IUp> up = tilEntity.getCapability(AllCapability.I_UP);
            if (up.isPresent()) {
                up.orElse(null).up();
            }
        };
    }

}
