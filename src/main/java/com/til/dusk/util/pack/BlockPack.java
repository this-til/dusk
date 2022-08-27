package com.til.dusk.util.pack;

import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import net.minecraft.tags.BlockTags;
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
