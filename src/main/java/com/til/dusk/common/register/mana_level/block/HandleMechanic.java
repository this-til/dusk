package com.til.dusk.common.register.mana_level.block;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.clock.Clock;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.handle.Handle;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.other.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author til
 */
public abstract class HandleMechanic extends DefaultCapacityMechanic {

    public final Supplier<Set<ShapedType>> getShapedTypeList;

    public HandleMechanic(ResourceLocation name, Supplier<Set<ShapedType>> getShapedTypeList) {
        super(name);
        this.getShapedTypeList = getShapedTypeList;
    }

    public HandleMechanic(String name, Supplier<Set<ShapedType>> getShapedTypeList) {
        this(new ResourceLocation(Dusk.MOD_ID, name), getShapedTypeList);
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.manaIn, BindType.manaOut, BindType.itemIn, BindType.itemOut, BindType.fluidIn, BindType.fluidOut, BindType.modelStore), manaLevel));
        IBack iUp = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
        IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new Clock(iUp, manaLevel));
        IHandle iHandle = duskModCapability.addCapability(CapabilityRegister.iHandle.capability, new Handle(iPosTrack, getShapedTypeList.get(), iControl, iClock, iUp, manaLevel));
    }

}
