package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

public class FormingShapedType extends ShapedType {

    public FormingShapedType() {
        super("forming", () -> ManaLevelBlock.shaping);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.get(OreItem.ingot).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreItem.plate).item(), 1), 1D)
                    .addMultipleSurplusTime((long) (ore.strength * 1024L))
                    .addMultipleConsumeMana((long) (ore.consume * 16L));
            new ShapedOre(this, ShapedDrive.get(1), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreItem.casing).item(), 1), 1D)
                    .addMultipleSurplusTime((long) (ore.strength * 512L))
                    .addMultipleConsumeMana((long) (ore.consume * 16L));
            new ShapedOre(this, ShapedDrive.get(2), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate).itemTag(), 4)
                    .addOutItem(new ItemStack(ore.get(OreItem.gear).item(), 1), 1D)
                    .addMultipleSurplusTime((long) (ore.strength * 2048L))
                    .addMultipleConsumeMana((long) (ore.consume * 22L));
            new ShapedOre(this, ShapedDrive.get(3), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate).itemTag(), 5)
                    .addOutItem(new ItemStack(ore.get(OreItem.rotor).item(), 1), 1D)
                    .addMultipleSurplusTime((long) (ore.strength * 2048L))
                    .addMultipleConsumeMana((long) (ore.consume * 24L));
            new ShapedOre(this, ShapedDrive.get(4), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate).itemTag(), 12)
                    .addInItem(ore.get(OreItem.gear).itemTag(), 1)
                    .addInItem(ore.get(OreItem.rotor).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreItem.circularSawBlade).item(), 1), 1D)
                    .addMultipleSurplusTime((long) (ore.strength * 4096L))
                    .addMultipleConsumeMana((long) (ore.consume * 24L));
        }

        for (Ore ore : Ore.screen(Ore.ARMS_DATA, Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(5), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate_4).itemTag(), 2)
                    .addInItem(ore.get(OreItem.plate_3).itemTag(), 4)
                    .addInItem(ore.get(OreItem.plate_2).itemTag(), 8)
                    .addOutItem(new ItemStack(ore.get(OreItem.swordBasics).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 16384L * 2L))
                    .addMultipleConsumeMana((long) (ore.consume * 12L));
            new ShapedOre(this, ShapedDrive.get(6), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate_4).itemTag(), 1)
                    .addInItem(ore.get(OreItem.plate_3).itemTag(), 2)
                    .addInItem(ore.get(OreItem.plate_2).itemTag(), 4)
                    .addOutItem(new ItemStack(ore.get(OreItem.shovelBasics).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 16384L))
                    .addMultipleConsumeMana((long) (ore.consume * 12L));
            new ShapedOre(this, ShapedDrive.get(7), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate_4).itemTag(), 3)
                    .addInItem(ore.get(OreItem.plate_3).itemTag(), 6)
                    .addInItem(ore.get(OreItem.plate_2).itemTag(), 12)
                    .addOutItem(new ItemStack(ore.get(OreItem.pickaxeBasics).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 16384L))
                    .addMultipleConsumeMana((long) (ore.consume * 12L));
            new ShapedOre(this, ShapedDrive.get(8), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate_4).itemTag(), 3)
                    .addInItem(ore.get(OreItem.plate_3).itemTag(), 6)
                    .addInItem(ore.get(OreItem.plate_2).itemTag(), 12)
                    .addOutItem(new ItemStack(ore.get(OreItem.axeBasics).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 16384L))
                    .addMultipleConsumeMana((long) (ore.consume * 12L));
            new ShapedOre(this, ShapedDrive.get(9), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate_4).itemTag(), 2)
                    .addInItem(ore.get(OreItem.plate_3).itemTag(), 4)
                    .addInItem(ore.get(OreItem.plate_2).itemTag(), 8)
                    .addOutItem(new ItemStack(ore.get(OreItem.hoeBasics).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 16384L))
                    .addMultipleConsumeMana((long) (ore.consume * 12L));
        }
    }
}
