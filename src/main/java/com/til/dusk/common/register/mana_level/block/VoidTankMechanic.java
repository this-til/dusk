package com.til.dusk.common.register.mana_level.block;

import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.fluid_handler.VoidTankFluidHandler;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.mana_level.ManaLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.AttachCapabilitiesEvent;

/**
 * @author til
 */
public class VoidTankMechanic extends DefaultCapacityMechanic {

    public VoidTankMechanic() {
        super("void_tank");
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        duskModCapability.addCapability(ForgeCapabilities.FLUID_HANDLER, new VoidTankFluidHandler(12800000 * manaLevel.level));
    }
}
