package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;

public class PerceptionCellOre extends Ore {
    public PerceptionCellOre() {
        super("perception_cell");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "感知细胞");
        lang.add(LangType.EN_CH, "PerceptionCellOre");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(159, 105, 156);
        manaLevel = ManaLevel.t4;
        fluidData = new FluidData();
    }

}

