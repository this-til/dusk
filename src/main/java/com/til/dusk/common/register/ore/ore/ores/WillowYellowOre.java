package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.DecorateBlockData;
import com.til.dusk.common.register.ore.block.MineralBlockData;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;

import java.util.List;

public class WillowYellowOre extends Ore {
    public WillowYellowOre() {
        super("willow_yellow");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "柳黄");
        lang.add(LangType.EN_CH, "Willow Yellow");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(201, 221, 34);
        manaLevel = ManaLevel.t1;
        isCrysta = true;
        hasDust = true;
        isLevelAcceptable = List.of(ManaLevel.t1);
        mineralBlockData = new MineralBlockData()
                .addOrePlacedFeatureConfig(new IOrePlacedFeatureConfig.GenerateData().useDefaultConfig(this, 12, 4));
        decorateBlockData = new DecorateBlockData();
        fluidData = new FluidData()
                .setCanCopy(true);
    }
}

