package com.til.dusk.common.register.ore.ore.fluid.basics;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author til
 */
public class IdeaOre extends Ore {
    public IdeaOre() {
        super("idea");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "念");
        lang.add(LangType.EN_CH, "Idea");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(249, 228, 190);
        manaLevel = ManaLevel.t2;
        fluidData = new FluidData()
                .setSplitting(new FluidData.SplittingData()
                        .addSunlightSplitting(new IShapedOreConfig.FluidOut(() -> new FluidStack(ideaAirOre.get(OreFluid.solution).source(), 72), 1D))
                        .addMoonlightSplitting(new IShapedOreConfig.FluidOut(() -> new FluidStack(ideaAirOre.get(OreFluid.solution).source(), 72), 1D))
                        .addRainSplitting(new IShapedOreConfig.FluidOut(() -> new FluidStack(ideaAirOre.get(OreFluid.solution).source(), 72), 1D)));
    }

}

