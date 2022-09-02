package com.til.dusk.util.nbt.cell;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NumericTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

public class EnumNBT<E extends Enum<E>> extends NBTCell<E> {

    public final Class<E> enumClass;
    public final E defaultEnum;

    public EnumNBT(Class<E> enumClass, E defaultEnum) {
        this.enumClass = enumClass;
        this.defaultEnum = defaultEnum;
    }

    @Override
    public Tag as(E e) {
        return StringTag.valueOf(e.toString());
    }

    @Override
    public E from(Tag numericTag) {
        try {
            return Enum.valueOf(enumClass, numericTag.getAsString());
        } catch (Exception e) {
            return defaultEnum;
        }
    }
}
