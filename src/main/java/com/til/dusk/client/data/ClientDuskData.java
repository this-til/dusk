package com.til.dusk.client.data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.til.dusk.Dusk;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.common.data.DuskData;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
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
        IForgeRegistry<UnitRegister<?, ?, ?, ?>>[] registries = new IForgeRegistry[]{Ore.ORE.get(), ManaLevel.MANA_LEVEL.get()};
        event.getGenerator().addProvider(true, new DataProvider() {
            @Override
            public void run(@NotNull CachedOutput cachedOutput) throws IOException {
                for (IForgeRegistry<UnitRegister<?, ?, ?, ?>> registry : registries) {
                    for (UnitRegister<?, ?, ?, ?> unitRegister : registry) {
                        for (Object o : unitRegister.itemEntrySet()) {
                            Map.Entry<ItemUnitRegister<?, ?>, ItemPack> entry = Util.forcedConversion(o);
                            createItemJson(entry.getValue().item(), entry.getKey(), Util.forcedConversion(unitRegister), cachedOutput);
                        }
                        for (Object o : unitRegister.blockEntrySet()) {
                            Map.Entry<BlockUnitRegister<?, ?>, BlockPack> entry = Util.forcedConversion(o);
                            createItemJson(entry.getValue().blockItem(), entry.getKey(), Util.forcedConversion(unitRegister), cachedOutput);
                            createBlockJson(entry.getValue().block(), entry.getKey(), Util.forcedConversion(unitRegister), cachedOutput);
                        }
                    }
                }
                for (ShapedDrive shapedDrive : ShapedDrive.SHAPED_DRIVE.get()) {
                    createItemJson(shapedDrive.blockPack.blockItem(),shapedDrive, null, cachedOutput);
                    createBlockJson(shapedDrive.blockPack.block(), shapedDrive, null, cachedOutput);
                }
                for (ItemPackRegister itemRegister : ItemPackRegister.ITEM_PACK_REGISTER.get()) {
                    if (itemRegister.pack == null) {
                        continue;
                    }
                    createItemJson(itemRegister.pack.item(), itemRegister, null, cachedOutput);
                }
                for (BlockPackRegister blockRegister : BlockPackRegister.BLOCK_PACK_REGISTER.get()) {
                    if (blockRegister.pack == null) {
                        continue;
                    }
                    createItemJson(blockRegister.pack.blockItem(), blockRegister, null, cachedOutput);
                    createBlockJson(blockRegister.pack.block(), blockRegister, null, cachedOutput);
                }
            }

            @Override
            public @NotNull String getName() {
                return "default_block_state";
            }

            public <D> void createItemJson(Item item, DuskItem.ICustomModel<D> iCustomModel, D d, CachedOutput cachedOutput) throws IOException {
                ResourceLocation name = ForgeRegistries.ITEMS.getKey(item);
                if (name == null) {
                    return;
                }
                if (iCustomModel == null) {
                    return;
                }
                createJson(String.format("assets/%s/models/item/%s.json", name.getNamespace(), name.getPath()), iCustomModel.createModel(item, d), cachedOutput);
            }

            public <D> void createBlockJson(Block block, DuskBlock.ICustomModel<D> iCustomModel, D d, CachedOutput cachedOutput) throws IOException {
                ResourceLocation name = ForgeRegistries.BLOCKS.getKey(block);
                if (name == null) {
                    return;
                }
                if (iCustomModel == null) {
                    return;
                }
                createJson(String.format("assets/%s/blockstates/%s.json", name.getNamespace(), name.getPath()), iCustomModel.createBlockModel(block, d), cachedOutput);
            }

            public void createJson(String pack, JsonObject json, CachedOutput cachedOutput) throws IOException {
                if (pack == null) {
                    return;
                }
                if (json == null) {
                    return;
                }
                DataProvider.saveStable(cachedOutput, json, ClientDuskData.dataGenerator.getOutputFolder().resolve(pack));
            }
        }); event.getGenerator().addProvider(true, new LangProvider());
        try {
            event.getGenerator().run();
        } catch (IOException e) {
            Dusk.instance.logger.error("生成数据错误", e);
        }
    }
}
