package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;

public class SourceAirOre extends Ore {
    public SourceAirOre() {
        super("source_air");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "源空");
        lang.add(LangType.EN_CH, "Source Air");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(Ore.COLOR, new DuskColor(240, 66, 243 ))
                .setConfigOfV(Ore.MANA_LEVEL, ManaLevel.t2)
                .setConfig(FluidConfig.FLUID_CONFIG, ConfigMap::new);
    }

}

