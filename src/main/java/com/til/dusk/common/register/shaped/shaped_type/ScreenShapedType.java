package com.til.dusk.common.register.shaped.shaped_type;


import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ScreenShapedType extends ShapedType {

    public ScreenShapedType() {
        super("screen", () -> ManaLevelBlock.screen);
    }

    @Override
    public void registerSubsidiaryBlack() {
        for (Ore ore : Ore.screen(Ore.IS_CRYSTA)) {
            Map<ItemStack, Double> out = new HashMap<>();
            out.put(new ItemStack(ore.itemMap.get(OreItem.damagedCrystal).item(), 1), 0.4);
            out.put(new ItemStack(ore.itemMap.get(OreItem.crystal).item(), 1), 0.2);
            out.put(new ItemStack(ore.itemMap.get(OreItem.delicateCrystal).item(), 1), 0.01);
            out.put(new ItemStack(ore.itemMap.get(OreItem.perfectCrystal).item(), 1), 0.005);
            out.put(new ItemStack(ore.itemMap.get(OreItem.crystalSeed).item(), 1), 0.3);

            out.put(new ItemStack(ore.itemMap.get(OreItem.dustTiny).item(), 1), 0.6);
            out.put(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1), 0.2);

            if (ore.hasSet(Ore.SCREEN_BYPRODUCT)) {
                for (Ore ore1 : ore.getSet(Ore.SCREEN_BYPRODUCT).get()) {
                    out.put(new ItemStack(ore1.itemMap.get(OreItem.dustTiny).item(), 1), 0.5);
                }
            }
            new ShapedOre(
                    this,
                    ShapedDrive.get(0),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.crushedPurified).itemTag(), 1),
                    null,
                    (long) (ore.strength * 512),
                    (long) (ore.consume * 16L),
                    0,
                    out,
                    null);
        }
    }
}
