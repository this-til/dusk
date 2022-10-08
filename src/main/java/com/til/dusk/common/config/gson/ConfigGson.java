package com.til.dusk.common.config.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.til.dusk.Dusk;
import com.til.dusk.common.config.gson.serializer.DuskColorSerializer;
import com.til.dusk.common.config.gson.serializer.NBTCellSerializer;
import com.til.dusk.common.config.gson.serializer.ResourceLocationSerializer;
import com.til.dusk.common.config.gson.type_adapter.factory.AcceptTypeJsonTypeAdapterFactory;
import com.til.dusk.common.config.gson.type_adapter.factory.DelayedTypeAdapterFactory;
import com.til.dusk.common.config.gson.type_adapter.factory.RegisterBasicsAdapterFactory;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.nbt.cell.AllNBTCell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

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
        gsonBuilder.registerTypeAdapter(ItemStack.class, new NBTCellSerializer<>(AllNBTCell.ITEM_STACK));
        gsonBuilder.registerTypeAdapter(FluidStack.class, new NBTCellSerializer<>(AllNBTCell.FLUID_STATE));
        gsonBuilder.registerTypeAdapter(new TypeToken<TagKey<Item>>() {
        }.getType(), new NBTCellSerializer<>(AllNBTCell.ITEM_TAG));
        gsonBuilder.registerTypeAdapter(new TypeToken<TagKey<Block>>() {
        }.getType(), new NBTCellSerializer<>(AllNBTCell.BLOCK_TAG));
        gsonBuilder.registerTypeAdapter(new TypeToken<TagKey<Fluid>>() {
        }.getType(), new NBTCellSerializer<>(AllNBTCell.FLUID_TAG));
        gsonBuilder.registerTypeAdapterFactory(REGISTER_BASICS_ADAPTER_FACTORY);
        gsonBuilder.registerTypeAdapterFactory(DELAYED_TYPE_ADAPTER_FACTORY);
        gsonBuilder.registerTypeAdapterFactory(ACCEPT_TYPE_JSON_TYPE_ADAPTER_FACTORY);
        gsonBuilder.registerTypeAdapterFactory(ACCEPT_TYPE_JSON_TYPE_ADAPTER_FACTORY.judgeTypeTypeAdapter.jsonAdapterAnnotationTypeAdapterFactory);
        GSON = gsonBuilder.create();
    }


}
