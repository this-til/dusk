package com.til.dusk.util.tag_tool;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class BooleanTag extends TagTool<Boolean> {
    public BooleanTag(ResourceLocation name) {
        super(name);
    }

    public BooleanTag(String name) {
        super(name);
    }

    @Override
    public Boolean get(CompoundTag nbt) {
        return nbt.getBoolean(tagName);
    }

    @Override
    public void set(CompoundTag nbt, Boolean aBoolean) {
        nbt.putBoolean(tagName, aBoolean);
    }
}
