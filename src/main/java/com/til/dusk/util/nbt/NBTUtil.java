package com.til.dusk.util.nbt;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.nbt.*;

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
            String s = jsonElement.getAsJsonPrimitive().getAsString();
            if (s.isEmpty()) {
                return DoubleTag.valueOf(0);
            }
            String ns = s;
            char c = s.charAt(s.length() - 1);
            if (!Character.isDigit(c)) {
                ns = s.substring(0, s.length() - 1);
            }
            try {
                return DoubleTag.valueOf(Double.parseDouble(ns));
            } catch (Exception e) {
                return net.minecraft.nbt.StringTag.valueOf(jsonElement.getAsJsonPrimitive().getAsString());
            }
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
            return new JsonPrimitive(numericTag.getAsDouble());
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

    public static Tag create(byte getaNBTId, Number numeric) {
        switch (getaNBTId) {
            case Tag.TAG_BYTE -> {
                return ByteTag.valueOf(numeric.byteValue());
            }
            case Tag.TAG_SHORT -> {
                return ShortTag.valueOf(numeric.shortValue());
            }
            case Tag.TAG_INT -> {
                return IntTag.valueOf(numeric.intValue());
            }
            case Tag.TAG_LONG -> {
                return LongTag.valueOf(numeric.longValue());
            }
            case Tag.TAG_FLOAT -> {
                return FloatTag.valueOf(numeric.floatValue());
            }
            case Tag.TAG_DOUBLE -> {
                return DoubleTag.valueOf(numeric.doubleValue());
            }
            case Tag.TAG_STRING -> {
                return StringTag.valueOf(numeric.toString());
            }
            case Tag.TAG_LIST -> {
                return new ListTag();
            }
            case Tag.TAG_COMPOUND -> {
                return new CompoundTag();
            }
            default -> {
                return EndTag.INSTANCE;
            }
        }
    }

/*    public static Tag get(byte id, String name, CompoundTag compoundTag) {
        switch (id) {
            case Tag.TAG_BYTE -> {
                return compoundTag.getA
            }
            case Tag.TAG_SHORT -> {
                return ShortTag.valueOf(numeric.shortValue());
            }
            case Tag.TAG_INT -> {
                return IntTag.valueOf(numeric.intValue());
            }
            case Tag.TAG_LONG -> {
                return LongTag.valueOf(numeric.longValue());
            }
            case Tag.TAG_FLOAT -> {
                return FloatTag.valueOf(numeric.floatValue());
            }
            case Tag.TAG_DOUBLE -> {
                return DoubleTag.valueOf(numeric.doubleValue());
            }
            case Tag.TAG_STRING -> {
                return StringTag.valueOf(numeric.toString());
            }
            case Tag.TAG_LIST -> {
                return new ListTag();
            }
            case Tag.TAG_COMPOUND -> {
                return new CompoundTag()
            }
            default -> {
                return EndTag.INSTANCE;
            }
        }
    }*/
}
