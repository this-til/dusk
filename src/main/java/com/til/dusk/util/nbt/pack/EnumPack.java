package com.til.dusk.util.nbt.pack;

import com.til.dusk.Dusk;
import com.til.dusk.util.nbt.cell.NBTCell;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class EnumPack<E extends Enum<E>> extends NBTPack<E> {
    public EnumPack(String name, NBTCell<?, E> nbtCell) {
        super(name, nbtCell);
    }
}
