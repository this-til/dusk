package com.til.dusk.common.register.tag_tool;

import com.til.dusk.Dusk;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTag<K, V> extends TagTool<Map<K, V>> {

    public final TagTool<List<K>> k;
    public final TagTool<List<V>> v;

    public final String kName;
    public final String vName;

    public MapTag(ResourceLocation name, TagTool<List<K>> k, TagTool<List<V>> v) {
        super(name);
        this.k = k;
        this.v = v;
        kName = tagName + "#k";
        vName = tagName + "#v";
    }

    public MapTag(String name, TagTool<List<K>> k, TagTool<List<V>> v) {
        this(new ResourceLocation(Dusk.MOD_ID, name), k, v);
    }

    @Override
    public Map<K, V> get(CompoundTag nbt) {
        List<K> kList = k.get(nbt.getCompound(kName));
        List<V> vList = v.get(nbt.getCompound(vName));
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
    public void set(CompoundTag nbt, Map<K, V> kvMap) {
        CompoundTag kCompoundTag = new CompoundTag();
        k.set(kCompoundTag, new ArrayList<>(kvMap.keySet()));
        nbt.put(kName, kCompoundTag);
        CompoundTag vCompoundTag = new CompoundTag();
        v.set(vCompoundTag, new ArrayList<>(kvMap.values()));
        nbt.put(vName, vCompoundTag);
    }
}
