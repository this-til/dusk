package com.til.dusk.common.data;

import com.mojang.datafixers.util.Pair;
import com.til.dusk.Dusk;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
                biConsumer.accept(resourceLocation, createSingleItemTable(b.block()));
            }
        }
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            for (BlockPack b : manaLevel.blockMap.values()) {
                ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(b.block());
                if (resourceLocation == null) {
                    continue;
                }
                biConsumer.accept(resourceLocation, createSingleItemTable(b.block()));
            }
        }
    }
/*  public static Method createOreDrop;
    public static Field lootTables_tables;
    public static Map<ResourceLocation, Supplier<LootTable.Builder>> lootTableMap = new HashMap<>();

    static {
        try {
            createOreDrop = BlockLoot.class.getDeclaredMethod("createOreDrop", Block.class, Item.class);
            createOreDrop.setAccessible(true);
        } catch (NoSuchMethodException e) {
            Dusk.instance.logger.error("", e);
        }
        try {
            lootTables_tables = LootTables.class.getDeclaredField("tables");
            lootTables_tables.setAccessible(true);
        } catch (NoSuchFieldException e) {
            Dusk.instance.logger.error("", e);
        }
    }

    @SubscribeEvent
    public static void onEvent(ServerStartingEvent event) {
        LootTables lootTables = event.getServer().getLootTables();
        try {
            Map<ResourceLocation, LootTable> oldMap = (Map<ResourceLocation, LootTable>) lootTables_tables.get(lootTables);
            Map<ResourceLocation, LootTable> map = new HashMap<>();
            for (Ore o : Ore.ORE.get()) {
                for (BlockPack b : o.blockMap.values()) {
                    ResourceLocation blockName = ForgeRegistries.BLOCKS.getKey(b.block());
                    if (blockName == null) {
                        continue;
                    }
                    LootTable.Builder builder = (LootTable.Builder) createOreDrop.invoke(null, b.block(), b);
                    map.put(new ResourceLocation(blockName.getNamespace(), "blocks/" + blockName.getPath()), builder.build());
                }
            }
            for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
                for (BlockPack value : manaLevel.blockMap.values()) {
                    ResourceLocation blockName = ForgeRegistries.BLOCKS.getKey(value.block());
                    if (blockName == null) {
                        continue;
                    }
                    LootTable.Builder builder = (LootTable.Builder) createOreDrop.invoke(null, value.block(), value);
                    map.put(new ResourceLocation(blockName.getNamespace(), "blocks/" + blockName.getPath()), builder.build());
                }
            }
            lootTableMap.forEach((k, v) -> map.put(k, v.get().build()));
            map.putAll(oldMap);
            lootTables_tables.set(lootTables, map);
        } catch (IllegalAccessException | RuntimeException | InvocationTargetException e) {
            Dusk.instance.logger.error("", e);
        }
    }*/


}
