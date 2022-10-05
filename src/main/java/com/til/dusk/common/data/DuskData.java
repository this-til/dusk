package com.til.dusk.common.data;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.til.dusk.Dusk;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.event.DelayTrigger;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.data.tag.FluidTag;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.data.tag.PotionsTag;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Consumer;

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
        event.getGenerator().addProvider(true, new DuskLootTableProvider(event.getGenerator()));
        try {
            event.getGenerator().run();
        } catch (Exception e) {
            Dusk.instance.logger.error("生成数据错误", e);
        }
        event.getGenerator().addProvider(true, new LangProvider());
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
                            Class<?> sClass = AllNBTPack.TYPE.get(entry.getValue().getAsJsonObject());
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
