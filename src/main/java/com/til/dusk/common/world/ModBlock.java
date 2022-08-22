package com.til.dusk.common.world;

import com.til.dusk.common.capability.tile_entity.DuskCapabilityProvider;
import com.til.dusk.common.capability.tile_entity.ITileEntityType;
import com.til.dusk.common.register.mana_level.ManaLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.AttachCapabilitiesEvent;

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

}
