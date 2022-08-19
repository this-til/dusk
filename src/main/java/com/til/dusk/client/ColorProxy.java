package com.til.dusk.client;

import com.til.dusk.Dusk;
import com.til.dusk.register.ManaLevel;
import com.til.dusk.register.Ore;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author til
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ColorProxy {
    @SubscribeEvent
    public static void itemColor(RegisterColorHandlersEvent.Item event) {
        for (Ore ore : Ore.ORE.get()) {
            List<ItemLike> likes = new ArrayList<>(ore.itemMap.size());
            for (Item value : ore.itemMap.values()) {
                likes.add(() -> value);
            }
            event.register((itemStack, l) -> l == 0 ? ore.color.getRGB() : -1, likes.toArray(new ItemLike[0]));
            List<ItemLike> itemBlockList = new ArrayList<>(ore.blockMap.size());
            for (Item value : ore.blockMap.values()) {
                itemBlockList.add(() -> value);
            }
            event.register((itemStack, l) -> l == 1 ? ore.color.getRGB() : -1, itemBlockList.toArray(new ItemLike[0]));
        }
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            List<ItemLike> likes = new ArrayList<>(manaLevel.itemMap.size());
            for (Item value : manaLevel.itemMap.values()) {
                likes.add(() -> value);
            }
            event.register((itemStack, l) -> l == 0 ? manaLevel.color.getRGB() : -1, likes.toArray(new ItemLike[0]));
            List<ItemLike> itemBlockList = new ArrayList<>(manaLevel.blockMap.size());
            for (Item value : manaLevel.blockMap.values()) {
                itemBlockList.add(() -> value);
            }
            event.register((itemStack, l) -> l == 1 ? manaLevel.color.getRGB() : -1, itemBlockList.toArray(new ItemLike[0]));
        }
    }

    @SubscribeEvent
    public static void blockColor(RegisterColorHandlersEvent.Block event) {
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
            event.register((blockState, blockAndTintGetter, blockPos, l) -> l == 1 ? manaLevel.color.getRGB() : -1, blocks.toArray(new Block[0]));
        }
    }

}
