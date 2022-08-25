package com.til.dusk.util.tag_tool;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class TagTag extends TagTool<CompoundTag> {
    public TagTag(ResourceLocation name) {
        super(name);
    }

    public TagTag(String name) {
        super(name);
    }

    @Override
    public CompoundTag get(CompoundTag nbt) {
        return nbt.getCompound(tagName);
    }

    @Override
    public void set(CompoundTag nbt, CompoundTag compoundTag) {
        nbt.put(tagName, compoundTag);
    }
}
