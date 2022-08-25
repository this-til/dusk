package com.til.dusk.util.tag_tool;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class StringTool extends TagTool<String> {
    public StringTool(ResourceLocation name) {
        super(name);
    }

    public StringTool(String name) {
        super(name);
    }

    @Override
    public String get(CompoundTag nbt) {
        return nbt.getString(tagName);
    }

    @Override
    public void set(CompoundTag nbt, String s) {
        nbt.putString(tagName, s);
    }
}
