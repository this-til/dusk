package com.til.dusk.common.capability.tile_entity;

import com.til.dusk.Dusk;
import com.til.dusk.util.Util;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.util.PropertySource;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DuskCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public final Map<Capability<?>, Object> map;

    public DuskCapabilityProvider(Map<Capability<?>, Object> map) {
        this.map = map;
    }

    public <T> T addCapability(Capability<T> capability, T t) {
        map.put(capability, t);
        return t;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (map.containsKey(cap)) {
            Object obj = map.get(cap);
            return LazyOptional.of(() -> obj).cast();
        } else {
            return LazyOptional.empty();
        }
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @org.jetbrains.annotations.Nullable Direction side) {
        if (map.containsKey(cap)) {
            Object obj = map.get(cap);
            return LazyOptional.of(() -> obj).cast();
        } else {
            return LazyOptional.empty();
        }
    }


    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbtTagCompound = new CompoundTag();
        map.forEach((k, v) -> {
            if (v instanceof INBTSerializable<?> serializable) {
                nbtTagCompound.put(k.getName(), serializable.serializeNBT());
            }
        });
        return nbtTagCompound;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        map.forEach((k, v) -> {
            if (v instanceof INBTSerializable<?> serializable) {
                INBTSerializable<CompoundTag> compoundTagINBTSerializable = Util.forcedConversion(serializable);
                compoundTagINBTSerializable.deserializeNBT(nbt.getCompound(k.getName()));
            }
        });
    }

    @SubscribeEvent
    public static void addTileEntityCapability(AttachCapabilitiesEvent<BlockEntity> event) {
        Map<Capability<?>, Object> map = new HashMap<>();
        DuskCapabilityProvider duskModCapability = new DuskCapabilityProvider(map);
        BlockEntity blockEntity = event.getObject();
        Block block = blockEntity.getBlockState().getBlock();
        if (block instanceof ITileEntityType iTileEntityType) {
            iTileEntityType.add(event, duskModCapability);
        }
        if (blockEntity instanceof IDeposit deposit) {
            deposit.setDuskCapabilityProvider(duskModCapability);
        }
        event.addCapability(new ResourceLocation(Dusk.MOD_ID, "dusk_capability_provider"), duskModCapability);
    }

    @SubscribeEvent
    public static void addEntityCapability(AttachCapabilitiesEvent<Entity> event) {
    }

    public interface IDeposit {
        void setDuskCapabilityProvider(DuskCapabilityProvider duskCapabilityProvider);

        DuskCapabilityProvider getDuskCapabilityProvider();
    }

}
