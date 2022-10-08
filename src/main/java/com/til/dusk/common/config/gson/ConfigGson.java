package com.til.dusk.common.config.gson;

import com.google.gson.*;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.til.dusk.Dusk;
import net.minecraft.resources.ResourceLocation;

import java.util.*;

/**
 * @author til
 */
public class ConfigGson {

    public static final String TYPE = "type";
    public static final String CONFIG = "config";

    public static Gson GSON;
    public static JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory;
    public static JudgeTypeTypeAdapter judgeTypeTypeAdapter;

    public static Map<Class<?>, TypeAdapter<?>> typeAdapterMap = new HashMap<>();

    public void onEvent(Dusk.Init init) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(ResourceLocation.class, (JsonDeserializer<ResourceLocation>) (json, typeOfT, context) -> new ResourceLocation(json.getAsString()));
        gsonBuilder.registerTypeAdapter(ResourceLocation.class, (JsonSerializer<ResourceLocation>) (src, typeOfT, context) -> new JsonPrimitive(src.toString()));
        judgeTypeTypeAdapter = new JudgeTypeTypeAdapter();
        jsonAdapterAnnotationTypeAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(new ConstructorConstructor(new HashMap<>(8)));
        gsonBuilder.registerTypeAdapterFactory(new AcceptTypeJsonTypeAdapterFactory());
        gsonBuilder.registerTypeAdapterFactory(jsonAdapterAnnotationTypeAdapterFactory);
        GSON = gsonBuilder.create();
    }


}
