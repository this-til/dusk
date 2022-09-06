package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.capability.tile_entity.DuskCapabilityProvider;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.world.block.MechanicBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;

/**
 * @author til
 */
public class DefaultCapacityMechanic extends Mechanic {

    public DefaultCapacityMechanic(ResourceLocation name) {
        super(name);
    }

    public DefaultCapacityMechanic(String name) {
        super(name);
    }

    @Override
    public Block createBlock(ManaLevel manaLevel) {
        return new MechanicBlock(manaLevel) {
            @Override
            public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, IPosTrack iPosTrack) {
                addCapability(event, duskModCapability, manaLevel,iPosTrack);
            }
        };
    }

    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        duskModCapability.addCapability(CapabilityRegister.iManaLevel.capability, () -> manaLevel);
    }
}
