package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.DecorateBlockData;
import com.til.dusk.common.register.ore.block.MineralBlockData;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.prefab.ColorPrefab;

import java.util.List;

/**
 * @author til
 */
public class RainOre extends Ore {
    public RainOre() {
        super("rain");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "");
        lang.add(LangType.EN_CH, "RainOre");
    }


    @Override
    public void defaultConfig() {
        color = ColorPrefab.RAIN_COLOR;
        manaLevel = ManaLevel.t1;
        isCrysta = true;
        hasDust = true;
        isLevelAcceptable = List.of(ManaLevel.t1);
        mineralBlockData = new MineralBlockData()
                .addOrePlacedFeatureConfig(new IOrePlacedFeatureConfig.GenerateData().useDefaultConfig(this, 12, 6));
        decorateBlockData = new DecorateBlockData();
        fluidData = new FluidData()
                .setCanCopy(true);
    }

}

