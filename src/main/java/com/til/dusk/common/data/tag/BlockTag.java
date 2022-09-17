package com.til.dusk.common.data.tag;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.*;

/**
 * 标签反射添加
 *
 * @author til
 */
public class BlockTag {

    public static final Map<TagKey<Block>, List<Block>> map = new HashMap<>();

    public static void addTag(TagKey<Block> tTagKey, Block t) {
        List<Block> tList;
        if (map.containsKey(tTagKey)) {
            tList = map.get(tTagKey);
        } else {
            tList = new ArrayList<>();
            map.put(tTagKey, tList);
        }
        tList.add(t);
    }


}
