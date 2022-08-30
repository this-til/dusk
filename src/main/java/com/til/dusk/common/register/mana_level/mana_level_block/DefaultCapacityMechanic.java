package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.common.capability.clock.Clock;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.handle.Handle;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.mana_level.IManaLevel;
import com.til.dusk.common.capability.tile_entity.DuskCapabilityProvider;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.capability.up.Up;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.world.ModBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.List;

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
        return new ModBlock.MechanicBlock(manaLevel) {
            @Override
            public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability) {
                addCapability(event, duskModCapability, manaLevel);
            }
        };
    }

    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel) {
        duskModCapability.addCapability(CapabilityRegister.iManaLevel.capability, () -> manaLevel);
    }
}
