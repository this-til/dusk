package com.til.dusk.common.register.tag_tool;

import com.til.dusk.Dusk;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class PackTag<T> extends TagTool<T> {

    public final TagTool<T> tTagTool;

    public PackTag(ResourceLocation name, TagTool<T> tTagTool) {
        super(name);
        this.tTagTool = tTagTool;
    }

    public PackTag(String name, TagTool<T> tTagTool) {
        this(new ResourceLocation(Dusk.MOD_ID, name), tTagTool);
    }

    @Override
    public T get(CompoundTag nbt) {
        return tTagTool.get(nbt.getCompound(tagName));
    }

    @Override
    public void set(CompoundTag nbt, T t) {
        tTagTool.set(nbt.getCompound(tagName), t);
    }
}
