package com.til.dusk.common.register.tag_tool;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class FloatTag extends TagTool<Float> {
    public FloatTag(ResourceLocation name) {
        super(name);
    }

    public FloatTag(String name) {
        super(name);
    }

    @Override
    public Float get(CompoundTag nbt) {
        return nbt.getFloat(tagName);
    }

    @Override
    public void set(CompoundTag nbt, Float aFloat) {
        nbt.putFloat(tagName, aFloat);
    }
}
