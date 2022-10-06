package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
import com.til.dusk.util.DuskColor;

public class SarconectinOre extends Ore {
    public SarconectinOre() {
        super("sarconectin");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "肌联蛋白");
        lang.add(LangType.EN_CH, "Sarconectin");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(OreConfig.COLOR, new DuskColor(148, 73, 56))
                .setConfigOfV(OreConfig.MANA_LEVEL, ManaLevel.t4)
                .setConfig(OreConfig.FluidConfig.FLUID_CONFIG, ConfigMap::new);
    }

}

