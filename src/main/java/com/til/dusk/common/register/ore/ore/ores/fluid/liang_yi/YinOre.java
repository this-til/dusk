package com.til.dusk.common.register.ore.ore.ores.fluid.liang_yi;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;

public class YinOre extends Ore {

    public YinOre() {
        super("yin");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        for (LangType value : LangType.values()) {
            lang.add(value, "阴仪");
        }
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(35, 35, 35);
        manaLevel = ManaLevel.t1;
        fluidData = new FluidData();
    }
}
