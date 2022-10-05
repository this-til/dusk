package com.til.dusk.client.data;

import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import com.til.dusk.Dusk;
import com.til.dusk.common.register.BlockUnitRegister;
import com.til.dusk.common.register.ItemUnitRegister;
import com.til.dusk.common.register.UnitRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.world.block.ModBlock;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.Util;
import com.til.dusk.util.pack.BlockPack;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientDuskData {
    public static DataGenerator dataGenerator;


    @SubscribeEvent
    public static void onEvent(GatherDataEvent event) {
        dataGenerator = event.getGenerator();
        IForgeRegistry<UnitRegister<?, ?, ?, ?>>[] registries = new IForgeRegistry[]{
                Ore.ORE.get(),
                ManaLevel.LEVEL.get()
        };
        event.getGenerator().addProvider(true, new DataProvider() {
            @Override
            public void run(CachedOutput cachedOutput) throws IOException {
                for (IForgeRegistry<UnitRegister<?, ?, ?, ?>> registry : registries) {
                    for (UnitRegister<?, ?, ?, ?> unitRegister : registry) {
                        for (Object o : unitRegister.itemEntrySet()) {
                            Map.Entry<ItemUnitRegister<?, ?>, ItemPack> entry = Util.forcedConversion(o);
                            createItemJson(ForgeRegistries.ITEMS.getKey(entry.getValue().item()), entry.getKey().getItemMoldMapping(Util.forcedConversion(unitRegister)), cachedOutput);
                        }
                        for (Object o : unitRegister.blockEntrySet()) {
                            Map.Entry<BlockUnitRegister<?, ?>, BlockPack> entry = Util.forcedConversion(o);
                            ModBlock.ICustomModel customModel = entry.getKey().getBlockModelMapping(Util.forcedConversion(unitRegister));
                            createItemJson(ForgeRegistries.ITEMS.getKey(entry.getValue().blockItem()), customModel, cachedOutput);
                            createBlockJson(ForgeRegistries.BLOCKS.getKey(entry.getValue().block()), customModel, cachedOutput);
                        }
                        /*for (Object o : unitRegister.fluidMap.entrySet()) {
                            Map.Entry<RegisterBasics.FluidUnitRegister<?, ?>, FluidPack> entry = Util.forcedConversion(o);
                            if (entry.getValue().liquidBlock() != null) {
                                asFluid(ForgeRegistries.BLOCKS.getKey(entry.getValue().liquidBlock()), cachedOutput);
                            }
                        }*/
                    }
                }
                for (ShapedDrive shapedDrive : ShapedDrive.SHAPED_DRIVE.get()) {
                    createItemJson(shapedDrive.name, ShapedDrive.RESOURCE_LOCATION, cachedOutput);
                    createBlockJson(shapedDrive.name, ShapedDrive.RESOURCE_LOCATION, cachedOutput);
                }
                for (RegistryObject<Item> entry : DuskItem.ITEMS.getEntries()) {
                    if (entry.get() instanceof DuskItem.ICustomModel customModel) {
                        createItemJson(ForgeRegistries.ITEMS.getKey(entry.get()), customModel, cachedOutput);
                    }
                }
                for (RegistryObject<Block> entry : ModBlock.BLOCKS.getEntries()) {
                    if (entry.get() instanceof ModBlock.ICustomModel customModel) {
                        createBlockJson(ForgeRegistries.BLOCKS.getKey(entry.get()), customModel, cachedOutput);
                    }
                }
            }

            @Override
            public @NotNull String getName() {
                return "default_block_state";
            }

            public void createItemJson(ResourceLocation name, DuskItem.ICustomModel iCustomModel, CachedOutput cachedOutput) throws IOException {
                if (name == null) {
                    return;
                }
                if (iCustomModel == null) {
                    return;
                }
                createJson(String.format("assets/%s/models/item/%s.json", name.getNamespace(), name.getPath()), iCustomModel.itemJson(), cachedOutput);
            }

            public void createBlockJson(ResourceLocation name, ModBlock.ICustomModel iCustomModel, CachedOutput cachedOutput) throws IOException {
                if (name == null) {
                    return;
                }
                if (iCustomModel == null) {
                    return;
                }
                ResourceLocation blockModel = iCustomModel.blockModelName();
                createJson(String.format("assets/%s/blockstates/%s.json", name.getNamespace(), name.getPath()), iCustomModel.blockStateJson(), cachedOutput);
            }

            public void createJson(String pack, String json, CachedOutput cachedOutput) throws IOException {
                if (pack == null) {
                    return;
                }
                if (json == null) {
                    return;
                }
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                HashingOutputStream hashingoutputstream = new HashingOutputStream(Hashing.sha256(), bytearrayoutputstream);
                Writer writer = new OutputStreamWriter(hashingoutputstream, StandardCharsets.UTF_8);
                writer.write(json.toCharArray());
                writer.close();
                cachedOutput.writeIfNeeded(dataGenerator.getOutputFolder().resolve(pack), bytearrayoutputstream.toByteArray(), hashingoutputstream.hash());
            }
        });
        try {
            event.getGenerator().run();
        } catch (IOException e) {
            Dusk.instance.logger.error("生成数据错误", e);
        }
    }
}
