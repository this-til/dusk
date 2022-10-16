package com.til.dusk.util.gson.type_adapter;

import com.google.gson.*;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.til.dusk.Dusk;
import com.til.dusk.util.Util;
import com.til.dusk.util.gson.ConfigGson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
public class AcceptTypeAdapter<T> extends TypeAdapter<T> {
    public static final String TYPE = "$type";
    public static final String GENERIC = "$generic";
    public final TypeAdapter<T> typeAdapter;
    public final Gson gson;
    public final TypeToken<T> typeToken;

    public AcceptTypeAdapter(Gson gson, TypeToken<T> type, TypeAdapter<T> typeAdapter) {
        this.typeAdapter = typeAdapter;
        this.gson = gson;
        this.typeToken = type;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(TYPE, new JsonPrimitive(value.getClass().getName()));
        JsonElement vJson;
        if (typeToken.getRawType().equals(value.getClass())) {
            vJson = typeAdapter.toJsonTree(value);
        } else {
            vJson = gson.getAdapter(value.getClass()).toJsonTree(Util.forcedConversion(value));
        }
        if (vJson.isJsonObject() && !vJson.getAsJsonObject().has(TYPE)) {
            for (Map.Entry<String, JsonElement> entry : vJson.getAsJsonObject().entrySet()) {
                jsonObject.add(entry.getKey(), entry.getValue());
            }
        } else {
            jsonObject.add(ConfigGson.CONFIG, vJson);
        }
        Streams.write(jsonObject, out);
    }

    @Override
    public T read(JsonReader in) throws IOException {
        if (in.peek().equals(JsonToken.NULL)) {
            return null;
        }
        JsonObject jsonObject = Streams.parse(in).getAsJsonObject();
        Class<T> type;
        try {
            type = Util.forcedConversion(Class.forName(jsonObject.get(TYPE).getAsString()));
        } catch (ClassNotFoundException e) {
            Dusk.instance.logger.error("反序列化时未找到对应类", e);
            return null;
        }
        boolean isInertia = !jsonObject.has(ConfigGson.CONFIG);
        JsonElement vJson = isInertia ? jsonObject : jsonObject.get(ConfigGson.CONFIG);
        if (typeToken.getRawType().equals(type)) {
            return typeAdapter.fromJsonTree(vJson);
        } else {
            return gson.getAdapter(type).fromJsonTree(vJson);
        }
    }
}
