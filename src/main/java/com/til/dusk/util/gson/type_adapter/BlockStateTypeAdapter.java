package com.til.dusk.util.gson.type_adapter;

import com.google.common.collect.ImmutableMap;
import com.google.gson.*;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.til.dusk.util.BlockStateUtil;
import com.til.dusk.util.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author til
 */
public class BlockStateTypeAdapter extends TypeAdapter<BlockState> {
    public static final String NAME = "name";
    public static final String STATE = "state";

    @Override
    public void write(JsonWriter jsonWriter, BlockState blockState) throws IOException {
        if (blockState == null) {
            jsonWriter.nullValue();
            return;
        }
        ImmutableMap<Property<?>, Comparable<?>> immutableMap = blockState.getValues();
        ResourceLocation blockName = ForgeRegistries.BLOCKS.getKey(blockState.getBlock());
        if (blockName == null) {
            jsonWriter.nullValue();
            return;
        }
        if (immutableMap.isEmpty()) {
            jsonWriter.value(blockName.toString());
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(NAME, new JsonPrimitive(blockName.toString()));
        JsonObject state = new JsonObject();
        for (Map.Entry<Property<?>, Comparable<?>> entry : immutableMap.entrySet()) {
            state.add(entry.getKey().getName(), new JsonPrimitive(entry.getKey().getName(Util.forcedConversion(entry.getValue()))));
        }
        jsonObject.add(STATE, state);
        Streams.write(jsonObject, jsonWriter);
    }

    @Override
    public BlockState read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek().equals(JsonToken.NULL)) {
            return Blocks.AIR.defaultBlockState();
        }
        JsonElement jsonElement = Streams.parse(jsonReader);
        if (jsonElement.isJsonNull()) {
            return Blocks.AIR.defaultBlockState();
        }
        if (jsonElement.isJsonPrimitive()) {
            Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(jsonElement.getAsString()));
            return Objects.requireNonNullElse(block, Blocks.AIR).defaultBlockState();
        }
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(jsonObject.get(NAME).getAsString()));
        if (block == null) {
            return Blocks.AIR.defaultBlockState();
        }
        return BlockStateUtil.create(block, jsonObject);
    }


}
