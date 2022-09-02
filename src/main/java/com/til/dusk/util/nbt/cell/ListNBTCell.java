package com.til.dusk.util.nbt.cell;

import com.til.dusk.util.Util;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.ArrayList;
import java.util.List;

public class ListNBTCell<E> extends NBTCell<List<E>> {

    protected final NBTCell<E> nbtCell;

    public ListNBTCell(NBTCell<E> nbtCell) {
        this.nbtCell = nbtCell;
    }

    @Override
    public ListTag as(List<E> es) {
        ListTag listTag = new ListTag();
        for (E e : es) {
            listTag.add(nbtCell.as(e));
        }
        return listTag;
    }

    @Override
    public List<E> from(Tag tag) {
        List<E> list = new ArrayList<>();
        for (Tag _tag : getAsListTag(tag)) {
            list.add(nbtCell.from(_tag));
        }
        return list;

    }

}
