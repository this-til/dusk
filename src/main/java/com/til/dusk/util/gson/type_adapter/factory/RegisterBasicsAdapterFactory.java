package com.til.dusk.util.gson.type_adapter.factory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.util.Util;
import com.til.dusk.util.gson.type_adapter.RegisterBasicsTypeAdapter;

/**
 * @author til
 */
public class RegisterBasicsAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (type.getRawType().isAssignableFrom(RegisterBasics.class)) {
            return Util.forcedConversion(new RegisterBasicsTypeAdapter<>(Util.forcedConversion(type.getRawType())));
        }
        return null;
    }
}
