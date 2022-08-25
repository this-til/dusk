package com.til.dusk.util.tag_tool;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class LongTag extends TagTool<Long> {
    public LongTag(ResourceLocation name) {
        super(name);
    }

    public LongTag(String name) {
        super(name);
    }

    @Override
    public Long get(CompoundTag nbt) {
        return nbt.getLong(tagName);
    }

    @Override
    public void set(CompoundTag nbt, Long aLong) {
        nbt.putLong(tagName, aLong);
    }
}
