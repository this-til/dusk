package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public class FormingShapedType extends ShapedType {

    public FormingShapedType() {
        super("forming", () -> ManaLevelBlock.shaping);
    }

    @Override
    public void registerShaped() {

        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.ingot).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.plate).item(), 1), 1D)
                    .addMultipleSurplusTime((long) (ore.strength * 1024L))
                    .addMultipleConsumeMana((long) (ore.consume * 16L));
            new ShapedOre(this, ShapedDrive.get(1), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.plate).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.casing).item(), 1), 1D)
                    .addMultipleSurplusTime((long) (ore.strength * 512L))
                    .addMultipleConsumeMana((long) (ore.consume * 16L));
            new ShapedOre(this, ShapedDrive.get(2), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.plate).itemTag(), 4)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.gear).item(), 1), 1D)
                    .addMultipleSurplusTime((long) (ore.strength * 2048L))
                    .addMultipleConsumeMana((long) (ore.consume * 22L));
            new ShapedOre(this, ShapedDrive.get(3), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.plate).itemTag(), 5)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.rotor).item(), 1), 1D)
                    .addMultipleSurplusTime((long) (ore.strength * 2048L))
                    .addMultipleConsumeMana((long) (ore.consume * 24L));
        }

        for (Ore ore : Ore.screen(Ore.ARMS_DATA)) {
            new ShapedOre(this, ShapedDrive.get(4), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.plate_4).itemTag(), 2)
                    .addInItem(ore.itemMap.get(OreItem.plate_3).itemTag(), 4)
                    .addInItem(ore.itemMap.get(OreItem.plate_2).itemTag(), 8)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.swordBasics).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 16384L * 2L))
                    .addMultipleConsumeMana((long) (ore.consume * 12L));
            new ShapedOre(this, ShapedDrive.get(5), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.plate_4).itemTag(), 1)
                    .addInItem(ore.itemMap.get(OreItem.plate_3).itemTag(), 2)
                    .addInItem(ore.itemMap.get(OreItem.plate_2).itemTag(), 4)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.shovelBasics).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 16384L))
                    .addMultipleConsumeMana((long) (ore.consume * 12L));
            new ShapedOre(this, ShapedDrive.get(6), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.plate_4).itemTag(), 3)
                    .addInItem(ore.itemMap.get(OreItem.plate_3).itemTag(), 6)
                    .addInItem(ore.itemMap.get(OreItem.plate_2).itemTag(), 12)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.pickaxeBasics).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 16384L))
                    .addMultipleConsumeMana((long) (ore.consume * 12L));
            new ShapedOre(this, ShapedDrive.get(7), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.plate_4).itemTag(), 3)
                    .addInItem(ore.itemMap.get(OreItem.plate_3).itemTag(), 6)
                    .addInItem(ore.itemMap.get(OreItem.plate_2).itemTag(), 12)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.axeBasics).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 16384L))
                    .addMultipleConsumeMana((long) (ore.consume * 12L));
            new ShapedOre(this, ShapedDrive.get(8), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.plate_4).itemTag(), 2)
                    .addInItem(ore.itemMap.get(OreItem.plate_3).itemTag(), 4)
                    .addInItem(ore.itemMap.get(OreItem.plate_2).itemTag(), 8)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.hoeBasics).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 16384L))
                    .addMultipleConsumeMana((long) (ore.consume * 12L));
        }
    }
}
