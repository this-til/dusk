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
public class CornflowerblueOre extends Ore {
    public CornflowerblueOre() {
        super("cornflowerblue");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "菊兰");
        lang.add(LangType.EN_CH, "Cornflowerblue");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(OreConfig.COLOR, new DuskColor(100, 149, 237))
                .setConfigOfV(OreConfig.MANA_LEVEL, ManaLevel.t1)
                .setConfig(OreConfig.IS_METAL)
                .setConfig(OreConfig.HAS_DUST)
                .setConfigOfV(OreConfig.IS_LEVEL_ACCEPTABLE, ManaLevel.t1)
                .setConfig(OreConfig.MineralBlockConfig.MINERAL_BLOCK_CONFIG, () -> new ConfigMap()
                        .setConfig(OreConfig.MineralBlockConfig.PLACED_FEATURE, () -> List.of(
                                new IOrePlacedFeatureConfig.OrePlacedFeatureConfig().useDefaultConfig(this, 12, 4))))
                .setConfig(OreConfig.DecorateBlockConfig.DECORATE_BLOCK_CONFIG, ConfigMap::new)
                .setConfig(OreConfig.FluidConfig.FLUID_CONFIG, () -> new ConfigMap()
                        .setConfig(OreConfig.FluidConfig.CAN_COPY));
    }

}

