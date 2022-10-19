package com.til.dusk.common.register.ore.ore.fluid.cell;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;

public class SupportCellOre extends Ore {
    public SupportCellOre() {
        super("support_cell");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "供养细胞");
        lang.add(LangType.EN_CH, "Support Cell");
    }
    @Override
    public void defaultConfig() {
        color = new DuskColor(136, 209, 142);
        manaLevel = ManaLevel.t4;
        fluidData = new FluidData();
    }

}

