package com.til.dusk.util.tag_tool;

import com.til.dusk.Dusk;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class EnumTag<E extends Enum<E>> extends TagTool<E> {

    public final Class<E> enumClass;

    /***
     * 默认枚举
     */
    public final E defaultEnum;

    public EnumTag(ResourceLocation name, Class<E> enumClass, E defaultEnum) {
        super(name);
        this.enumClass = enumClass;
        this.defaultEnum = defaultEnum;
    }

    public EnumTag(String name, Class<E> enumClass, E defaultEnum) {
        this(new ResourceLocation(Dusk.MOD_ID, name), enumClass, defaultEnum);
    }

    @Override
    public E get(CompoundTag nbt) {
        try {
            return Enum.valueOf(enumClass, nbt.getString(tagName));
        } catch (IllegalArgumentException e) {
            return defaultEnum;
        }
    }

    @Override
    public void set(CompoundTag nbt, E e) {
        nbt.putString(tagName, e.toString());
    }
}
