package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.IAcceptConfig;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.config.util.IShapedOreConfig;
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
        for (Ore ore : Ore.ORE.get()) {
            if (ore.fluidData == null) {
                continue;
            }
            if (!ore.fluidData.canCopy) {
                continue;
            }
            shapedConsumer.accept(qualityGenerate.create(ore));
        }
    }

    @Override
    public void defaultConfig() {
        qualityGenerate = new IShapedCreate.OreShapedCreate(name, this, ShapedDrive.get(0), 4096L, 64L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.joinUUSolution.name, 72))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidOut(OreFluid.solution, 144, 1));
    }

    @ConfigField
    public IShapedCreate<Ore> qualityGenerate;

}
