package com.til.dusk.util.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.gson.serializer.*;
import com.til.dusk.util.gson.type_adapter.BlockStateTypeAdapter;
import com.til.dusk.util.gson.type_adapter.factory.AcceptTypeAdapterFactory;
import com.til.dusk.util.gson.type_adapter.factory.BlockStateTypeAdapterFactory;
import com.til.dusk.util.gson.type_adapter.factory.DelayedTypeAdapterFactory;
import com.til.dusk.util.gson.type_adapter.factory.RegisterBasicsAdapterFactory;
import com.til.dusk.util.nbt.cell.AllNBTCell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.Map;
import java.util.Random;

/**
 * @author til
 */
public class ConfigGson {

    public static final String TYPE = "$type";
    public static final String GENERIC = "$generic";
    public static final String CONFIG = "$config";

    public final static Gson GSON;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(Random.class, new RandomSerializer());
        gsonBuilder.registerTypeAdapter(ResourceLocation.class, new ResourceLocationSerializer());
        gsonBuilder.registerTypeAdapter(DuskColor.class, new NBTCellSerializer<>(AllNBTCell.COLOR));
        gsonBuilder.registerTypeAdapter(BlockState.class, new BlockStateTypeAdapter());
        gsonBuilder.registerTypeAdapter(Item.class, new NBTCellSerializer<>(AllNBTCell.ITEM));
        gsonBuilder.registerTypeAdapter(Block.class, new NBTCellSerializer<>(AllNBTCell.BLOCK));
        gsonBuilder.registerTypeAdapter(Fluid.class, new NBTCellSerializer<>(AllNBTCell.FLUID));
        gsonBuilder.registerTypeAdapter(Biomes.class, new NBTCellSerializer<>(AllNBTCell.BIOME));
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
        gsonBuilder.registerTypeAdapter(new TypeToken<Map<Block, Block>>() {
        }.getType(), new NBTCellSerializer<>(AllNBTCell.BLOCK_BLOCK_MAP));
        gsonBuilder.registerTypeAdapterFactory(new BlockStateTypeAdapterFactory());
        gsonBuilder.registerTypeAdapterFactory(new RegisterBasicsAdapterFactory());
        gsonBuilder.registerTypeAdapterFactory(new DelayedTypeAdapterFactory());
        gsonBuilder.registerTypeAdapterFactory(new AcceptTypeAdapterFactory());
        GSON = gsonBuilder.create();
    }


}
