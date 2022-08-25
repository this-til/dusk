package com.til.dusk.util.tag_tool;

import com.til.dusk.Dusk;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class ListTag<D> extends TagTool<List<D>> {

    public final TagTool<D> cellTag;

    public ListTag(ResourceLocation name, TagTool<D> cellTag) {
        super(name);
        this.cellTag = cellTag;
    }

    public ListTag(String name, TagTool<D> cellTag) {
        this(new ResourceLocation(Dusk.MOD_ID, name), cellTag);
    }

    @Override
    public List<D> get(CompoundTag nbt) {
        net.minecraft.nbt.ListTag tagList = nbt.getList(tagName, 10);
        List<D> list = new ArrayList<>(tagList.size());
        for (Tag tag : tagList) {
            if (tag instanceof CompoundTag compoundTag) {
                list.add(cellTag.get(compoundTag));
            }
        }
        return list;
    }

    @Override
    public void set(CompoundTag nbt, List<D> ds) {
        net.minecraft.nbt.ListTag tagList = new net.minecraft.nbt.ListTag();
        for (D d : ds) {
            CompoundTag compoundTag = new CompoundTag();
            cellTag.set(compoundTag, d);
            tagList.add(compoundTag);
        }
        nbt.put(tagName, tagList);
    }
}
