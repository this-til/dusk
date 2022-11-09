package com.til.dusk.common.register.ore.ore.fluid;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.prefab.ColorPrefab;

public class GasOre extends Ore {
    public GasOre() {
        super("gas");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "瓦斯");
        lang.add(LangType.EN_CH, "Gas");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(109, 189, 119);
        manaLevel = ManaLevel.t2;
        fluidData = new FluidData();
    }

}
