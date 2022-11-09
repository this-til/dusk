package com.til.dusk.common.register.mana_level.block;

import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.block_attribute.IBlockAttribute;
import com.til.dusk.common.capability.clock.Clock;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.handle.MKHandle;
import com.til.dusk.common.capability.multi_block.IMultiBlock;
import com.til.dusk.common.capability.multi_block.ManaLevelMultiBlock;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.attribute.block.BlockAttribute;
import com.til.dusk.common.register.bind_type.BindType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.multiblock.MultiBlock;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.AllShaped;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.util.Util;
import com.til.dusk.util.math.INumberPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fluids.FluidStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author til
 */
public abstract class MKBasicsMechanic extends DefaultCapacityMechanic {
    public MKBasicsMechanic(ResourceLocation name) {
        super(name);
    }

    public MKBasicsMechanic(String name) {
        super(name);
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IBack iBack = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.manaIn, BindType.manaOut, BindType.itemIn, BindType.itemOut, BindType.fluidIn, BindType.fluidOut, BindType.modelStore), manaLevel));
        IClock testClock = new Clock(iBack, this.testClock.ofValue(manaLevel.level));
        IClock runClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new Clock(iBack, manaLevel));
        IMultiBlock iMultiBlock = duskModCapability.addCapability(CapabilityRegister.iMultiBlock.capability, new ManaLevelMultiBlock(multiBlock, testClock, iPosTrack, () -> manaLevel));
        Map<BlockAttribute<?>, Number> map = new HashMap<>(supplier.size());
        for (Map.Entry<BlockAttribute<?>, INumberPack<?>> entry : supplier.entrySet()) {
            map.put(entry.getKey(), Util.forcedConversion(entry.getValue().ofValue(manaLevel.level)));
        }
        IBlockAttribute iBlockAttribute = duskModCapability.addCapability(CapabilityRegister.iBlockAttribute.capability, new IBlockAttribute.Pack(map));
        IHandle iHandle = duskModCapability.addCapability(CapabilityRegister.iHandle.capability, new MKHandle(iPosTrack, Shaped.get(shapedTypeList), iControl, iBlockAttribute, runClock, iBack, 1, iMultiBlock, Ore.coolant.get(OreFluid.solution).fluidTag(), 1, new FluidStack(Ore.overheatedCoolantOre.get(OreFluid.solution).source(), 1)));
    }

    @Override
    public void defaultConfig() {
        testClock = new INumberPack.IIntPack.Constant(30 * 20);
        supplier = Map.of(
                BlockAttribute.efficiency, new INumberPack.IIntPack.LinearFunction(new INumberPack.IIntPack.Constant(1), new INumberPack.IIntPack.Constant(0)),
                BlockAttribute.consume, new INumberPack.IIntPack.LinearFunction(new INumberPack.IIntPack.Constant(1), new INumberPack.IIntPack.Constant(0)));
    }

    @ConfigField
    public INumberPack<Integer> testClock;

    @ConfigField
    public MultiBlock<ManaLevel> multiBlock;

    @ConfigField
    public Map<BlockAttribute<?>, INumberPack<?>> supplier;


    @ConfigField
    public List<ShapedType> shapedTypeList;

}
