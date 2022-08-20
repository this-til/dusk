package com.til.dusk.common.capability;

import com.til.dusk.util.AllNBT;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DuskModCapability implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public final Map<Capability<?>, Object> map;
    private final List<INBT> inbtSerializables = new ArrayList<>();

    public DuskModCapability(Map<Capability<?>, Object> map) {
        this.map = map;

        for (Capability<?> capability : map.keySet()) {
            Object o = map.get(capability);
            if (o instanceof INBT) {
                inbtSerializables.add((INBT) o);
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
        for (INBT inbt : inbtSerializables) {
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
        for (INBT inbt : inbtSerializables) {
            if (inbt != null) {
                AllNBT.IGS<Tag> igs = inbt.getNBTBase();
                if (igs != null) {
                    Tag nbtBase = igs.get(nbt);
                    inbt.deserializeNBT(nbtBase instanceof CompoundTag ? (CompoundTag) nbtBase : new CompoundTag());
                }
            }
        }
    }

}
