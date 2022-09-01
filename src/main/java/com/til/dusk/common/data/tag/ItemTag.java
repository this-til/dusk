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

    private static ItemTag itemTag;

    public static final IForgeRegistry<Item> ITEMS = ForgeRegistries.ITEMS;
    public static final IForgeRegistry<Block> BLOCKS = ForgeRegistries.BLOCKS;
    public static ITagManager<Item> ITEM_TAG;
    public static ITagManager<Block> BLOCK_TAG;


    public static ItemTag getInstance() {
        if (itemTag == null) {
            itemTag = new ItemTag();
        }
        return itemTag;
    }

    public Map<TagKey<Item>, List<Item>> map = new HashMap<>();

    public void addTag(TagKey<Item> tTagKey, Item t) {
        List<Item> tList;
        if (map.containsKey(tTagKey)) {
            tList = map.get(tTagKey);
        } else {
            tList = new ArrayList<>();
            map.put(tTagKey, tList);
        }
        tList.add(t);
    }

    public TagKey<Item> createTag(Item item) {
        init();
        ResourceLocation itemName = ITEMS.getKey(item);
        assert itemName != null;
        TagKey<Item> itemTagKey = ITEM_TAG.createTagKey(itemName);
        addTag(itemTagKey, item);
        return itemTagKey;
    }

    public Extension.Data_2<TagKey<Item>, TagKey<Block>> createBlockTag(BlockItem blockItem) {
        init();
        ResourceLocation itemName = ITEMS.getKey(blockItem);
        assert itemName != null;
        TagKey<Item> itemTagKey = ITEM_TAG.createTagKey(itemName);
        Block block = blockItem.getBlock();
        ResourceLocation blockName = BLOCKS.getKey(block);
        assert blockName != null;
        TagKey<Block> blockTagKey = BLOCK_TAG.createTagKey(itemName);
        addTag(itemTagKey, blockItem);
        BlockTag.getInstance().addTag(blockTagKey, block);
        return new Extension.Data_2<>(itemTagKey, blockTagKey);
    }

    public void init() {
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

    public static TagKey<Item> ENDER_EYE;

    @SubscribeEvent
    public static void event(NewRegistryEvent event) {
        TNT = getInstance().createBlockTag((BlockItem) Items.TNT);
        ENDER_EYE = getInstance().createTag(Items.ENDER_EYE);
    }

}
