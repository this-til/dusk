package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;

import java.util.function.Consumer;

/**
 * @author til
 */
public class DialysisShapedType extends ShapedType {

    public DialysisShapedType() {
        super("dialysis", () -> ManaLevelBlock.dialysis);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.ORE.get()) {
            if (ore.fluidData == null) {
                continue;
            }
            if (!ore.fluidData.canCopy) {
                continue;
            }
            shapedConsumer.accept(dialysis.create(ore));
        }
    }

    @Override
    public void defaultConfig() {
        dialysis = new IShapedCreate.OreShapedCreate(name, this, ShapedDrive.get(0), 4096L, 44L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.solution.name, 72))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.FluidIn(() -> Ore.uu.get(OreFluid.solution).fluidTag(), 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidOut(OreFluid.joinUUSolution, 72, 1d));
    }

    @ConfigField
    public IShapedCreate<Ore> dialysis;
}
