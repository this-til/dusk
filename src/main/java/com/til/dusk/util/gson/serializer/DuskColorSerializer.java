package com.til.dusk.util.gson.serializer;

import com.google.gson.*;
import com.til.dusk.util.DuskColor;

import java.lang.reflect.Type;

/**
 * @author til
 */
public class DuskColorSerializer implements JsonSerializer<DuskColor>, JsonDeserializer<DuskColor> {

    @Override
    public DuskColor deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new DuskColor(json.getAsInt());
    }

    @Override
    public JsonElement serialize(DuskColor src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getRGB());
    }
}
