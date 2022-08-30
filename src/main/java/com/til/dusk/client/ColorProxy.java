package com.til.dusk.client;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.ManaLevelFluid;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import com.til.dusk.common.register.ore.OreFluid;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.world.ModBlock;
import com.til.dusk.common.world.ModItem;
import com.til.dusk.util.Extension;
import com.til.dusk.util.pack.BlockPack;
import com.til.dusk.util.pack.FluidPack;
import com.til.dusk.util.pack.ItemPack;
import com.til.dusk.util.prefab.ColorPrefab;
import com.til.dusk.util.tag_tool.TagTool;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.HashMap;
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
            for (Map.Entry<OreItem, ItemPack> entry : ore.itemMap.entrySet()) {
                ItemColorPack itemColorPack = new ItemColorPack(entry.getValue().item());
                ITEM_COLOR_PACK_MAP.put(entry.getValue().item(), itemColorPack);
                entry.getKey().dyeBlack(ore, itemColorPack);
            }
            for (Map.Entry<OreBlock, BlockPack> entry : ore.blockMap.entrySet()) {
                ItemColorPack itemColorPack = new ItemColorPack(entry.getValue().blockItem());
                ITEM_COLOR_PACK_MAP.put(entry.getValue().blockItem(), itemColorPack);
                entry.getKey().dyeBlack(ore, itemColorPack);
            }
            for (Map.Entry<OreFluid, FluidPack> entry : ore.fluidMap.entrySet()) {
                if (entry.getValue().bucketItem() != null) {
                    ItemColorPack itemColorPack = new ItemColorPack(entry.getValue().bucketItem());
                    ITEM_COLOR_PACK_MAP.put(entry.getValue().bucketItem(), itemColorPack);
                }
            }
        }
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            for (Map.Entry<ManaLevelItem, ItemPack> entry : manaLevel.itemMap.entrySet()) {
                ItemColorPack itemColorPack = new ItemColorPack(entry.getValue().item());
                ITEM_COLOR_PACK_MAP.put(entry.getValue().item(), itemColorPack);
                entry.getKey().dyeBlack(manaLevel, itemColorPack);
            }
            for (Map.Entry<ManaLevelBlock, BlockPack> entry : manaLevel.blockMap.entrySet()) {
                ItemColorPack itemColorPack = new ItemColorPack(entry.getValue().blockItem());
                ITEM_COLOR_PACK_MAP.put(entry.getValue().blockItem(), itemColorPack);
                entry.getKey().dyeBlack(manaLevel, itemColorPack);
            }
            for (Map.Entry<ManaLevelFluid, FluidPack> entry : manaLevel.fluidMap.entrySet()) {
                if (entry.getValue().bucketItem() != null) {
                    ItemColorPack itemColorPack = new ItemColorPack(entry.getValue().bucketItem());
                    ITEM_COLOR_PACK_MAP.put(entry.getValue().bucketItem(), itemColorPack);
                }
            }
        }
        for (RegistryObject<Item> entry : ModItem.ITEMS.getEntries()) {
            ITEM_COLOR_PACK_MAP.put(entry.get(), new ItemColorPack(entry.get()));
        }
        for (RegistryObject<Block> entry : ModBlock.BLOCKS.getEntries()) {
            BLOCK_COLOR_PACK_MAP.put(entry.get(), new BlockColorPack(entry.get()));
        }
        for (Map.Entry<Item, ItemColorPack> itemItemColorPackEntry : ITEM_COLOR_PACK_MAP.entrySet()) {
            event.register(itemItemColorPackEntry.getValue(), itemItemColorPackEntry.getKey());
        }
        ITEM_COLOR_PACK_MAP.get(ModItem.BIND_STAFF.get()).addClock(0, itemStack -> {
            CompoundTag compoundTag = itemStack.getTag();
            if (compoundTag == null) {
                return new Color(-1);
            }
            return new Color(TagTool.colorTag.get(compoundTag));
        });
    }

    @SubscribeEvent
    public static void blockColor(RegisterColorHandlersEvent.Block event) {
        for (Ore ore : Ore.ORE.get()) {
            for (Map.Entry<OreBlock, BlockPack> entry : ore.blockMap.entrySet()) {
                BlockColorPack blockColorPack = new BlockColorPack(entry.getValue().block());
                BLOCK_COLOR_PACK_MAP.put(entry.getValue().block(), blockColorPack);
                entry.getKey().dyeBlack(ore, blockColorPack);
            }
            for (Map.Entry<OreFluid, FluidPack> entry : ore.fluidMap.entrySet()) {
                if (entry.getValue().liquidBlock() != null) {
                    BlockColorPack blockColorPack = new BlockColorPack(entry.getValue().liquidBlock());
                    BLOCK_COLOR_PACK_MAP.put(entry.getValue().liquidBlock(), blockColorPack);
                }
            }
        }
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            for (Map.Entry<ManaLevelBlock, BlockPack> entry : manaLevel.blockMap.entrySet()) {
                BlockColorPack blockColorPack = new BlockColorPack(entry.getValue().block());
                BLOCK_COLOR_PACK_MAP.put(entry.getValue().block(), blockColorPack);
                entry.getKey().dyeBlack(manaLevel, blockColorPack);
            }
            for (Map.Entry<ManaLevelFluid, FluidPack> entry : manaLevel.fluidMap.entrySet()) {
                if (entry.getValue().liquidBlock() != null) {
                    BlockColorPack blockColorPack = new BlockColorPack(entry.getValue().liquidBlock());
                    BLOCK_COLOR_PACK_MAP.put(entry.getValue().liquidBlock(), blockColorPack);
                }
            }
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
