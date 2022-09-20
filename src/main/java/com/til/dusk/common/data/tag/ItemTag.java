package com.til.dusk.common.data.tag;

import com.til.dusk.Dusk;
import com.til.dusk.util.Extension;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.NewRegistryEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 标签反射添加
 *
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemTag {

    public static final Map<TagKey<Item>, List<Item>> map = new HashMap<>();

    public static void addTag(TagKey<Item> tTagKey, Item t) {
        List<Item> tList;
        if (map.containsKey(tTagKey)) {
            tList = map.get(tTagKey);
        } else {
            tList = new ArrayList<>();
            map.put(tTagKey, tList);
        }
        tList.add(t);
    }

    public static void addTag(Extension.Data_2<TagKey<Item>, TagKey<Block>> tag, BlockItem blockItem) {
        addTag(tag.d1(), blockItem);
        BlockTag.addTag(tag.d2(), blockItem.getBlock());
    }

    public static TagKey<Item> createTag(Item item) {
        ResourceLocation itemName = ForgeRegistries.ITEMS.getKey(item);
        assert itemName != null;
        TagKey<Item> itemTagKey = Dusk.instance.ITEM_TAG.createTagKey(itemName);
        addTag(itemTagKey, item);
        return itemTagKey;
    }

    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> createBlockTag(BlockItem blockItem) {
        ResourceLocation itemName = ForgeRegistries.ITEMS.getKey(blockItem);
        assert itemName != null;
        TagKey<Item> itemTagKey = Dusk.instance.ITEM_TAG.createTagKey(itemName);
        Block block = blockItem.getBlock();
        ResourceLocation blockName = ForgeRegistries.BLOCKS.getKey(block);
        assert blockName != null;
        TagKey<Block> blockTagKey = Dusk.instance.BLOCK_TAG.createTagKey(itemName);
        addTag(itemTagKey, blockItem);
        BlockTag.addTag(blockTagKey, block);
        return new Extension.Data_2<>(itemTagKey, blockTagKey);
    }

    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> createBlockTag(ResourceLocation resourceLocation) {
        return new Extension.Data_2<>(Dusk.instance.ITEM_TAG.createTagKey(resourceLocation), Dusk.instance.BLOCK_TAG.createTagKey(resourceLocation));
    }

    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> TNT;
    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> ICES;
    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> SNOW_BLOCK;
    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> SLIME_BLOCK;
    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> GRASS;
    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> ENCHANTING_TABLE;
    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> BREWING_STAND;
    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> CRAFTING_TABLE;
    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> REPEATER;
    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> COMPARATOR;
    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> PISTON;


    public static TagKey<Item> ENDER_EYE;
    public static TagKey<Item> SNOWBALL;
    public static TagKey<Item> POWDER_SNOW_BUCKET;
    public static TagKey<Item> SLIME_BALL;
    public static TagKey<Item> DRAGON_BREATH;
    public static TagKey<Item> POTION;
    public static TagKey<Item> SPLASH_POTION;
    public static TagKey<Item> LINGERING_POTION;
    public static TagKey<Item> POTIONS;
    public static TagKey<Item> BLAZE_POWDER;
    public static TagKey<Item> BUCKET;
    public static TagKey<Item> ENCHANTING_BOOK;
    public static TagKey<Item> BREAD;


    @SubscribeEvent
    public static void event(NewRegistryEvent event) {
        TNT = createBlockTag((BlockItem) Items.TNT);
        ICES = createBlockTag(new ResourceLocation("ices"));
        {
            addTag(ICES, (BlockItem) Items.ICE);
            addTag(ICES, (BlockItem) Items.PACKED_ICE);
            addTag(ICES, (BlockItem) Items.BLUE_ICE);
        }
        SNOW_BLOCK = createBlockTag((BlockItem) Items.SNOW_BLOCK);
        SLIME_BLOCK = createBlockTag((BlockItem) Items.SLIME_BLOCK);
        GRASS = createBlockTag((BlockItem) Items.GRASS);
        ENCHANTING_TABLE = createBlockTag((BlockItem) Items.ENCHANTING_TABLE);
        BREWING_STAND = createBlockTag((BlockItem) Items.BREWING_STAND);
        CRAFTING_TABLE = createBlockTag((BlockItem) Items.CRAFTING_TABLE);
        REPEATER = createBlockTag((BlockItem) Items.REPEATER);
        COMPARATOR = createBlockTag((BlockItem) Items.COMPARATOR);
        PISTON = createBlockTag((BlockItem) Items.PISTON);

        ENDER_EYE = createTag(Items.ENDER_EYE);
        SNOWBALL = createTag(Items.SNOWBALL);
        POWDER_SNOW_BUCKET = createTag(Items.POWDER_SNOW_BUCKET);
        SLIME_BALL = createTag(Items.SLIME_BALL);
        DRAGON_BREATH = createTag(Items.DRAGON_BREATH);
        POTION = createTag(Items.POTION);
        SPLASH_POTION = createTag(Items.SPLASH_POTION);
        LINGERING_POTION = createTag(Items.LINGERING_POTION);
        POTIONS = Dusk.instance.ITEM_TAG.createTagKey(new ResourceLocation("potions"));
        {
            addTag(POTIONS, Items.POTION);
            addTag(POTIONS, Items.SPLASH_POTION);
            addTag(POTIONS, Items.LINGERING_POTION);
        }
        BLAZE_POWDER = createTag(Items.BLAZE_POWDER);
        BUCKET = createTag(Items.BUCKET);
        ENCHANTING_BOOK = createTag(Items.ENCHANTED_BOOK);
        BREAD = createTag(Items.BREAD);
    }

}
