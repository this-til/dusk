package com.til.dusk.client.data;

import com.google.common.collect.ImmutableList;
import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.world.block.ModBlock;
import com.til.dusk.common.world.item.ModItem;
import com.til.dusk.util.Util;
import com.til.dusk.util.pack.BlockPack;
import com.til.dusk.util.pack.FluidPack;
import com.til.dusk.util.pack.ItemPack;
import com.til.dusk.util.prefab.JsonPrefab;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Map;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModData {
    public static DataGenerator dataGenerator;


    @SubscribeEvent
    public static void onEvent(GatherDataEvent event) {
        dataGenerator = event.getGenerator();
        IForgeRegistry<RegisterBasics.UnitRegister<?, ?, ?, ?>>[] registries = new IForgeRegistry[]{
                Ore.ORE.get(),
                ManaLevel.LEVEL.get()
        };
        event.getGenerator().addProvider(true, new DataProvider() {
            @Override
            public void run(CachedOutput cachedOutput) throws IOException {
                for (IForgeRegistry<RegisterBasics.UnitRegister<?, ?, ?, ?>> registry : registries) {
                    for (RegisterBasics.UnitRegister<?, ?, ?, ?> unitRegister : registry) {
                        for (Object o : unitRegister.itemMap.entrySet()) {
                            Map.Entry<RegisterBasics.ItemUnitRegister<?, ?>, ItemPack> entry = Util.forcedConversion(o);
                            asItemFather(ForgeRegistries.ITEMS.getKey(entry.getValue().item()), entry.getKey().getItemMoldMapping(Util.forcedConversion(unitRegister)), cachedOutput);
                        }
                        for (Object o : unitRegister.blockMap.entrySet()) {
                            Map.Entry<RegisterBasics.BlockUnitRegister<?, ?>, BlockPack> entry = Util.forcedConversion(o);
                            asItemBlockFather(ForgeRegistries.ITEMS.getKey(entry.getValue().blockItem()), entry.getKey().getBlockItemMoldMapping(Util.forcedConversion(unitRegister)), cachedOutput);
                            ResourceLocation modelName = entry.getKey().getBlockModelMapping(Util.forcedConversion(unitRegister));
                            asBlockCustomJson(ForgeRegistries.BLOCKS.getKey(entry.getValue().block()), MessageFormat.format(entry.getKey().getBlockStateJson(), modelName.getNamespace(), modelName.getPath()), cachedOutput);
                        }
                        for (Object o : unitRegister.fluidMap.entrySet()) {
                            Map.Entry<RegisterBasics.FluidUnitRegister<?, ?>, FluidPack> entry = Util.forcedConversion(o);
                            if (entry.getValue().liquidBlock() != null) {
                                asFluid(ForgeRegistries.BLOCKS.getKey(entry.getValue().liquidBlock()), cachedOutput);
                            }
                        }
                    }
                }
                for (ShapedDrive shapedDrive : ShapedDrive.SHAPED_DRIVE.get()) {
                    asItemBlockFather(shapedDrive.name, ShapedDrive.RESOURCE_LOCATION, cachedOutput);
                    asBlock(shapedDrive.name, ShapedDrive.RESOURCE_LOCATION, cachedOutput);

                }
                for (RegistryObject<Item> entry : ModItem.ITEMS.getEntries()) {
                    if (entry.get() instanceof ModItem.ICustomModel customModel) {
                        asItemFather(entry.getId(), customModel.itemModelName(), cachedOutput);
                    }
                }
                for (RegistryObject<Block> entry : ModBlock.BLOCKS.getEntries()) {
                    if (entry.get() instanceof ModBlock.ICustomModel customModel) {
                        Item blockItem = entry.get().asItem();
                        if (blockItem != null) {
                            asItemBlockFather(ForgeRegistries.ITEMS.getKey(blockItem), customModel.itemModelName(), cachedOutput);
                        }
                        ResourceLocation modelName = customModel.blockModelName();
                        asBlockCustomJson(ForgeRegistries.BLOCKS.getKey(entry.get()), MessageFormat.format(customModel.blockStateJson(), modelName.getNamespace(), modelName.getPath()), cachedOutput);
                    }
                }
            }

            @Override
            public @NotNull String getName() {
                return "default_block_state";
            }

            @Deprecated
            public void asBlock(@Nullable ResourceLocation name, @Nullable ResourceLocation model, CachedOutput cachedOutput) throws IOException {
                if (name == null) {
                    return;
                }
                if (model == null) {
                    return;
                }
                String json = MessageFormat.format(JsonPrefab.BLOCK_STATE_MODEL, model.getNamespace(), model.getPath());
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                HashingOutputStream hashingoutputstream = new HashingOutputStream(Hashing.sha256(), bytearrayoutputstream);
                Writer writer = new OutputStreamWriter(hashingoutputstream, StandardCharsets.UTF_8);
                writer.write(json.toCharArray());
                writer.close();

                Path mainOutput = dataGenerator.getOutputFolder();
                String pathSuffix = String.format("assets/%s/blockstates/%s.json", name.getNamespace(), name.getPath());
                Path outputPath = mainOutput.resolve(pathSuffix);

                cachedOutput.writeIfNeeded(outputPath, bytearrayoutputstream.toByteArray(), hashingoutputstream.hash());
            }

            public void asBlockCustomJson(@Nullable ResourceLocation name, String json, CachedOutput cachedOutput) throws IOException {
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                HashingOutputStream hashingoutputstream = new HashingOutputStream(Hashing.sha256(), bytearrayoutputstream);
                Writer writer = new OutputStreamWriter(hashingoutputstream, StandardCharsets.UTF_8);
                writer.write(json.toCharArray());
                writer.close();

                Path mainOutput = dataGenerator.getOutputFolder();
                String pathSuffix = String.format("assets/%s/blockstates/%s.json", name.getNamespace(), name.getPath());
                Path outputPath = mainOutput.resolve(pathSuffix);

                cachedOutput.writeIfNeeded(outputPath, bytearrayoutputstream.toByteArray(), hashingoutputstream.hash());
            }

            /*public void asItem(@Nullable ResourceLocation name, @Nullable ResourceLocation father, CachedOutput cachedOutput) throws IOException{
                if (name == null) {
                    return;
                }
                if (father == null) {
                    return;
                }
                String json = String.format(FATHER_MODEL, father.getNamespace(), father.getPath());
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                HashingOutputStream hashingoutputstream = new HashingOutputStream(Hashing.sha256(), bytearrayoutputstream);
                Writer writer = new OutputStreamWriter(hashingoutputstream, StandardCharsets.UTF_8);
                writer.write(json.toCharArray());
                writer.close();


                Path mainOutput = dataGenerator.getOutputFolder();
                String pathSuffix = String.format("assets/%s/models/item/%s.json", name.getNamespace(), name.getPath());
                Path outputPath = mainOutput.resolve(pathSuffix);

                cachedOutput.writeIfNeeded(outputPath, bytearrayoutputstream.toByteArray(), hashingoutputstream.hash());
            }*/

            public void asItemBlockFather(@Nullable ResourceLocation name, @Nullable ResourceLocation father, CachedOutput cachedOutput) throws IOException {
                if (name == null) {
                    return;
                }
                if (father == null) {
                    return;
                }
                String json = MessageFormat.format(JsonPrefab.ITEM_BLOCK_FATHER, father.getNamespace(), father.getPath());
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                HashingOutputStream hashingoutputstream = new HashingOutputStream(Hashing.sha256(), bytearrayoutputstream);
                Writer writer = new OutputStreamWriter(hashingoutputstream, StandardCharsets.UTF_8);
                writer.write(json.toCharArray());
                writer.close();

                Path mainOutput = dataGenerator.getOutputFolder();
                String pathSuffix = String.format("assets/%s/models/item/%s.json", name.getNamespace(), name.getPath());
                Path outputPath = mainOutput.resolve(pathSuffix);

                cachedOutput.writeIfNeeded(outputPath, bytearrayoutputstream.toByteArray(), hashingoutputstream.hash());
            }

            public void asItemFather(@Nullable ResourceLocation name, @Nullable ResourceLocation father, CachedOutput cachedOutput) throws IOException {
                if (name == null) {
                    return;
                }
                if (father == null) {
                    return;
                }
                String json = MessageFormat.format(JsonPrefab.ITEM_FATHER, father.getNamespace(), father.getPath());
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                HashingOutputStream hashingoutputstream = new HashingOutputStream(Hashing.sha256(), bytearrayoutputstream);
                Writer writer = new OutputStreamWriter(hashingoutputstream, StandardCharsets.UTF_8);
                writer.write(json.toCharArray());
                writer.close();

                Path mainOutput = dataGenerator.getOutputFolder();
                String pathSuffix = String.format("assets/%s/models/item/%s.json", name.getNamespace(), name.getPath());
                Path outputPath = mainOutput.resolve(pathSuffix);

                cachedOutput.writeIfNeeded(outputPath, bytearrayoutputstream.toByteArray(), hashingoutputstream.hash());
            }

            public void asFluid(@Nullable ResourceLocation name, CachedOutput cachedOutput) throws IOException {
                if (name == null) {
                    return;
                }
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                HashingOutputStream hashingoutputstream = new HashingOutputStream(Hashing.sha256(), bytearrayoutputstream);
                Writer writer = new OutputStreamWriter(hashingoutputstream, StandardCharsets.UTF_8);
                writer.write(JsonPrefab.FLUID_BLOCK_STATE.toCharArray());
                writer.close();

                Path mainOutput = dataGenerator.getOutputFolder();
                String pathSuffix = String.format("assets/%s/blockstates/%s.json", name.getNamespace(), name.getPath());
                Path outputPath = mainOutput.resolve(pathSuffix);

                cachedOutput.writeIfNeeded(outputPath, bytearrayoutputstream.toByteArray(), hashingoutputstream.hash());
            }
        });
        try {
            event.getGenerator().run();
        } catch (IOException e) {
            Dusk.instance.logger.error("生成数据错误", e);
        }
    }
}
