package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.common.capability.tile_entity.DuskCapabilityProvider;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.register.mana_level.ManaLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;

/***
 * 收集
 */
public class CollectMechanic extends DefaultCapacityMechanic{

    public CollectMechanic(String name) {
        super(name);
    }

    public CollectMechanic(ResourceLocation name) {
        super(name);
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel) {
        super.addCapability(event, duskModCapability, manaLevel);
    }
}
