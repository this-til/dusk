package com.til.dusk.common.register.mana_level.block;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.block_attribute.IBlockAttribute;
import com.til.dusk.common.capability.clock.Clock;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.handle.Handle;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.bind_type.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.List;

/**
 * @author til
 */
public abstract class HandleMechanic extends DefaultCapacityMechanic {


    public HandleMechanic(ResourceLocation name) {
        super(name);
    }

    public HandleMechanic(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.manaIn, BindType.manaOut, BindType.itemIn, BindType.itemOut, BindType.fluidIn, BindType.fluidOut, BindType.modelStore), manaLevel));
        IBack iUp = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
        IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new Clock(iUp, manaLevel));
        IBlockAttribute iBlockAttribute = duskModCapability.addCapability(CapabilityRegister.iBlockAttribute.capability, new IBlockAttribute.ProbePack(iPosTrack, iClock));
        IHandle iHandle = duskModCapability.addCapability(CapabilityRegister.iHandle.capability, new Handle(iPosTrack,  Shaped.get(shapedTypeList), iControl, iBlockAttribute, iClock, iUp, manaLevel.parallel));
    }

    @ConfigField
    public List<ShapedType> shapedTypeList;

}
