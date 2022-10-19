package com.til.dusk.common.register.ore.ore.fluid.liang_yi;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;

public class YangOre extends Ore {

    public YangOre() {
        super("yang");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        for (LangType value : LangType.values()) {
            lang.add(value, "阳仪");
        }
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(255, 255, 255);
        manaLevel = ManaLevel.t1;
        fluidData = new FluidData();
    }
}
