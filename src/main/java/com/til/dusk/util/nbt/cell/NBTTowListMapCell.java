package com.til.dusk.util.nbt.cell;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NBTTowListMapCell<K, V> extends NBTCell<Map<K, V>> {

    public static final String K = "k";
    public static final String V = "v";

    public final ListNBTCell<K> kList;
    public final ListNBTCell<V> vList;

    public NBTTowListMapCell(ListNBTCell<K> kList, ListNBTCell<V> vList) {
        this.kList = kList;
        this.vList = vList;
    }

    @Override
    public CompoundTag as(Map<K, V> kvMap) {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.put(K, kList.as(new ArrayList<>(kvMap.keySet())));
        compoundTag.put(V, vList.as(new ArrayList<>(kvMap.values())));
        return compoundTag;
    }

    @Override
    public Map<K, V> from(Tag tag) {
        CompoundTag compoundTag = getAsCompoundTag(tag);
        Tag kNBT = compoundTag.get(K);
        if (kNBT == null) {
            return new HashMap<>(0);
        }
        Tag vNBT = compoundTag.get(V);
        if (vNBT == null) {
            return new HashMap<>(0);
        }
        List<K> kList = this.kList.from(kNBT);
        List<V> vList = this.vList.from(vNBT);
        int s = kList.size();
        Map<K, V> map = new HashMap<>(s);
        for (int i = 0; i < s; i++) {
            K k = kList.get(i);
            if (k == null) {
                continue;
            }
            if (i >= vList.size()) {
                continue;
            }
            V v = vList.get(i);
            map.put(k, v);
        }
        return map;
    }

    @Override
    public JsonElement asJson(Map<K, V> kvMap) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(K, kList.asJson(new ArrayList<>(kvMap.keySet())));
        jsonObject.add(V, vList.asJson(new ArrayList<>(kvMap.values())));
        return jsonObject;
    }

    @Override
    public Map<K, V> fromJson(JsonElement json) {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement kJson = jsonObject.get(K);
        if (kJson == null) {
            return new HashMap<>(0);
        }
        JsonElement vJson = jsonObject.get(V);
        if (vJson == null) {
            return new HashMap<>(0);
        }
        List<K> kList = this.kList.fromJson(kJson);
        List<V> vList = this.vList.fromJson(vJson);
        int s = kList.size();
        Map<K, V> map = new HashMap<>(s);
        for (int i = 0; i < s; i++) {
            K k = kList.get(i);
            if (k == null) {
                continue;
            }
            if (i >= vList.size()) {
                continue;
            }
            V v = vList.get(i);
            map.put(k, v);
        }
        return map;
    }
}
