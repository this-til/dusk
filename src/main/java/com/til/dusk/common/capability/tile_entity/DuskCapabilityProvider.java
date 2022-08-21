package com.til.dusk.common.capability.tile_entity;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.INBT;
import com.til.dusk.util.AllNBT;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.DesertVillagePools;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class DuskCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public final Map<Capability<?>, Object> map;
    private final List<INBT> initSerializable = new ArrayList<>();

    public DuskCapabilityProvider(Map<Capability<?>, Object> map) {
        this.map = map;

        for (Capability<?> capability : map.keySet()) {
            Object o = map.get(capability);
            if (o instanceof INBT) {
                initSerializable.add((INBT) o);
            }
        }
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
        for (INBT inbt : initSerializable) {
            if (inbt != null) {
                AllNBT.IGS<Tag> igs = inbt.getNBTBase();
                if (igs != null) {
                    igs.set(nbtTagCompound, inbt.serializeNBT());
                }
            }
        }
        return nbtTagCompound;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        for (INBT inbt : initSerializable) {
            if (inbt != null) {
                AllNBT.IGS<Tag> igs = inbt.getNBTBase();
                if (igs != null) {
                    Tag nbtBase = igs.get(nbt);
                    inbt.deserializeNBT(nbtBase instanceof CompoundTag ? (CompoundTag) nbtBase : new CompoundTag());
                }
            }
        }
    }

    @SubscribeEvent
    public void addTileEntityCapability(AttachCapabilitiesEvent<BlockEntity> event) {
        Map<Capability<?>, Object> map = new HashMap<>();
        DuskCapabilityProvider duskModCapability = new DuskCapabilityProvider(map);
        Block tileEntity = event.getObject().getBlockState().getBlock();
        if (tileEntity instanceof ITileEntityType iTileEntityType) {
            iTileEntityType.add(event, duskModCapability);
        }
        event.addCapability(new ResourceLocation(Dusk.MOD_ID, "dusk_capability_provider"), duskModCapability);
    }

    @SubscribeEvent
    public void addEntityCapability(AttachCapabilitiesEvent<Entity> event) {
    }

    public interface IDeposit {
        void setDuskCapabilityProvider(DuskCapabilityProvider duskCapabilityProvider);

        DuskCapabilityProvider getDuskCapabilityProvider();
    }

}
