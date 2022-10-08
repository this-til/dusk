package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
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
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(Ore.COLOR, new DuskColor(136, 209, 142))
                .setConfigOfV(Ore.MANA_LEVEL, ManaLevel.t4)
                .setConfig(FluidConfig.FLUID_CONFIG, ConfigMap::new);
    }

}

