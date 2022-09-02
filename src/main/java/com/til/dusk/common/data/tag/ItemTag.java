package com.til.dusk.common.data.tag;

import com.til.dusk.Dusk;
import com.til.dusk.util.Extension;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.tags.ITagManager;

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

    public static final IForgeRegistry<Item> ITEMS = ForgeRegistries.ITEMS;
    public static final IForgeRegistry<Block> BLOCKS = ForgeRegistries.BLOCKS;
    public static ITagManager<Item> ITEM_TAG;
    public static ITagManager<Block> BLOCK_TAG;

    public static Map<TagKey<Item>, List<Item>> map = new HashMap<>();

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

    public static TagKey<Item> createTag(Item item) {
        init();
        ResourceLocation itemName = ITEMS.getKey(item);
        assert itemName != null;
        TagKey<Item> itemTagKey = ITEM_TAG.createTagKey(itemName);
        addTag(itemTagKey, item);
        return itemTagKey;
    }

    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> createBlockTag(BlockItem blockItem) {
        init();
        ResourceLocation itemName = ITEMS.getKey(blockItem);
        assert itemName != null;
        TagKey<Item> itemTagKey = ITEM_TAG.createTagKey(itemName);
        Block block = blockItem.getBlock();
        ResourceLocation blockName = BLOCKS.getKey(block);
        assert blockName != null;
        TagKey<Block> blockTagKey = BLOCK_TAG.createTagKey(itemName);
        addTag(itemTagKey, blockItem);
        BlockTag.addTag(blockTagKey, block);
        return new Extension.Data_2<>(itemTagKey, blockTagKey);
    }

    public static void init() {
        if (ITEM_TAG == null) {
            ITEM_TAG = ITEMS.tags();
            assert ITEM_TAG != null;
        }
        if (BLOCK_TAG == null) {
            BLOCK_TAG = BLOCKS.tags();
            assert BLOCK_TAG != null;
        }
    }

    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> TNT;
    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> ICE;
    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> PACKED_ICE;
    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> SNOW_BLOCK;
    public static Extension.Data_2<TagKey<Item>, TagKey<Block>> SLIME_BLOCK;

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

    @SubscribeEvent
    public static void event(NewRegistryEvent event) {
        TNT = createBlockTag((BlockItem) Items.TNT);
        ICE = createBlockTag((BlockItem) Items.ICE);
        PACKED_ICE = createBlockTag((BlockItem) Items.PACKED_ICE);
        SNOW_BLOCK = createBlockTag((BlockItem) Items.SNOW_BLOCK);
        SLIME_BLOCK = createBlockTag((BlockItem) Items.SLIME_BLOCK);
        ENDER_EYE = createTag(Items.ENDER_EYE);
        SNOWBALL = createTag(Items.SNOWBALL);
        POWDER_SNOW_BUCKET = createTag(Items.POWDER_SNOW_BUCKET);
        SLIME_BALL = createTag(Items.SLIME_BALL);
        DRAGON_BREATH = createTag(Items.DRAGON_BREATH);
        POTION = createTag(Items.POTION);
        SPLASH_POTION = createTag(Items.SPLASH_POTION);
        LINGERING_POTION= createTag(Items.LINGERING_POTION);
        POTIONS = ITEM_TAG.createTagKey(new ResourceLocation("potions"));
        {
            addTag(POTIONS, Items.POTION);
            addTag(POTIONS, Items.SPLASH_POTION);
            addTag(POTIONS, Items.LINGERING_POTION);
        }
        BLAZE_POWDER = createTag(Items.BLAZE_POWDER);
    }

}
