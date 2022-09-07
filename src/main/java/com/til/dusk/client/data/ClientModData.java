package com.til.dusk.client.data;

import com.google.common.collect.ImmutableList;
import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.util.Util;
import com.til.dusk.util.pack.BlockPack;
import com.til.dusk.util.pack.FluidPack;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Map;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModData {
    public static DataGenerator dataGenerator;

    public static final String BLOCK_STATE_MODEL =
            """        
                    {
                      "variants": {
                        "": {
                          "model": "%s:block/%s"
                        }
                      }
                    }
                    """;

    public static final String BLOCK_STATE_FATHER =
            """
                    {
                      "parent": "%s:blockstates/%s"
                    }
                    """;

    public static final String ITEM_FATHER =
            """
                    {
                      "parent": "%s:item/%s"
                    }
                    """;

    public static final String ITEM_BLOCk_FATHER =
            """
                    {
                      "parent": "%s:block/%s"
                    }
                    """;

    public static final String FLUID_BLOCK_STATE =
            """
                    {
                      "variants": {
                        "": {
                          "model": "minecraft:block/water"
                        }
                      }
                    }
                    """;


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
                            ImmutableList<BlockState> definition = entry.getValue().block().getStateDefinition().getPossibleStates();
                            if (definition.size() == 1) {
                                asBlock(ForgeRegistries.BLOCKS.getKey(entry.getValue().block()), entry.getKey().getBlockModelMapping(Util.forcedConversion(unitRegister)), cachedOutput);
                            } else {
                                asBlockFather(ForgeRegistries.BLOCKS.getKey(entry.getValue().block()), entry.getKey().getBlockModelMapping(Util.forcedConversion(unitRegister)), cachedOutput);
                            }
                        }
                        for (Object o : unitRegister.fluidMap.entrySet()) {
                            Map.Entry<RegisterBasics.FluidUnitRegister<?, ?>, FluidPack> entry = Util.forcedConversion(o);
                            if (entry.getValue().liquidBlock() != null) {
                                asFluid(ForgeRegistries.BLOCKS.getKey(entry.getValue().liquidBlock()), cachedOutput);
                            }
                        }
                    }
                }
            }

            @Override
            public @NotNull String getName() {
                return "default_block_state";
            }

            public void asBlock(@Nullable ResourceLocation name, @Nullable ResourceLocation model, CachedOutput cachedOutput) throws IOException {
                if (name == null) {
                    return;
                }
                if (model == null) {
                    return;
                }
                String json = String.format(BLOCK_STATE_MODEL, model.getNamespace(), model.getPath());
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

            public void asBlockFather(@Nullable ResourceLocation name, @Nullable ResourceLocation father, CachedOutput cachedOutput) throws IOException {
                if (name == null) {
                    return;
                }
                if (father == null) {
                    return;
                }
                String json = String.format(BLOCK_STATE_FATHER, father.getNamespace(), father.getPath());
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
                String json = String.format(ITEM_BLOCk_FATHER, father.getNamespace(), father.getPath());
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
                String json = String.format(ITEM_FATHER, father.getNamespace(), father.getPath());
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
                writer.write(FLUID_BLOCK_STATE.toCharArray());
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
