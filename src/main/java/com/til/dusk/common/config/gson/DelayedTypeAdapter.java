package com.til.dusk.common.config.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.util.Util;

import java.lang.reflect.Type;
import java.util.function.Supplier;

/**
 * @author til
 */
public class DelayedTypeAdapter<E> extends TypeAdapter<Delayed<E>> {
    public final Gson gson;
    public final Type type;

    public DelayedTypeAdapter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public void write(JsonWriter out, Delayed<E> value) {
        gson.toJson(value.get(), type, out);
    }

    @Override
    public Delayed<E> read(JsonReader in) {
        return new Delayed<>(Util.forcedConversion((Supplier<E>) () -> gson.fromJson(in, type)));
    }
}
