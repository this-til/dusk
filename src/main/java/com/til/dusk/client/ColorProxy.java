package com.til.dusk.client;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.world.ModItem;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author til
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ColorProxy {
    @SubscribeEvent
    public static void itemColor(RegisterColorHandlersEvent.Item event) {
        List<ItemLike> sunlightList = new ArrayList<>();
        List<ItemLike> moonlightList = new ArrayList<>();
        for (Ore ore : Ore.ORE.get()) {
            event.register((itemStack, l) -> l == 0 ? ore.color.getRGB() : -1, ore.itemMap.values().toArray(new ItemLike[0]));
            event.register((itemStack, l) -> l == 1 ? ore.color.getRGB() : -1, ore.blockMap.values().toArray(new ItemLike[0]));
        }
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            event.register((itemStack, l) -> l == 0 ? manaLevel.color.getRGB() : -1, manaLevel.itemMap.values().toArray(new ItemLike[0]));
            event.register((itemStack, l) -> l == 0 ? manaLevel.color.getRGB() : -1, manaLevel.blockMap.values().toArray(new ItemLike[0]));
            sunlightList.add(manaLevel.blockMap.get(ManaLevelBlock.sunlight));
            moonlightList.add(manaLevel.blockMap.get(ManaLevelBlock.moonlight));
        }
        event.register((itemStack, l) -> l == 1 ? ColorPrefab.SUNLIGHT_COLOR.getRGB() : -1, sunlightList.toArray(new ItemLike[0]));
        event.register((itemStack, l) -> l == 1 ? ColorPrefab.MOONLIGHT_COLOR.getRGB() : -1, moonlightList.toArray(new ItemLike[0]));
    }

    @SubscribeEvent
    public static void blockColor(RegisterColorHandlersEvent.Block event) {
        List<Block> sunlightList = new ArrayList<>();
        List<Block> moonlightList = new ArrayList<>();
        for (Ore ore : Ore.ORE.get()) {
            List<Block> blocks = new ArrayList<>(ore.blockMap.size());
            for (BlockItem value : ore.blockMap.values()) {
                blocks.add(value.getBlock());
            }
            event.register((blockState, blockAndTintGetter, blockPos, l) -> l == 1 ? ore.color.getRGB() : -1, blocks.toArray(new Block[0]));
        }
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            List<Block> blocks = new ArrayList<>(manaLevel.blockMap.size());
            for (BlockItem value : manaLevel.blockMap.values()) {
                blocks.add(value.getBlock());
            }
            event.register((blockState, blockAndTintGetter, blockPos, l) -> l == 0 ? manaLevel.color.getRGB() : -1, blocks.toArray(new Block[0]));
            sunlightList.add(manaLevel.blockMap.get(ManaLevelBlock.sunlight).getBlock());
            moonlightList.add(manaLevel.blockMap.get(ManaLevelBlock.moonlight).getBlock());
        }
        event.register((blockState, blockAndTintGetter, blockPos, l) -> l == 1 ? ColorPrefab.SUNLIGHT_COLOR.getRGB() : -1, sunlightList.toArray(new Block[0]));
        event.register((blockState, blockAndTintGetter, blockPos, l) -> l == 1 ? ColorPrefab.MOONLIGHT_COLOR.getRGB() : -1, moonlightList.toArray(new Block[0]));
    }

}
