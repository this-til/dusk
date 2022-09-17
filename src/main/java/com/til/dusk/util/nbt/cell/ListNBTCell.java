package com.til.dusk.util.nbt.cell;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * @author til
 */
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

    @Override
    public JsonElement asJson(List<E> es) {
        JsonArray jsonArray = new JsonArray();
        for (E e : es) {
            jsonArray.add(nbtCell.asJson(e));
        }
        return jsonArray;
    }

    @Override
    public List<E> fromJson(JsonElement json) {
        List<E> list = new ArrayList<>();
        for (JsonElement jsonElement : json.getAsJsonArray()) {
            list.add(nbtCell.fromJson(jsonElement));
        }
        return list;
    }
}
