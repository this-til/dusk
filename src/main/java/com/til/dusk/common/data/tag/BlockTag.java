package com.til.dusk.common.data.tag;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.*;

/**
 * 标签反射添加
 *
 * @author til
 */
public class BlockTag  {

    private static BlockTag blockTag;


    public static BlockTag getInstance() {
        if (blockTag == null) {
            blockTag = new BlockTag();
        }
        return blockTag;
    }

    public Map<TagKey<Block>, List<Block>> map = new HashMap<>();

    public void addTag(TagKey<Block> tTagKey, Block t) {
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
