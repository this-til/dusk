package com.til.dusk.common.data;

import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.function.BiConsumer;

/***
 * 战利品列表反射
 * @author til
 */
public class LootTableAdd extends BlockLoot {
    @Override
    protected void addTables() {
    }

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
        for (Ore o : Ore.ORE.get()) {
            for (Map.Entry<OreBlock, BlockPack> e : o.blockEntrySet()) {
                ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(e.getValue().block());
                if (resourceLocation == null) {
                    continue;
                }
                resourceLocation = new ResourceLocation(resourceLocation.getNamespace(), "blocks/" + resourceLocation.getPath());
                biConsumer.accept(resourceLocation, createSingleItemTable(e.getValue().block()));
            }
        }
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            for (Map.Entry<ManaLevelBlock, BlockPack> e : manaLevel.blockEntrySet()) {
                ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(e.getValue().block());
                if (resourceLocation == null) {
                    continue;
                }
                resourceLocation = new ResourceLocation(resourceLocation.getNamespace(), "blocks/" + resourceLocation.getPath());
                biConsumer.accept(resourceLocation, createSingleItemTable(e.getValue().block()));
            }
        }
    }
}
