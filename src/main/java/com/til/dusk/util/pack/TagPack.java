package com.til.dusk.util.pack;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public record TagPack(TagKey<Item> itemTagKey,
                      TagKey<Block> blockTagKey,
                      TagKey<Fluid> fluidTagKey) {
}
