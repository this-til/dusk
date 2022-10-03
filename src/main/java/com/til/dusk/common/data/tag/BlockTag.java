package com.til.dusk.common.data.tag;

import com.til.dusk.Dusk;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * 标签反射添加
 *
 * @author til
 */
public class BlockTag extends BlockTagsProvider {

    public static final Map<TagKey<Block>, List<Block>> MAP = new HashMap<>();

    public static void addTag(TagKey<Block> tTagKey, Block t) {
        List<Block> tList;
        if (MAP.containsKey(tTagKey)) {
            tList = MAP.get(tTagKey);
        } else {
            tList = new ArrayList<>();
            MAP.put(tTagKey, tList);
        }
        tList.add(t);
    }

    public BlockTag(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, Dusk.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        for (Map.Entry<TagKey<Block>, List<Block>> entry : BlockTag.MAP.entrySet()) {
            this.tag(entry.getKey()).add(entry.getValue().toArray(new Block[0]));
        }
    }
}
