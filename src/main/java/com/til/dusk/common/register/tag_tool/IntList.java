package com.til.dusk.common.register.tag_tool;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NumericTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class IntList extends TagTool<List<Integer>> {
    public IntList(ResourceLocation name) {
        super(name);
    }

    public IntList(String name) {
        super(name);
    }

    @Override
    public List<Integer> get(CompoundTag nbt) {
        List<Integer> list = new ArrayList<>();
        for (Tag tag : nbt.getList(tagName, 8)) {
            if (tag instanceof NumericTag numericTag) {
                list.add(numericTag.getAsInt());
            }
        }
        return list;
    }

    @Override
    public void set(CompoundTag nbt, List<Integer> integers) {
        net.minecraft.nbt.ListTag listTag = new net.minecraft.nbt.ListTag();
        for (int i : integers) {
            listTag.add(net.minecraft.nbt.IntTag.valueOf(i));
        }
        nbt.put(tagName, listTag);
    }
}
