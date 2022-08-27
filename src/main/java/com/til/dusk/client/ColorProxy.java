package com.til.dusk.client;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.world.ModItem;
import com.til.dusk.util.Extension;
import com.til.dusk.util.pack.BlockPack;
import com.til.dusk.util.pack.ItemPack;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author til
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ColorProxy {

    public static final Map<Item, ItemColorPack> ITEM_COLOR_PACK_MAP = new HashMap<>();
    public static final Map<Block, BlockColorPack> BLOCK_COLOR_PACK_MAP = new HashMap<>();

    @SubscribeEvent
    public static void itemColor(RegisterColorHandlersEvent.Item event) {
        for (Ore ore : Ore.ORE.get()) {
            for (ItemPack value : ore.itemMap.values()) {
                ITEM_COLOR_PACK_MAP.put(value.item(), new ItemColorPack(value.item()));
            }
            for (BlockPack value : ore.blockMap.values()) {
                ITEM_COLOR_PACK_MAP.put(value.blockItem(), new ItemColorPack(value.blockItem()));
            }
        }
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            for (ItemPack value : manaLevel.itemMap.values()) {
                ITEM_COLOR_PACK_MAP.put(value.item(), new ItemColorPack(value.item()));
            }
            for (BlockPack value : manaLevel.blockMap.values()) {
                ITEM_COLOR_PACK_MAP.put(value.blockItem(), new ItemColorPack(value.blockItem()));
            }
        }
        for (Ore ore : Ore.ORE.get()) {
            for (ItemPack value : ore.itemMap.values()) {
                ITEM_COLOR_PACK_MAP.get(value.item()).addClock(0, itemStack -> ore.color);
            }
            for (BlockPack value : ore.blockMap.values()) {
                ITEM_COLOR_PACK_MAP.get(value.blockItem()).addClock(1, itemStack -> ore.color);
            }
        }
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            for (ItemPack value : manaLevel.itemMap.values()) {
                ITEM_COLOR_PACK_MAP.get(value.item()).addClock(0, itemStack -> manaLevel.color);
            }
            for (BlockPack value : manaLevel.blockMap.values()) {
                ITEM_COLOR_PACK_MAP.get(value.blockItem()).addClock(0, itemStack -> manaLevel.color);
            }
            ITEM_COLOR_PACK_MAP.get(manaLevel.blockMap.get(ManaLevelBlock.moonlight).blockItem()).addClock(1, itemStack -> ColorPrefab.MOONLIGHT_COLOR);
            ITEM_COLOR_PACK_MAP.get(manaLevel.blockMap.get(ManaLevelBlock.sunlight).blockItem()).addClock(1, itemStack -> ColorPrefab.SUNLIGHT_COLOR);
        }
        for (Map.Entry<Item, ItemColorPack> itemItemColorPackEntry : ITEM_COLOR_PACK_MAP.entrySet()) {
            event.register(itemItemColorPackEntry.getValue(), itemItemColorPackEntry.getKey());
        }
    }

    @SubscribeEvent
    public static void blockColor(RegisterColorHandlersEvent.Block event) {
        for (Ore ore : Ore.ORE.get()) {
            for (BlockPack value : ore.blockMap.values()) {
                BLOCK_COLOR_PACK_MAP.put(value.block(), new BlockColorPack(value.block()));
            }
        }
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            for (BlockPack value : manaLevel.blockMap.values()) {
                BLOCK_COLOR_PACK_MAP.put(value.block(), new BlockColorPack(value.block()));
            }
        }
        for (Ore ore : Ore.ORE.get()) {
            for (BlockPack value : ore.blockMap.values()) {
                BLOCK_COLOR_PACK_MAP.get(value.block()).addClock(1, (blockState, blockAndTintGetter, blockPos) -> ore.color);
            }
        }
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            for (BlockPack value : manaLevel.blockMap.values()) {
                BLOCK_COLOR_PACK_MAP.get(value.block()).addClock(0, (blockState, blockAndTintGetter, blockPos) -> manaLevel.color);
            }
            BLOCK_COLOR_PACK_MAP.get(manaLevel.blockMap.get(ManaLevelBlock.sunlight).block()).addClock(1, (blockState, blockAndTintGetter, blockPos) -> ColorPrefab.SUNLIGHT_COLOR);
            BLOCK_COLOR_PACK_MAP.get(manaLevel.blockMap.get(ManaLevelBlock.moonlight).block()).addClock(1, (blockState, blockAndTintGetter, blockPos) -> ColorPrefab.MOONLIGHT_COLOR);
        }
        for (Map.Entry<Block, BlockColorPack> blockBlockColorPackEntry : BLOCK_COLOR_PACK_MAP.entrySet()) {
            event.register(blockBlockColorPackEntry.getValue(), blockBlockColorPackEntry.getKey());
        }
    }

    public static class ItemColorPack implements ItemColor {
        public final ItemLike itemLike;
        public final Map<Integer, Extension.Func_1I<ItemStack, Color>> layerColor = new HashMap<>();

        public ItemColorPack(ItemLike itemLike) {
            this.itemLike = itemLike;
        }

        public ItemColorPack addClock(int layer, Extension.Func_1I<ItemStack, Color> color) {
            layerColor.put(layer, color);
            return this;
        }

        @Override
        public int getColor(@NotNull ItemStack itemStack, int layer) {
            if (layerColor.containsKey(layer)) {
                return layerColor.get(layer).func(itemStack).getRGB();
            }
            return -1;
        }
    }

    public static class BlockColorPack implements BlockColor {
        public final Block block;
        public final Map<Integer, Extension.Func_3I<BlockState, BlockAndTintGetter, BlockPos, Color>> layerColor = new HashMap<>();

        public BlockColorPack(Block block) {
            this.block = block;
        }

        public BlockColorPack addClock(int layer, Extension.Func_3I<BlockState, BlockAndTintGetter, BlockPos, Color> color) {
            layerColor.put(layer, color);
            return this;
        }

        @Override
        public int getColor(@NotNull BlockState blockState, @Nullable BlockAndTintGetter blockAndTintGetter, @Nullable BlockPos blockPos, int layer) {
            if (layerColor.containsKey(layer)) {
                return layerColor.get(layer).func(blockState, blockAndTintGetter, blockPos).getRGB();
            }
            return -1;
        }
    }

}
