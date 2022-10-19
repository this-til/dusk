package com.til.dusk.common.register.ore.ore.ores.fluid.ba_gua;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;

import java.awt.*;
import java.util.List;

/**
 * @author til
 */
public class DuiOre extends Ore {

    public DuiOre() {
        super("dui");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        for (LangType value : LangType.values()) {
            lang.add(value, "兑");
        }
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(178, 205, 218);
        manaLevel = ManaLevel.t1;
        fluidData = new FluidData();
    }
}
