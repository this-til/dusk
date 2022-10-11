package com.til.dusk.util.gson.type_adapter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.util.Util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.Supplier;

/**
 * @author til
 */
public class DelayedTypeAdapter<E> extends TypeAdapter<Delayed<E>> {
    public final Gson gson;
    public final TypeToken<E> typeToken;
    public final Type type;

    public DelayedTypeAdapter(Gson gson, TypeToken<E> typeToken) {
        this.gson = gson;
        this.typeToken = typeToken;
        Type basicsType = typeToken.getType();
        if (basicsType instanceof ParameterizedType parameterizedType) {
            type = parameterizedType.getActualTypeArguments()[0];
        } else {
            type = ((ParameterizedType) typeToken.getRawType().getGenericSuperclass()).getActualTypeArguments()[0];
        }
    }

    @Override
    public void write(JsonWriter out, Delayed<E> value) {
        gson.toJson(value.get(), type, out);
    }

    @Override
    public Delayed<E> read(JsonReader in) {
        JsonElement value = Streams.parse(in);
        return new Delayed<>(Util.forcedConversion((Supplier<E>) () -> gson.fromJson(value, type))) {
        };
    }
}
