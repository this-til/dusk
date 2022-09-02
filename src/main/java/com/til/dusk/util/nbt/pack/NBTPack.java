package com.til.dusk.util.nbt.pack;

import com.til.dusk.util.Util;
import com.til.dusk.util.nbt.cell.NBTCell;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.EndTag;
import net.minecraft.nbt.Tag;

/**
 * @author til
 */
public class NBTPack<E> {

    public final String name;
    public final NBTCell<E> nbtCell;

    public NBTPack(String name, NBTCell<E> nbtCell) {
        this.name = name;
        this.nbtCell = nbtCell;
    }

    public E get(CompoundTag nbt) {
        Tag tag = nbt.get(name);
        return nbtCell.from(tag == null ? EndTag.INSTANCE : tag);
    }

    public void set(CompoundTag nbt, E v) {
        nbt.put(name, nbtCell.as(v));
    }

    public boolean contains(CompoundTag compoundTag) {
        return compoundTag.contains(name);
    }
}
