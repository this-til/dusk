package com.til.dusk.common.capability.tile_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;


/**
 * @author til
 */
public class DefaultTileEntity extends BlockEntity implements DuskCapabilityProvider.IDeposit {

    public DuskCapabilityProvider duskCapabilityProvider;

    public DefaultTileEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Override
    public void setDuskCapabilityProvider(DuskCapabilityProvider duskCapabilityProvider) {
        this.duskCapabilityProvider = duskCapabilityProvider;
    }

    @Override
    public DuskCapabilityProvider getDuskCapabilityProvider() {
        return duskCapabilityProvider;
    }
}
