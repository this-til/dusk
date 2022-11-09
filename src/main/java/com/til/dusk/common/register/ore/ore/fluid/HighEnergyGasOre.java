package com.til.dusk.common.register.ore.ore.fluid;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.DuskColor;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class HighEnergyGasOre extends Ore {
    public HighEnergyGasOre() {
        super("high_energy_gas");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "高能瓦斯");
        lang.add(LangType.EN_CH, "High Energy Gas");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(163, 225, 196);
        manaLevel = ManaLevel.t3;
        fluidData = new FluidData();
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(name, ShapedType.highPressureFuse, ShapedDrive.get(0), manaLevel)
                        .addInFluid(gas.get(OreFluid.solution).fluidTag(), 72)
                        .addInFluid(highEnergyRedStone.get(OreFluid.solution).fluidTag(), 36)
                        .addInFluid(dissolutionMana.get(OreFluid.solution).fluidTag(), 36)
                        .addOutFluid(new FluidStack(get(OreFluid.solution).source(), 144), 1D))
        );
    }

}
