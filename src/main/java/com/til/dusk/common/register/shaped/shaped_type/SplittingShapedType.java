package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

/**
 * @author til
 */
public class SplittingShapedType extends ShapedType {

    public SplittingShapedType() {
        super("splitting", () -> ManaLevelBlock.splitting);
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
        splittingSunlightSolution = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), ""}),
                this, ShapedDrive.get(0), 1024L, 12L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.splittingSunlightSolution.name, 144))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.FluidOut(() -> new FluidStack(Ore.sunlight.get(OreFluid.solution).flowing(), 42), 1));
        splittingMoonlightSolution = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), ""}),
                this, ShapedDrive.get(0), 1024L, 12L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.splittingMoonlightSolution.name, 144))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.FluidOut(() -> new FluidStack(Ore.moonlight.get(OreFluid.solution).flowing(), 42), 1));
        splittingRainSolution = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), ""}),
                this, ShapedDrive.get(0), 1024L, 12L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.splittingRainSolution.name, 144))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.FluidOut(() -> new FluidStack(Ore.rain.get(OreFluid.solution).flowing(), 42), 1));
    }

    @ConfigField
    public IShapedCreate<Ore> splittingSunlightSolution;

    @ConfigField
    public IShapedCreate<Ore> splittingMoonlightSolution;

    @ConfigField
    public IShapedCreate<Ore> splittingRainSolution;
}
