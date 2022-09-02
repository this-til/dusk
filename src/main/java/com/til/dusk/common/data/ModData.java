package com.til.dusk.common.data;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.til.dusk.Dusk;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.data.tag.FluidTag;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.data.tag.PotionsTag;
import com.til.dusk.util.nbt.NBTUtil;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.core.Registry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.tags.TagKey;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
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
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModData {
    public static DataGenerator dataGenerator;

    @SubscribeEvent
    public static void onEvent(GatherDataEvent event) {
        dataGenerator = event.getGenerator();
        BlockTagsProvider blockTagsProvider = new BlockTagsProvider(event.getGenerator(), Dusk.MOD_ID, event.getExistingFileHelper()) {
            @Override
            protected void addTags() {
                for (Map.Entry<TagKey<Block>, List<Block>> entry : BlockTag.map.entrySet()) {
                    this.tag(entry.getKey()).add(entry.getValue().toArray(new Block[0]));
                }
            }
        };
        event.getGenerator().addProvider(true, blockTagsProvider);
        event.getGenerator().addProvider(true, new ItemTagsProvider(event.getGenerator(), blockTagsProvider, Dusk.MOD_ID, event.getExistingFileHelper()) {
            @Override
            protected void addTags() {
                for (Map.Entry<TagKey<Item>, List<Item>> entry : ItemTag.map.entrySet()) {
                    this.tag(entry.getKey()).add(entry.getValue().toArray(new Item[0]));
                }
            }
        });
        event.getGenerator().addProvider(true, new FluidTagsProvider(event.getGenerator(), Dusk.MOD_ID, event.getExistingFileHelper()) {
            @Override
            protected void addTags() {
                for (Map.Entry<TagKey<Fluid>, List<Fluid>> entry : FluidTag.map.entrySet()) {
                    this.tag(entry.getKey()).add(entry.getValue().toArray(new Fluid[0]));
                }
            }
        });
        event.getGenerator().addProvider(true, new TagsProvider<>(event.getGenerator(), Registry.POTION, Dusk.MOD_ID, event.getExistingFileHelper()) {
            @Override
            protected void addTags() {
                for (Map.Entry<TagKey<Potion>, List<Potion>> entry : PotionsTag.map.entrySet()) {
                    this.tag(entry.getKey()).add(entry.getValue().toArray(new Potion[0]));
                }
            }
        });
        event.getGenerator().addProvider(true, new DataProvider() {
            @Override
            public void run(@NotNull CachedOutput cachedOutput) throws IOException {
                for (Map.Entry<String, Shaped> entry : Shaped.ID_MAP.entrySet()) {
                    Shaped shaped = entry.getValue();
                    CompoundTag compoundTag = new CompoundTag();
                    AllNBTPack.CLASS.set(compoundTag, shaped.getClass());
                    entry.getValue().writ(compoundTag);
                    Path mainOutput = ModData.dataGenerator.getOutputFolder();
                    assert shaped.shapedType != null;
                    String pathSuffix = String.format("data/%s/shaped/%s/%s/%s.json",
                            shaped.shapedType.name.getNamespace(),
                            shaped.shapedType.name.getPath(),
                            shaped.shapedDrive.name.getPath(),
                            shaped.name);
                    Path outputPath = mainOutput.resolve(pathSuffix);
                    DataProvider.saveStable(cachedOutput, NBTUtil.toJson(compoundTag), outputPath);
                }

            }

            @Override
            public @NotNull String getName() {
                return "shaped";
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
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, (Consumer<AddReloadListenerEvent>)event -> {
            Shaped.MAP.clear();
            Shaped.ID_MAP.clear();
            event.addListener(new SimpleJsonResourceReloadListener(new Gson(), "shaped") {
                @Override
                protected void apply(@NotNull Map<ResourceLocation, JsonElement> map, @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profilerFiller) {
                    for (Map.Entry<ResourceLocation, JsonElement> entry : map.entrySet()) {
                        try {
                            CompoundTag compoundTag = (CompoundTag) NBTUtil.toTag(entry.getValue());
                            Class<?> sClass = AllNBTPack.CLASS.get(compoundTag);
                            Shaped shaped = (Shaped) sClass.getDeclaredConstructor(ResourceLocation.class, CompoundTag.class).newInstance(entry.getKey(), compoundTag);
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
