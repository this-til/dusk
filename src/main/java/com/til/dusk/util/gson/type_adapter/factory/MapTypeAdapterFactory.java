package com.til.dusk.util.gson.type_adapter.factory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.til.dusk.util.Util;
import com.til.dusk.util.gson.type_adapter.MapTypeAdapter;
import net.minecraft.tags.TagKey;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author til
 */
public class MapTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (Map.class.isAssignableFrom(type.getRawType()) && type.getType() instanceof ParameterizedType parameterizedType) {
            Type[] kvType = parameterizedType.getActualTypeArguments();
            if (kvType.length != 2) {
                return null;
            }
            return Util.forcedConversion(new MapTypeAdapter<>(gson,
                    Util.forcedConversion(TypeToken.get(kvType[0])),
                    Util.forcedConversion(TypeToken.get(kvType[1]))));
        }
        return null;
    }
}
