package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.prefab.ColorPrefab;

public class ManaOre extends Ore {
    public ManaOre() {
        super("mana");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "灵气");
        lang.add(LangType.EN_CH, "Mana");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(Ore.COLOR,  ColorPrefab.MANA_IO)
                .setConfigOfV(Ore.MANA_LEVEL, ManaLevel.t1)
                .setConfig(FluidConfig.FLUID_CONFIG, ConfigMap::new);
    }

}

