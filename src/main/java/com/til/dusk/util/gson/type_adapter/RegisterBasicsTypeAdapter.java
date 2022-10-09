package com.til.dusk.util.gson.type_adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.util.Util;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;

/**
 * @author til
 */
public class RegisterBasicsTypeAdapter<T extends RegisterBasics<?>> extends TypeAdapter<RegisterBasics<?>> {

    public final Class<T> registerBasicsClass;

    public RegisterBasicsTypeAdapter(Class<T> registerBasicsClass) {
        this.registerBasicsClass = registerBasicsClass;
    }

    @Override
    public void write(JsonWriter out, RegisterBasics<?> value) throws IOException {
        out.value(value.name.toString());
    }

    @Override
    public RegisterBasics<?> read(JsonReader in) throws IOException {
        ResourceLocation name = new ResourceLocation(in.nextString());
        try {
            return Util.forcedConversion(RegisterManage.ALL_MAP.get(Class.forName(registerBasicsClass.getTypeName())).get().getValue(name));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
