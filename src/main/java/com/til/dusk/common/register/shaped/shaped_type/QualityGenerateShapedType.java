package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

/**
 * @author til
 */
public class QualityGenerateShapedType extends ShapedType {

    public QualityGenerateShapedType() {
        super("quality_generate", () -> ManaLevelBlock.qualityGenerate);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.screen(Ore.FLUID_DATA)) {
            if (!ore.getSet(Ore.FLUID_DATA).canCopy) {
                continue;
            }
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInFluid(ore.get(OreFluid.joinUUSolution).fluidTag(), 72)
                    .addOutFluid(new FluidStack(ore.get(OreFluid.solution).source(), 144), 1d)
                    .addMultipleSurplusTime(4096L)
                    .addMultipleConsumeMana(64L);
        }
    }
}
