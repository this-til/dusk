package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.util.ResourceLocationUtil;

import java.util.function.Consumer;

/**
 * @author til
 */
public class HighPressureFuseShapedType extends ShapedType {

    public HighPressureFuseShapedType() {
        super("high_pressure_fuse");
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.ORE.get()) {
            if (ore.fluidData == null) {
                continue;
            }
            if (ore.fluidData.splitting == null) {
                continue;
            }
            FluidData.SplittingData splittingData = ore.fluidData.splitting;
            if (splittingData.sunlightSplitting != null) {
                shapedConsumer.accept(splittingSunlightSolution.create(ore));
            }
            if (splittingData.moonlightSplitting != null) {
                shapedConsumer.accept(splittingMoonlightSolution.create(ore));
            }
            if (splittingData.rainSplitting != null) {
                shapedConsumer.accept(splittingRainSolution.create(ore));
            }
        }
    }

    @Override
    public void defaultConfig() {
        blockTagKey = new Delayed.BlockDelayed(() -> ManaLevelBlock.highPressureFuse.tagPackSupplier.getTagPack().blockTagKey());
        splittingSunlightSolution = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "sunlight"),
                this, ShapedDrive.get(1), 2048L, 22L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.solution.name, 72))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.FluidIn(() -> Ore.sunlight.get(OreFluid.solution).fluidTag(), 72))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidOut(OreFluid.splittingSunlightSolution, 144, 1));
        splittingMoonlightSolution = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "moonlight"),
                this, ShapedDrive.get(1), 2048L, 22L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.solution.name, 72))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.FluidIn(() -> Ore.moonlight.get(OreFluid.solution).fluidTag(), 72))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidOut(OreFluid.splittingMoonlightSolution, 144, 1));
        splittingRainSolution = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "rain"),
                this, ShapedDrive.get(1), 2048L, 22L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.solution.name, 72))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.FluidIn(() -> Ore.rain.get(OreFluid.solution).fluidTag(), 72))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidOut(OreFluid.splittingRainSolution, 144, 1));
    }

    @ConfigField
    public IShapedCreate<Ore> splittingSunlightSolution;

    @ConfigField
    public IShapedCreate<Ore> splittingMoonlightSolution;

    @ConfigField
    public IShapedCreate<Ore> splittingRainSolution;
}
