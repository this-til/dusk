package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author til
 */
public class ManaCoagulationShapedType extends ShapedType {
    public ManaCoagulationShapedType() {
        super("mana_coagulation", () -> ManaLevelBlock.manaCoagulation);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {

    }

    @Override
    public void defaultConfig() {
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(name, this, ShapedDrive.get(0), ManaLevel.t1)
                        .addOutFluid(new FluidStack(Ore.mana.get(OreFluid.solution).source(), 1), 1D)
                        .addMultipleSurplusTime(1024L).addMultipleConsumeMana(32L)
        ));
    }
}
