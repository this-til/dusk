package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;

/**
 * @author til
 */
public class NeuronCellOre extends Ore {
    public NeuronCellOre() {
        super("neuron_cell");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "神经元细胞");
        lang.add(LangType.EN_CH, "Neuron Cell");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(Ore.COLOR, new DuskColor(176, 221, 227))
                .setConfigOfV(Ore.MANA_LEVEL, ManaLevel.t4)
                .setConfig(FluidConfig.FLUID_CONFIG, ConfigMap::new);
    }

}

