package com.til.dusk.common.config.gson.serializer;

import com.google.gson.*;
import com.til.dusk.util.nbt.cell.AllNBTCell;
import com.til.dusk.util.nbt.cell.NBTCell;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.lang.reflect.Type;

/**
 * @author til
 */
public class NBTCellSerializer<E> implements JsonSerializer<E>, JsonDeserializer<E> {
    public final NBTCell<E> cell;

    public NBTCellSerializer(NBTCell<E> cell) {
        this.cell = cell;
    }

    @Override
    public E deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return cell.fromJson(json);
    }

    @Override
    public JsonElement serialize(E src, Type typeOfSrc, JsonSerializationContext context) {
        return cell.asJson(src);
    }
}
