package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;

import java.util.List;

/**
 * @author til
 */
public class AliceblueOre extends Ore {
    public AliceblueOre() {
        super("aliceblue");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "艾利斯兰");
        lang.add(LangType.EN_CH, "Aliceblue");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(Ore.COLOR, new DuskColor(240, 248, 255))
                .setConfigOfV(Ore.MANA_LEVEL, ManaLevel.t1)
                .setConfig(Ore.IS_METAL)
                .setConfig(Ore.HAS_DUST)
                .setConfigOfV(Ore.IS_LEVEL_ACCEPTABLE, ManaLevel.t1)
                .setConfig(MineralBlockConfig.MINERAL_BLOCK_CONFIG, () -> new ConfigMap()
                        .setConfig(MineralBlockConfig.PLACED_FEATURE, () -> List.of(
                                new IOrePlacedFeatureConfig.OrePlacedFeatureConfig().useDefaultConfig(this, 12, 4))))
                .setConfig(DecorateBlockConfig.DECORATE_BLOCK_CONFIG, ConfigMap::new)
                .setConfig(FluidConfig.FLUID_CONFIG, () -> new ConfigMap()
                        .setConfig(FluidConfig.CAN_COPY));
    }

}

