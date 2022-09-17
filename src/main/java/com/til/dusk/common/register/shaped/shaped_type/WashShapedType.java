package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
public class WashShapedType extends ShapedType {

    public WashShapedType() {
        super("wash", () -> ManaLevelBlock.wash);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.MINERAL_BLOCK_DATA)) {
            HashMap<ItemStack, Double> outItem = new HashMap<>(1);
            outItem.put(new ItemStack(ore.itemMap.get(OreItem.crushedPurified).item(), 1), 1d);
            if (ore.hasSet(Ore.WASH_BYPRODUCT)) {
                for (Ore ore1 : ore.getSet(Ore.WASH_BYPRODUCT).get()) {
                    outItem.put(new ItemStack(ore1.itemMap.get(OreItem.dustTiny).item(), 1), 0.5);
                }
            }
            new ShapedOre(
                    this,
                    ShapedDrive.get(0),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.crushed).itemTag(), 1),
                    Map.of(FluidTags.WATER, 1000),
                    (long) (ore.strength * 1280L),
                    (long) (ore.consume * 12L),
                    0,
                    outItem,
                    null);
        }
    }

}
