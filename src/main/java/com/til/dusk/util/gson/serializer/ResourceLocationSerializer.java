package com.til.dusk.util.gson.serializer;

import com.google.gson.*;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Type;

/**
 * @author til
 */
public class ResourceLocationSerializer implements JsonSerializer<ResourceLocation>, JsonDeserializer<ResourceLocation> {
    @Override
    public ResourceLocation deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new ResourceLocation(json.getAsString());
    }

    @Override
    public JsonElement serialize(ResourceLocation src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }
}
