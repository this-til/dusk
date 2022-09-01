package com.til.dusk.common.data;


import com.til.dusk.Dusk;
import com.til.dusk.common.data.shaped.ShapedProvider;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.data.tag.FluidTag;
import com.til.dusk.common.data.tag.ItemTag;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModGatherData {
    public static DataGenerator dataGenerator;

    @SubscribeEvent
    public static void onEvent(GatherDataEvent event) {
        dataGenerator = event.getGenerator();
        BlockTagsProvider blockTagsProvider = new BlockTagsProvider(event.getGenerator(), Dusk.MOD_ID, event.getExistingFileHelper()) {
            @Override
            protected void addTags() {
                for (Map.Entry<TagKey<Block>, List<Block>> entry : BlockTag.getInstance().map.entrySet()) {
                    this.tag(entry.getKey()).add(entry.getValue().toArray(new Block[0]));
                }
            }
        };
        event.getGenerator().addProvider(true, blockTagsProvider);
        event.getGenerator().addProvider(true, new ItemTagsProvider(event.getGenerator(), blockTagsProvider, Dusk.MOD_ID, event.getExistingFileHelper()){
            @Override
            protected void addTags() {
                for (Map.Entry<TagKey<Item>, List<Item>> entry : ItemTag.getInstance().map.entrySet()) {
                    this.tag(entry.getKey()).add(entry.getValue().toArray(new Item[0]));
                }
            }
        });
        event.getGenerator().addProvider(true, new FluidTagsProvider(event.getGenerator(), Dusk.MOD_ID, event.getExistingFileHelper()){
            @Override
            protected void addTags() {
                for (Map.Entry<TagKey<Fluid>, List<Fluid>> entry : FluidTag.getInstance().map.entrySet()) {
                    this.tag(entry.getKey()).add(entry.getValue().toArray(new Fluid[0]));
                }
            }
        });
        event.getGenerator().addProvider(true, new ShapedProvider());
        try {
            event.getGenerator().run();
        } catch (Exception e) {
            Dusk.instance.logger.error("生成数据错误", e);
        }
    }
}
