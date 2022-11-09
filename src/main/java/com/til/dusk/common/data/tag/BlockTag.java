package com.til.dusk.common.data.tag;

import com.til.dusk.Dusk;
import com.til.dusk.common.event.RegisterManageEvent;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

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

    public static TagKey<Block> AIR;

    @SubscribeEvent
    public static void event(RegisterManageEvent.InMap event) {
        AIR = Dusk.instance.BLOCK_TAG.createTagKey(new ResourceLocation("air"));
    }
}
