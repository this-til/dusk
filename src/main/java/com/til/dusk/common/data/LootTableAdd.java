package com.til.dusk.common.data;

import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.ForgeRegistries;

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
            for (BlockPack b : o.blockMap.values()) {
                ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(b.block());
                if (resourceLocation == null) {
                    continue;
                }
                resourceLocation = new ResourceLocation(resourceLocation.getNamespace(), "blocks/" + resourceLocation.getPath());
                biConsumer.accept(resourceLocation, createSingleItemTable(b.block()));
            }
        }
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            for (BlockPack b : manaLevel.blockMap.values()) {
                ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(b.block());
                if (resourceLocation == null) {
                    continue;
                }
                resourceLocation = new ResourceLocation(resourceLocation.getNamespace(), "blocks/" + resourceLocation.getPath());
                biConsumer.accept(resourceLocation, createSingleItemTable(b.block()));
            }
        }
    }
}
