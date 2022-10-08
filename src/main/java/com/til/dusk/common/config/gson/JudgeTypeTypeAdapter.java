package com.til.dusk.common.config.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.til.dusk.Dusk;
import com.til.dusk.util.Util;

import java.io.IOException;

/***
 * 判断类型
 */
public class JudgeTypeTypeAdapter extends TypeAdapter<Object> {
    public JudgeTypeTypeAdapter() {
    }

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
        if (ConfigGson.typeAdapterMap.containsKey(type)) {
            typeAdapter = ConfigGson.typeAdapterMap.get(type);
        } else {
            typeAdapter = ConfigGson.jsonAdapterAnnotationTypeAdapterFactory.create(ConfigGson.GSON, TypeToken.get(type));
            ConfigGson.typeAdapterMap.put(type, typeAdapter);
        }
        Object obj = typeAdapter.read(in);
        in.nextName();
        in.endObject();
        return obj;
    }

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.beginObject();
        out.name(ConfigGson.TYPE).value(value.getClass().getName());
        out.name(ConfigGson.CONFIG);
        TypeAdapter<?> typeAdapter;
        if (ConfigGson.typeAdapterMap.containsKey(value.getClass())) {
            typeAdapter = ConfigGson.typeAdapterMap.get(value.getClass());
        } else {
            typeAdapter = ConfigGson.jsonAdapterAnnotationTypeAdapterFactory.create(ConfigGson.GSON, TypeToken.get(value.getClass()));
            ConfigGson.typeAdapterMap.put(value.getClass(), typeAdapter);
        }
        typeAdapter.write(out, Util.forcedConversion(value));
        out.endObject();
    }
}
