package com.til.dusk.common.register.shaped.shaped_type;


import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
public class ScreenShapedType extends ShapedType {

    public ScreenShapedType() {
        super("screen", () -> ManaLevelBlock.screen);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.IS_CRYSTA)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.crushedPurified).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.damagedCrystal).item(), 1), 0.4)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.crystal).item(), 1), 0.5)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.delicateCrystal).item(), 1), 0.05)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.perfectCrystal).item(), 1), 0.01)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.crystalSeed).item(), 1), 0.3)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.dustTiny).item(), 1), 0.6)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1), 0.2)
                    .runThis(Ore.SCREEN_BYPRODUCT, ore, null)
                    .addMultipleSurplusTime((long) (128 * ore.strength))
                    .addMultipleConsumeMana((long) (4 * ore.consume));
        }
    }
}
