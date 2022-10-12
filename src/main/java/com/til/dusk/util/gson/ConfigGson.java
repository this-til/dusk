package com.til.dusk.util.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.gson.serializer.DuskColorSerializer;
import com.til.dusk.util.gson.serializer.NBTCellSerializer;
import com.til.dusk.util.gson.serializer.ResourceLocationSerializer;
import com.til.dusk.util.gson.type_adapter.factory.AcceptTypeAdapterFactory;
import com.til.dusk.util.gson.type_adapter.factory.DelayedTypeAdapterFactory;
import com.til.dusk.util.gson.type_adapter.factory.RegisterBasicsAdapterFactory;
import com.til.dusk.util.nbt.cell.AllNBTCell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.Map;
import java.util.Random;

/**
 * @author til
 */
public class ConfigGson {

    public static final String TYPE = "$type";
    public static final String GENERIC ="$generic";
    public static final String CONFIG = "$config";

    public final static Gson GSON;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return clazz.isAssignableFrom(Random.class);
            }
        });
        gsonBuilder.registerTypeAdapter(ResourceLocation.class,  new ResourceLocationSerializer());
        gsonBuilder.registerTypeAdapter(DuskColor.class, new DuskColorSerializer());
        gsonBuilder.registerTypeAdapter(ItemStack.class, new NBTCellSerializer<>(AllNBTCell.ITEM_STACK));
        gsonBuilder.registerTypeAdapter(FluidStack.class, new NBTCellSerializer<>(AllNBTCell.FLUID_STATE));
        gsonBuilder.registerTypeAdapter(new TypeToken<TagKey<Item>>() {
        }.getType(), new NBTCellSerializer<>(AllNBTCell.ITEM_TAG));
        gsonBuilder.registerTypeAdapter(new TypeToken<TagKey<Block>>() {
        }.getType(), new NBTCellSerializer<>(AllNBTCell.BLOCK_TAG));
        gsonBuilder.registerTypeAdapter(new TypeToken<TagKey<Fluid>>() {
        }.getType(), new NBTCellSerializer<>(AllNBTCell.FLUID_TAG));
        gsonBuilder.registerTypeAdapter(new TypeToken<Map<TagKey<Item>, Integer>>() {
        }.getType(), new NBTCellSerializer<>(AllNBTCell.ITEM_TAG_INT_MAP));
        gsonBuilder.registerTypeAdapter(new TypeToken<Map<TagKey<Fluid>, Integer>>() {
        }.getType(), new NBTCellSerializer<>(AllNBTCell.FLUID_TAG_INT_MAP));
        gsonBuilder.registerTypeAdapter(new TypeToken<Map<ItemStack, Double>>() {
        }.getType(), new NBTCellSerializer<>(AllNBTCell.ITEM_STACK_DOUBLE_MAP));
        gsonBuilder.registerTypeAdapter(new TypeToken<Map<FluidStack, Double>>() {
        }.getType(), new NBTCellSerializer<>(AllNBTCell.FLUID_STACK_DOUBLE_MAP));
        gsonBuilder.registerTypeAdapterFactory(new RegisterBasicsAdapterFactory());
        gsonBuilder.registerTypeAdapterFactory( new DelayedTypeAdapterFactory());
        gsonBuilder.registerTypeAdapterFactory(new AcceptTypeAdapterFactory());
        GSON = gsonBuilder.create();
    }


}
