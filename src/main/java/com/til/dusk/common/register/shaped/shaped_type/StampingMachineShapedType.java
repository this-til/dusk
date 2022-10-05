package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

/**
 * @author til
 */
public class StampingMachineShapedType extends ShapedType {

    public StampingMachineShapedType() {
        super("stamping_machine", () -> ManaLevelBlock.stampingMachine);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate).itemTag(), 2)
                    .addOutItem(new ItemStack(ore.get(OreItem.plate_2).item(), 1), 1D)
                    .addMultipleSurplusTime((long) (ore.strength * 1024L))
                    .addMultipleConsumeMana((long) (ore.consume * 32L));
        }
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(1), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate_2).itemTag(), 2)
                    .addOutItem(new ItemStack(ore.get(OreItem.plate_3).item(), 1), 1D)
                    .addMultipleSurplusTime((long) (ore.strength * 1024L))
                    .addMultipleConsumeMana((long) (ore.consume * 64L));
        }
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(3), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate_3).itemTag(), 2)
                    .addOutItem(new ItemStack(ore.get(OreItem.plate_4).item(), 1), 1D)
                    .addMultipleSurplusTime((long) (ore.strength * 1024L))
                    .addMultipleConsumeMana((long) (ore.consume * 128L));
        }
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(3), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreItem.foil).item(), 4), 1D)
                    .addMultipleSurplusTime((long) (ore.strength * 512L))
                    .addMultipleConsumeMana((long) (ore.consume * 12L));
        }

        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(4), ore.manaLevel)
                    .addInItem(ore.get(OreItem.stick).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreItem.stick_long).item(), 1), 1D)
                    .addMultipleSurplusTime((long) (ore.strength * 512L))
                    .addMultipleConsumeMana((long) (ore.consume * 8L));
        }
    }
}
