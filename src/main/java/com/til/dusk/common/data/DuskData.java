package com.til.dusk.common.data;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.til.dusk.Dusk;
import com.til.dusk.common.event.DelayTrigger;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.data.tag.FluidTag;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.data.tag.PotionsTag;
import com.til.dusk.util.Extension;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.core.Registry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.tags.TagKey;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DuskData {
    public static DataGenerator dataGenerator;

    @SubscribeEvent
    public static void onEvent(GatherDataEvent event) {
        dataGenerator = event.getGenerator();
        DelayTrigger.run(DelayTrigger.TAG, Runnable::run);
        BlockTag blockTag = new BlockTag(event.getGenerator(), event.getExistingFileHelper());
        event.getGenerator().addProvider(true, blockTag);
        event.getGenerator().addProvider(true, new ItemTag(event.getGenerator(), blockTag, event.getExistingFileHelper()));
        event.getGenerator().addProvider(true, new FluidTag(event.getGenerator(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(true, new PotionsTag(event.getGenerator(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(true, new ShapedProvider());
        event.getGenerator().addProvider(true, new ModRecipeProvider(event.getGenerator()));
        event.getGenerator().addProvider(true, new LootTableProvider(event.getGenerator()) {
            @Override
            protected @NotNull List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
                List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> list = new ArrayList<>();
                list.add(new Pair<>(LootTableAdd::new, LootContextParamSets.BLOCK));
                return list;
            }

            @Override
            protected void validate(@NotNull Map<ResourceLocation, LootTable> map, @NotNull ValidationContext validationContext) {
            }
        });
        try {
            event.getGenerator().run();
        } catch (Exception e) {
            Dusk.instance.logger.error("生成数据错误", e);
        }
    }

    @SubscribeEvent
    public static void onEvent(FMLCommonSetupEvent fmlCommonSetupEvent) {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, (Consumer<AddReloadListenerEvent>) event -> {
            Shaped.MAP.clear();
            Shaped.ID_MAP.clear();
            event.addListener(new SimpleJsonResourceReloadListener(new Gson(), "shaped") {
                @Override
                protected void apply(@NotNull Map<ResourceLocation, JsonElement> map, @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profilerFiller) {
                    for (Map.Entry<ResourceLocation, JsonElement> entry : map.entrySet()) {
                        try {
                            Class<?> sClass = AllNBTPack.CLASS.get(entry.getValue().getAsJsonObject());
                            Shaped shaped = (Shaped) sClass.getDeclaredConstructor(ResourceLocation.class, JsonObject.class).newInstance(entry.getKey(), entry.getValue().getAsJsonObject());
                            Shaped.add(shaped);
                        } catch (Exception e) {
                            Dusk.instance.logger.error(String.format("错误配方[%s]", entry.getValue()), e);
                        }
                    }
                }
            });
        });
    }

}
