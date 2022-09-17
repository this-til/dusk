package com.til.dusk.util.nbt.cell;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.HashMap;
import java.util.Map;

public class NBTMapCell<K, V> extends NBTCell<Map<K, V>> {

    public static final String K = "k";
    public static final String V = "v";

    public final NBTCell<K> kCell;
    public final NBTCell<V> vCell;


    public NBTMapCell(NBTCell<K> kCell, NBTCell<V> vCell) {
        this.kCell = kCell;
        this.vCell = vCell;
    }

    @Override
    public Tag as(Map<K, V> kvMap) {
        ListTag listTag = new ListTag();
        for (Map.Entry<K, V> kvEntry : kvMap.entrySet()) {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.put(K, kCell.as(kvEntry.getKey()));
            compoundTag.put(V, vCell.as(kvEntry.getValue()));
            listTag.add(compoundTag);
        }
        return listTag;
    }

    @Override
    public Map<K, V> from(Tag t) {
        ListTag listTag = getAsListTag(t);
        Map<K, V> map = new HashMap<>(listTag.size());
        for (Tag tag : listTag) {
            CompoundTag compoundTag = getAsCompoundTag(tag);
            map.put(kCell.from(compoundTag.get(K)), vCell.from(compoundTag.get(V)));
        }
        return map;
    }

    @Override
    public JsonElement asJson(Map<K, V> kvMap) {
        JsonArray jsonArray = new JsonArray();
        for (Map.Entry<K, V> kvEntry : kvMap.entrySet()) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add(K, kCell.asJson(kvEntry.getKey()));
            jsonObject.add(V, vCell.asJson(kvEntry.getValue()));
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public Map<K, V> fromJson(JsonElement json) {
        JsonArray jsonArray = json.getAsJsonArray();
        Map<K, V> map = new HashMap<>(jsonArray.size());
        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            map.put(kCell.fromJson(jsonObject.get(K)), vCell.fromJson(jsonObject.get(V)));
        }
        return map;
    }
}
