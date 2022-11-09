package com.til.dusk.common.register.ore.ore.fluid.ba_gua;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;

import java.awt.*;

/**
 * @author til
 */
public class GenOre extends BaGuaBasicsOre {

    public GenOre() {
        super("gen");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        for (LangType value : LangType.values()) {
            lang.add(value, "艮");
        }
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(122, 122, 0);
        manaLevel = ManaLevel.t1;
        fluidData = new FluidData();
    }
}
