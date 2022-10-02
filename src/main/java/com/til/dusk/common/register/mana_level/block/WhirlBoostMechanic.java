package com.til.dusk.common.register.mana_level.block;

import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.mana_handle.WhirlBoostManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.register.other.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.List;

/***
 * @author til
 */
public class WhirlBoostMechanic extends DefaultCapacityMechanic {

    public WhirlBoostMechanic() {
        super("whirl_boost");
    }


    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.manaIn), manaLevel));
        duskModCapability.addCapability(CapabilityRegister.iManaHandle.capability, new WhirlBoostManaHandle(iControl, manaLevel.manaLoss));
    }
}