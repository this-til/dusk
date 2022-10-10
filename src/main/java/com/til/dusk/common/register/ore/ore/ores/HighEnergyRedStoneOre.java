package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class HighEnergyRedStoneOre extends Ore {
    public HighEnergyRedStoneOre() {
        super("high_energy_red_stone");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "高能红石");
        lang.add(LangType.EN_CH, "High Energy Red Stone");
    }


    @Override
    public void defaultConfig() {
        color = new DuskColor(245, 35, 35);
        manaLevel = ManaLevel.t1;
        fluidData = new FluidData();
        relevantShaped = new Delayed<>(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(this, OreFluid.solution), ShapedType.highPressureFuse, ShapedDrive.get(0),manaLevel)
                        .addInItem(Tags.Items.DUSTS_REDSTONE, 1)
                        .addInItem(Tags.Items.DUSTS_GLOWSTONE, 1)
                        .addInFluid(crimson.get(OreFluid.solution).fluidTag(), 144)
                        .addOutFluid(new FluidStack(highEnergyRedStone.get(OreFluid.solution).source(), 144), 1d)
                        .addMultipleSurplusTime((long) (1024L * strength))
                        .addMultipleConsumeMana((long) (22L * consume))));
    }

}

