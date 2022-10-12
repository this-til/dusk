package com.til.dusk.client.data;

import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import com.til.dusk.Dusk;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.common.register.BlockUnitRegister;
import com.til.dusk.common.register.ItemUnitRegister;
import com.til.dusk.common.register.UnitRegister;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.world.block.BlockPackRegister;
import com.til.dusk.common.register.world.item.ItemPackRegister;
import com.til.dusk.common.world.block.DuskBlock;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.Util;
import com.til.dusk.util.pack.BlockPack;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
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
                ManaLevel.MANA_LEVEL.get()
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
                            DuskBlock.ICustomModel customModel = entry.getKey().getBlockModelMapping(Util.forcedConversion(unitRegister));
                            createItemJson(ForgeRegistries.ITEMS.getKey(entry.getValue().blockItem()), customModel, cachedOutput);
                            createBlockJson(ForgeRegistries.BLOCKS.getKey(entry.getValue().block()), customModel, cachedOutput);
                        }
                    }
                }
                for (ShapedDrive shapedDrive : ShapedDrive.SHAPED_DRIVE.get()) {
                    createItemJson(shapedDrive.name, ShapedDrive.RESOURCE_LOCATION, cachedOutput);
                    createBlockJson(shapedDrive.name, ShapedDrive.RESOURCE_LOCATION, cachedOutput);
                }
                for (ItemPackRegister itemRegister : ItemPackRegister.ITEM_PACK_REGISTER.get()) {
                    if (itemRegister.pack == null) {
                        continue;
                    }
                    createItemJson(ForgeRegistries.ITEMS.getKey(itemRegister.pack.item()), itemRegister, cachedOutput);
                }
                for (BlockPackRegister blockRegister : BlockPackRegister.BLOCK_PACK_REGISTER.get()) {
                    if (blockRegister.pack == null) {
                        continue;
                    }
                    if (blockRegister instanceof DuskBlock.ICustomModel customModel) {
                        createItemJson(ForgeRegistries.ITEMS.getKey(blockRegister.pack.blockItem()), customModel, cachedOutput);
                        createBlockJson(ForgeRegistries.BLOCKS.getKey(blockRegister.pack.block()), customModel, cachedOutput);
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
                String json = iCustomModel.itemJson();
                if (json.isEmpty()) {
                    return;
                }
                createJson(String.format("assets/%s/models/item/%s.json", name.getNamespace(), name.getPath()), json, cachedOutput);
            }

            public void createBlockJson(ResourceLocation name, DuskBlock.ICustomModel iCustomModel, CachedOutput cachedOutput) throws IOException {
                if (name == null) {
                    return;
                }
                if (iCustomModel == null) {
                    return;
                }
                String json = iCustomModel.blockStateJson();
                if (json.isEmpty()) {
                    return;
                }
                createJson(String.format("assets/%s/blockstates/%s.json", name.getNamespace(), name.getPath()), json, cachedOutput);
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
        event.getGenerator().addProvider(true, new LangProvider());
        try {
            event.getGenerator().run();
        } catch (IOException e) {
            Dusk.instance.logger.error("生成数据错误", e);
        }
    }
}
