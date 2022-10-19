package com.til.dusk.common.register.ore.ore.ores.fluid.si_xiang;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;

/**
 * @author til
 */
public class SaoYang extends Ore {

    public SaoYang() {
        super("sao_yang");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        for (LangType value : LangType.values()) {
            lang.add(value, "少阳");
        }
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(175, 175, 175);
        manaLevel = ManaLevel.t1;
        fluidData = new FluidData();
    }
}
