package com.til.dusk.common.config.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.til.dusk.common.config.AcceptTypeJson;
import com.til.dusk.util.Util;

/**
 * @author til
 */
public class AcceptTypeJsonTypeAdapterFactory implements TypeAdapterFactory {
    public final JudgeTypeTypeAdapter judgeTypeTypeAdapter = new JudgeTypeTypeAdapter();
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (type.getRawType().isAnnotationPresent(AcceptTypeJson.class)) {
            return Util.forcedConversion(judgeTypeTypeAdapter);
        }
        return null;
    }
}
