package com.til.dusk.util.tag_tool;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class DoubleTag extends TagTool<Double> {
    public DoubleTag(ResourceLocation name) {
        super(name);
    }

    public DoubleTag(String name) {
        super(name);
    }

    @Override
    public Double get(CompoundTag nbt) {
        return nbt.getDouble(tagName);
    }

    @Override
    public void set(CompoundTag nbt, Double doubleTag) {
        nbt.putDouble(tagName, doubleTag);
    }
}
