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
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class UUOre extends Ore {
    public UUOre() {
        super("uu");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "UU");
        lang.add(LangType.EN_CH, "UU");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(160, 32, 240);
        manaLevel = ManaLevel.t4;
        fluidData = new FluidData();
        relevantShaped = new Delayed<>(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(this, OreFluid.solution), ShapedType.uuGenerate, ShapedDrive.get(0), manaLevel)
                        .addOutFluid(new FluidStack(uu.get(OreFluid.solution).source(), 1), 1D)
                        .addMultipleSurplusTime((long) (32768L * strength))
                        .addMultipleConsumeMana((long) (12L * consume))));
    }

}

