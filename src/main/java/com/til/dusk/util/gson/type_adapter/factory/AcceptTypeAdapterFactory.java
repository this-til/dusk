package com.til.dusk.util.gson.type_adapter.factory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.til.dusk.Dusk;
import com.til.dusk.common.config.AcceptTypeJson;
import com.til.dusk.util.Util;
import com.til.dusk.util.gson.ConfigGson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
public class AcceptTypeAdapterFactory implements TypeAdapterFactory {
    public Map<TypeToken<?>, TypeAdapter<?>> map = new HashMap<>();

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (type.getRawType().isAnnotationPresent(AcceptTypeJson.class)) {
            if (map.containsKey(type)) {
                return null;
            }
            TypeAdapter<T> typeAdapter = new TypeAdapter<>() {
                TypeAdapter<T> typeAdapter;

                @Override
                public void write(JsonWriter out, T value) throws IOException {
                    if (value == null) {
                        out.nullValue();
                        return;
                    }
                    out.beginObject();
                    out.name(ConfigGson.TYPE).value(value.getClass().getName());
                    if (typeAdapter == null) {
                        typeAdapter = Util.forcedConversion(gson.getAdapter(type));
                    }
                    typeAdapter.write(out, value);
                }

                @Override
                public T read(JsonReader in) throws IOException {
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
                    if (typeAdapter == null) {
                        typeAdapter = Util.forcedConversion(gson.getAdapter(type));
                    }
                    return typeAdapter.read(in);
                }
            };
            map.put(type, typeAdapter);
            return typeAdapter;
        }
        return null;
    }
}
