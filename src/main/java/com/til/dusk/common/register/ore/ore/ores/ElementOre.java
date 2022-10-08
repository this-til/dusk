package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author til
 */
public class ElementOre extends Ore {
    public ElementOre() {
        super("element");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "素");
        lang.add(LangType.EN_CH, "Element");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(94, 191, 155);
        manaLevel = ManaLevel.t2;
        fluidData = new FluidData()
                .setSplitting(new FluidData.SplittingData()
                        .addSunlightSplitting(new IShapedOreConfig.FluidOut(new FluidStack(natureAir.get(OreFluid.solution).source(), 72), 1D))
                        .addMoonlightSplitting(new IShapedOreConfig.FluidOut(new FluidStack(natureAir.get(OreFluid.solution).source(), 72), 1D))
                        .addRainSplitting(new IShapedOreConfig.FluidOut(new FluidStack(natureAir.get(OreFluid.solution).source(), 72), 1D)));
    }

}

