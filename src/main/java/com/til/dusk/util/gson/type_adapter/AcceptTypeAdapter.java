package com.til.dusk.util.gson.type_adapter;

import com.google.gson.*;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.til.dusk.Dusk;
import com.til.dusk.util.gson.ConfigGson;
import com.til.dusk.util.Util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class AcceptTypeAdapter extends TypeAdapter<Object> {
    public static final ConstructorConstructor CONSTRUCTOR_CONSTRUCTOR = new ConstructorConstructor(new HashMap<>());
    public static final JsonAdapterAnnotationTypeAdapterFactory JSON_ADAPTER_ANNOTATION_TYPE_ADAPTER_FACTORY = new JsonAdapterAnnotationTypeAdapterFactory(CONSTRUCTOR_CONSTRUCTOR);
    public static final ReflectiveTypeAdapterFactory REFLECTIVE_TYPE_ADAPTER_FACTORY = new ReflectiveTypeAdapterFactory(
            new ConstructorConstructor(new HashMap<>()), FieldNamingPolicy.IDENTITY, Excluder.DEFAULT, JSON_ADAPTER_ANNOTATION_TYPE_ADAPTER_FACTORY);

    public static final Map<Type, TypeAdapter<?>> MAP = new HashMap<>();

    public static final String NAME = "type";

    @Override
    public Object read(JsonReader in) throws IOException {
        if (in.peek().equals(JsonToken.NULL)) {
            return null;
        }
        in.beginObject();
        in.nextName();
        Class<?> type;
        try {
            type = Class.forName(in.nextString());
        } catch (ClassNotFoundException e) {
            Dusk.instance.logger.error("反序列化时未找到对应类", e);
            return null;
        }
        TypeAdapter<?> typeAdapter;
        if (MAP.containsKey(type)) {
            typeAdapter = MAP.get(type);
        } else {
            typeAdapter = REFLECTIVE_TYPE_ADAPTER_FACTORY.create(ConfigGson.GSON, TypeToken.get(type));
            MAP.put(type, typeAdapter);
        }
        Object obj = typeAdapter.read(in);
        in.nextName();
        in.endObject();
        return obj;
    }

    @Override
    public void write(JsonWriter out, Object  value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.beginObject();
        out.name(ConfigGson.TYPE).value(value.getClass().getName());
        out.name(ConfigGson.CONFIG);
        TypeAdapter<?> typeAdapter;
        if (MAP.containsKey(value.getClass())) {
            typeAdapter = MAP.get(value.getClass());
        } else {
            typeAdapter = REFLECTIVE_TYPE_ADAPTER_FACTORY.create(ConfigGson.GSON, TypeToken.get(value.getClass()));
            MAP.put(value.getClass(), typeAdapter);
        }
        typeAdapter.write(out, Util.forcedConversion(value));
        out.endObject();
    }
}
