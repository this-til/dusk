package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreFluid;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author til
 */
public class QualityGenerateShapedType extends ShapedType {

    public QualityGenerateShapedType() {
        super("quality_generate", () -> ManaLevelBlock.qualityGenerate);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.FLUID_DATA)) {
            if (!ore.getSet(Ore.FLUID_DATA).canCopy) {
                continue;
            }
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInFluid(ore.fluidMap.get(OreFluid.joinUUSolution).fluidTag(), 72)
                    .addOutFluid(new FluidStack(ore.fluidMap.get(OreFluid.solution).source(), 144), 1d)
                    .addMultipleSurplusTime(4096L)
                    .addMultipleConsumeMana(64L);
        }
    }
}
