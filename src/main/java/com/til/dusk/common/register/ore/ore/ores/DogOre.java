package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.DecorateBlockData;
import com.til.dusk.common.register.ore.block.MineralBlockData;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class DogOre extends Ore {
    public DogOre() {
        super("dog");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "狗");
        lang.add(LangType.EN_CH, "Dog");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(235, 225, 125);
        manaLevel = ManaLevel.t2;
        isCrysta = true;
        hasDust = true;
        isLevelAcceptable = List.of(ManaLevel.t2);
        mineralBlockData = new MineralBlockData()
                .addOrePlacedFeatureConfig(new IOrePlacedFeatureConfig.GenerateData().useDefaultConfig(this, 12, 4));
        decorateBlockData = new DecorateBlockData();
        fluidData = new FluidData();
        relevantShaped = new Delayed<>(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(this, OreFluid.solution), ShapedType.highPressureFuse, ShapedDrive.get(1), manaLevel)
                        .addInFluid(crystal.get(OreFluid.solution).fluidTag(), 72)
                        .addInFluid(tibetanBlue.get(OreFluid.solution).fluidTag(), 72)
                        .addInFluid(sourceAir.get(OreFluid.solution).fluidTag(), 440)
                        .addOutFluid(new FluidStack(this.get(OreFluid.solution).source(), 144), 1D)
                        .addMultipleSurplusTime((long) (855L * strength))
                        .addMultipleConsumeMana((long) (17L * consume))));
    }
}

