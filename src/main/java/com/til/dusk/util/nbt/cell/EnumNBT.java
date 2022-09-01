package com.til.dusk.util.nbt.cell;

import net.minecraft.nbt.NumericTag;
import net.minecraft.nbt.StringTag;

public class EnumNBT<E extends Enum<E>> extends NBTCell<StringTag, E> {

    public final Class<E> enumClass;
    public final E defaultEnum;

    public EnumNBT(Class<E> enumClass, E defaultEnum) {
        this.enumClass = enumClass;
        this.defaultEnum = defaultEnum;
    }

    @Override
    public StringTag as(E e) {
        return StringTag.valueOf(e.toString());
    }

    @Override
    public E from(StringTag numericTag) {
        try {
            return Enum.valueOf(enumClass, numericTag.getAsString());
        } catch (Exception e) {
            return defaultEnum;
        }
    }

    @Override
    public byte getaNBTId() {
        return 8;
    }
}
