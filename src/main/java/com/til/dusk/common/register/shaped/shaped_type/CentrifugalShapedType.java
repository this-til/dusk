package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
public class CentrifugalShapedType extends ShapedType {

    public CentrifugalShapedType() {
        super("centrifugal", () -> ManaLevelBlock.centrifugal);
    }

    @Override
    public void registerSubsidiaryBlack() {
        for (Ore ore : Ore.screen(Ore.HAS_MINERAL_BLOCK)) {
            HashMap<ItemStack, Double> outItem = new HashMap<>(1);
            outItem.put(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1), 1d);
            if (ore.hasSet(Ore.CENTRIFUGE_BYPRODUCT)) {
                for (Ore ore1 : ore.getSet(Ore.CENTRIFUGE_BYPRODUCT).get()) {
                    outItem.put(new ItemStack(ore1.itemMap.get(OreItem.dustTiny).item(), 1), 0.5);
                }
            }
            new ShapedOre(
                    this,
                    ShapedDrive.get(0),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.crushedPurified).itemTag(), 1),
                    null,
                    (long) (ore.strength * 1280L),
                    (long) (ore.consume * 48L),
                    0,
                    outItem,
                    null);
        }
    }

}
