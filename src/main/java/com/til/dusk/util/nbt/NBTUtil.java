package com.til.dusk.util.nbt;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NumericTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

import java.util.Set;

public class NBTUtil {
    public static void clear(CompoundTag compoundTag) {
        Set<String> strings = compoundTag.getAllKeys();
        if (strings.isEmpty()) {
            return;
        }
        for (String string : strings) {
            compoundTag.remove(string);
        }
    }

    public static void clear(JsonObject jsonObject) {
        for (String s : jsonObject.keySet()) {
            jsonObject.remove(s);
        }
    }

    public static void copy(CompoundTag old, CompoundTag compoundTag) {
        clear(old);
        Set<String> strings = compoundTag.getAllKeys();
        if (strings.isEmpty()) {
            return;
        }
        for (String string : strings) {
            Tag tag = compoundTag.get(string);
            if (tag == null) {
                continue;
            }
            old.put(string, tag);
        }
    }

    public static Tag toTag(JsonElement jsonElement) {
        if (jsonElement.isJsonNull()) {
            return net.minecraft.nbt.StringTag.valueOf("null");
        }
        if (jsonElement.isJsonPrimitive()) {
            return net.minecraft.nbt.StringTag.valueOf(jsonElement.getAsJsonPrimitive().getAsString());
        }
        if (jsonElement.isJsonArray()) {
            net.minecraft.nbt.ListTag listTag = new net.minecraft.nbt.ListTag();
            for (JsonElement element : jsonElement.getAsJsonArray()) {
                listTag.add(toTag(element));
            }
            return listTag;
        }
        if (jsonElement.isJsonObject()) {
            CompoundTag compoundTag = new CompoundTag();
            for (String s : jsonElement.getAsJsonObject().keySet()) {
                compoundTag.put(s, toTag(jsonElement.getAsJsonObject().get(s)));
            }
            return compoundTag;
        }
        return new CompoundTag();
    }

    public static JsonElement toJson(Tag tag) {
        if (tag instanceof StringTag stringTag) {
            return new JsonPrimitive(stringTag.getAsString());
        }
        if (tag instanceof NumericTag numericTag) {
            return new JsonPrimitive(numericTag.getAsString());
        }
        if (tag instanceof net.minecraft.nbt.ListTag listTag) {
            JsonArray arrayList = new JsonArray();
            for (Tag tag1 : listTag) {
                arrayList.add(toJson(tag1));
            }
            return arrayList;
        }
        if (tag instanceof CompoundTag compoundTag) {
            JsonObject jsonObject = new JsonObject();
            for (String allKey : compoundTag.getAllKeys()) {
                jsonObject.add(allKey, toJson(compoundTag.get(allKey)));
            }
            return jsonObject;
        }
        return new JsonObject();
    }
}
