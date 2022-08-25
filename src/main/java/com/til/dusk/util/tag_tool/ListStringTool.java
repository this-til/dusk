package com.til.dusk.util.tag_tool;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class ListStringTool extends TagTool<List<String>> {

    public ListStringTool(ResourceLocation name) {
        super(name);
    }

    public ListStringTool(String name) {
        super(name);
    }

    @Override
    public List<String> get(CompoundTag nbt) {
        List<String> list = new ArrayList<>();
        for (Tag tag : nbt.getList(tagName, 8)) {
            list.add(tag.getAsString());
        }
        return list;
    }

    @Override
    public void set(CompoundTag nbt, List<String> strings) {
        net.minecraft.nbt.ListTag listTag = new net.minecraft.nbt.ListTag();
        for (String string : strings) {
            listTag.add(StringTag.valueOf(string));
        }
        nbt.put(tagName, listTag);
    }
}
