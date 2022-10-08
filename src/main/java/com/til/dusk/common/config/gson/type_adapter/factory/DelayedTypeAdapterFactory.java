package com.til.dusk.common.config.gson.type_adapter.factory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.til.dusk.common.config.gson.type_adapter.DelayedTypeAdapter;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.util.Util;

/**
 * @author til
 */
public class DelayedTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (type.getRawType().equals(Delayed.class)) {
            return Util.forcedConversion(new DelayedTypeAdapter<>(gson, type.getType()));
        }
        return null;
    }
}
