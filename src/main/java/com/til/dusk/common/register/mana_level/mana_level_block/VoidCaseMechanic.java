package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.item_handler.VoidCaseItemHandler;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.mana_level.ManaLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.AttachCapabilitiesEvent;

/**
 * @author til
 */
public class VoidCaseMechanic extends DefaultCapacityMechanic{
    public VoidCaseMechanic() {
        super("void_case");
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        duskModCapability.addCapability(ForgeCapabilities.ITEM_HANDLER, new VoidCaseItemHandler(4096L * manaLevel.level));
    }
}
