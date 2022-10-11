package com.til.dusk.util.gson.type_adapter.factory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.til.dusk.util.ReflectionUtil;
import com.til.dusk.util.gson.AcceptTypeJson;
import com.til.dusk.util.gson.type_adapter.AcceptTypeAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
public class AcceptTypeAdapterFactory implements TypeAdapterFactory {
    public Map<TypeToken<?>, TypeAdapter<?>> map = new HashMap<>();

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (pass(type)) {
            if (map.containsKey(type)) {
                return null;
            }
            AcceptTypeAdapter<T> acceptTypeAdapter = new AcceptTypeAdapter<>(gson, type, gson.getDelegateAdapter(this, type));
            map.put(type, acceptTypeAdapter);
            return acceptTypeAdapter;
        }
        return null;
    }

    public <T> boolean pass(TypeToken<T> type) {
        for (Class<?> e : ReflectionUtil.getAllExtends(type.getRawType())) {
            if (e.isAnnotationPresent(AcceptTypeJson.class)) {
                return true;
            }
        }
        return false;
    }
}
