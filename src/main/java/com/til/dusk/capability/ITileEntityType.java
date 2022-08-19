package com.til.dusk.capability;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.Map;


public interface ITileEntityType {

    default void add(AttachCapabilitiesEvent<BlockEntity> event, Map<Capability<?>, Object> map) {
    }

}
