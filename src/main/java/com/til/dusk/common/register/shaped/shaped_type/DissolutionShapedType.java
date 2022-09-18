package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreFluid;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraftforge.fluids.FluidStack;

import java.util.Map;

/**
 * @author til
 */
public class DissolutionShapedType extends ShapedType {

    public DissolutionShapedType() {
        super("dissolution", () -> ManaLevelBlock.dissolution);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.FLUID_DATA, Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.ingot).itemTag(), 1)
                    .addOutFluid(new FluidStack(ore.fluidMap.get(OreFluid.solution).source(), 144), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 512))
                    .addMultipleConsumeMana((long) (ore.consume * 128L));
        }
        for (Ore ore : Ore.screen(Ore.FLUID_DATA, Ore.IS_CRYSTA)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.crystal).itemTag(), 1)
                    .addOutFluid(new FluidStack(ore.fluidMap.get(OreFluid.solution).source(), 144), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 512))
                    .addMultipleConsumeMana((long) (ore.consume * 128L));
        }
        for (Ore ore : Ore.screen(Ore.FLUID_DATA)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.dust).itemTag(), 1)
                    .addOutFluid(new FluidStack(ore.fluidMap.get(OreFluid.solution).source(), 144), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 512))
                    .addMultipleConsumeMana((long) (ore.consume * 128L));
        }
    }

}
