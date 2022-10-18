package com.til.dusk.util.gson.type_adapter.factory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.til.dusk.util.Util;
import com.til.dusk.util.gson.type_adapter.BlockStateTypeAdapter;
import net.minecraft.world.level.block.state.BlockState;

public class BlockStateTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if (BlockState.class.isAssignableFrom(typeToken.getRawType())) {
            return Util.forcedConversion(new BlockStateTypeAdapter());
        }
        return null;
    }
}
