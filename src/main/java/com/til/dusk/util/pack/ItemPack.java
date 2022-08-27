package com.til.dusk.util.pack;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public record ItemPack(Item item, TagKey<Item> itemTag) {
}
