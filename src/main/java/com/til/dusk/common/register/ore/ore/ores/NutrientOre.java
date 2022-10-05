package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
import com.til.dusk.util.DuskColor;

import java.util.List;

/**
 * @author til
 */
public class NutrientOre extends Ore {
    public NutrientOre() {
        super("nutrient");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "营养液");
        lang.add(LangType.EN_CH, "Nutrient");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(OreConfig.COLOR, new DuskColor(245, 190, 110 ))
                .setConfigOfV(OreConfig.MANA_LEVEL, ManaLevel.t1)
                .setConfig(OreConfig.FluidConfig.FLUID_CONFIG, ConfigMap::new);
    }

}

