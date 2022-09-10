package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.clock.Clock;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.handle.Handle;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.capability.tile_entity.DuskCapabilityProvider;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.capability.up.Up;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.world.block.MechanicBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author til
 */
public class HandleMechanic extends DefaultCapacityMechanic {

    public final Supplier<List<ShapedType>> getShapedTypeList;

    public HandleMechanic(ResourceLocation name, Supplier<List<ShapedType>> getShapedTypeList) {
        super(name);
        this.getShapedTypeList = getShapedTypeList;
    }

    public HandleMechanic(String name, Supplier<List<ShapedType>> getShapedTypeList) {
        this(new ResourceLocation(Dusk.MOD_ID, name), getShapedTypeList);
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.manaIn, BindType.manaOut, BindType.itemIn, BindType.itemOut, BindType.fluidIn, BindType.fluidOut, BindType.modelStore), manaLevel));
        IUp iUp = duskModCapability.addCapability(CapabilityRegister.iUp.capability, new Up());
        IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new Clock(iUp, manaLevel));
        IHandle iHandle = duskModCapability.addCapability(CapabilityRegister.iHandle.capability, new Handle(iPosTrack, getShapedTypeList.get(), iControl, iClock, iUp, manaLevel));
    }

}
