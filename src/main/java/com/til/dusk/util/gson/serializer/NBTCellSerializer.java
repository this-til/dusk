package com.til.dusk.util.gson.serializer;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.til.dusk.util.nbt.cell.NBTCell;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.lang.reflect.Type;

/**
 * @author til
 */
public class NBTCellSerializer<E> implements JsonSerializer<E>, JsonDeserializer<E> {
    public final NBTCell<E> cell;

    public static final TypeToken<TagKey<Block>> T = new TypeToken<>() {
    };

    public NBTCellSerializer(NBTCell<E> cell) {
        this.cell = cell;
    }

    @Override
    public E deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonNull()) {
            return null;
        }
        return cell.fromJson(json);
    }

    @Override
    public JsonElement serialize(E src, Type typeOfSrc, JsonSerializationContext context) {
        if (src == null) {
            return JsonNull.INSTANCE;
        }
        return cell.asJson(src);
    }
}
