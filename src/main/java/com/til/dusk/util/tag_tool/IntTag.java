package com.til.dusk.util.tag_tool;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class IntTag extends TagTool<Integer> {

    public IntTag(ResourceLocation name) {
        super(name);
    }

    public IntTag(String name) {
        super(name);
    }

    @Override
    public Integer get(CompoundTag nbt) {
        return nbt.getInt(tagName);
    }

    @Override
    public void set(CompoundTag nbt, Integer integer) {
        nbt.putInt(tagName, integer);
    }
}
