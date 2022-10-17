package com.til.dusk.common.register.mana_level.block;

import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.block_attribute.IBlockAttributeSupplier;
import com.til.dusk.common.capability.clock.Clock;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.multi_block.IMultiBlock;
import com.til.dusk.common.capability.multi_block.ManaLevelMultiBlock;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.attribute.block.BlockAttribute;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.multiblock.MultiBlock;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.Util;
import com.til.dusk.util.math.INumberPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
public abstract class ManaLevelMultiBlockMechanic extends DefaultCapacityMechanic {
    public ManaLevelMultiBlockMechanic(ResourceLocation name) {
        super(name);
    }

    public ManaLevelMultiBlockMechanic(String name) {
        super(name);
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IBack iBack = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
        IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new Clock(iBack, clock.ofValue(manaLevel.level)));
        IMultiBlock iMultiBlock = duskModCapability.addCapability(CapabilityRegister.iMultiBlock.capability, new ManaLevelMultiBlock(multiBlock, iClock, iPosTrack, () -> manaLevel));
        Map<BlockAttribute<?>, Number> map = new HashMap<>(supplier.size());
        for (Map.Entry<BlockAttribute<?>, INumberPack<?>> entry : supplier.entrySet()) {
            map.put(entry.getKey(), Util.forcedConversion(entry.getValue().ofValue(manaLevel.level)));
        }
        IBlockAttributeSupplier iBlockAttributeSupplier = duskModCapability.addCapability(CapabilityRegister.iBlockAttributeSupplier.capability, new IBlockAttributeSupplier.Pack(map, iMultiBlock::isComplete));
    }

    @ConfigField
    public MultiBlock<ManaLevel> multiBlock;

    @ConfigField
    public INumberPack<Integer> clock;

    @ConfigField
    public Map<BlockAttribute<?>, INumberPack<?>> supplier;

}
