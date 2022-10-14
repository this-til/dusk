package com.til.dusk.util.gson.serializer;

import com.google.gson.*;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Type;
import java.util.Random;

/**
 * @author til
 */
public class RandomSerializer implements JsonSerializer<Random>, JsonDeserializer<Random> {
    @Override
    public Random deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Random();
    }

    @Override
    public JsonElement serialize(Random src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive("This is just a placeholder. Deleting will result in null!");
    }
}
