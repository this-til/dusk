package com.til.dusk.common.register.ore.ore.fluid.si_xiang;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;

/**
 * @author til
 */
public class TaiYin extends Ore {

    public TaiYin() {
        super("tai_yin");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        for (LangType value : LangType.values()) {
            lang.add(value, "太阴");
        }
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(45, 45, 45);
        manaLevel = ManaLevel.t1;
        fluidData = new FluidData();
    }
}
