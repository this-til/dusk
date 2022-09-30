package com.til.dusk.util.nbt.cell;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.IConfigKey;
import com.til.dusk.util.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @author til
 */
public class ConfigMapCell extends NBTCell<ConfigMap> {
    @Override
    public Tag as(ConfigMap configMapCell) {
        CompoundTag compoundTag = new CompoundTag();
        for (Map.Entry<IConfigKey<?>, Supplier<?>> e : configMapCell.entrySet()) {
            e.getKey().set(compoundTag, Util.forcedConversion(e.getValue()));
        }
        return compoundTag;
    }

    @Override
    public ConfigMap from(Tag t) {
        ConfigMap configMap = new ConfigMap();
        CompoundTag compoundTag = getAsCompoundTag(t);
        for (String s : compoundTag.getAllKeys()) {
            IConfigKey<?> iConfigKey = IConfigKey.getKey(s);
            configMap.put(iConfigKey, iConfigKey.get(compoundTag));
        }
        return configMap;
    }

    @Override
    public JsonElement asJson(ConfigMap configMapCell) {
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<IConfigKey<?>, Supplier<?>> e : configMapCell.entrySet()) {
            e.getKey().set(jsonObject, Util.forcedConversion(e.getValue()));
        }
        return jsonObject;
    }

    @Override
    public ConfigMap fromJson(JsonElement json) {
        ConfigMap configMap = new ConfigMap();
        JsonObject jsonObject = json.getAsJsonObject();
        for (String s : jsonObject.keySet()) {
            IConfigKey<?> iConfigKey = IConfigKey.getKey(s);
            configMap.put(iConfigKey, iConfigKey.get(jsonObject));
        }
        return configMap;
    }
}
