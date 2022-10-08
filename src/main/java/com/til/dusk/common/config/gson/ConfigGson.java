package com.til.dusk.common.config.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.til.dusk.Dusk;
import com.til.dusk.util.DuskColor;
import net.minecraft.resources.ResourceLocation;

/**
 * @author til
 */
public class ConfigGson {

    public static final String TYPE = "type";
    public static final String CONFIG = "config";

    public static Gson GSON;

    public static final ResourceLocationSerializer JSON_RESOURCE_LOCATION = new ResourceLocationSerializer();
    public static final DuskColorSerializer DUSK_COLOR_SERIALIZER = new DuskColorSerializer();
    public static final DelayedTypeAdapterFactory DELAYED_TYPE_ADAPTER_FACTORY = new DelayedTypeAdapterFactory();
    public static final AcceptTypeJsonTypeAdapterFactory ACCEPT_TYPE_JSON_TYPE_ADAPTER_FACTORY = new AcceptTypeJsonTypeAdapterFactory();
    public static final RegisterBasicsAdapterFactory REGISTER_BASICS_ADAPTER_FACTORY = new RegisterBasicsAdapterFactory();

    public void onEvent(Dusk.Init init) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(ResourceLocation.class, JSON_RESOURCE_LOCATION);
        gsonBuilder.registerTypeAdapter(DuskColor.class, DUSK_COLOR_SERIALIZER);
        gsonBuilder.registerTypeAdapterFactory(REGISTER_BASICS_ADAPTER_FACTORY);
        gsonBuilder.registerTypeAdapterFactory(DELAYED_TYPE_ADAPTER_FACTORY);
        gsonBuilder.registerTypeAdapterFactory(ACCEPT_TYPE_JSON_TYPE_ADAPTER_FACTORY);
        gsonBuilder.registerTypeAdapterFactory(ACCEPT_TYPE_JSON_TYPE_ADAPTER_FACTORY.judgeTypeTypeAdapter.jsonAdapterAnnotationTypeAdapterFactory);
        GSON = gsonBuilder.create();
    }


}
