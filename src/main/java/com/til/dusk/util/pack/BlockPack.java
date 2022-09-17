package com.til.dusk.util.pack;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public record BlockPack(
        Block block,
        TagKey<Block> blockTag,
        BlockItem blockItem,
        TagKey<Item> blockItemTag) {
}
