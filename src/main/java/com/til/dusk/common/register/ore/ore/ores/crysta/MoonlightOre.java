package com.til.dusk.common.register.ore.ore.ores.crysta;

import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.DecorateBlockData;
import com.til.dusk.common.register.ore.block.MineralBlockData;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.prefab.ColorPrefab;

import java.util.List;

public class MoonlightOre extends Ore {
    public MoonlightOre() {
        super("moonlight");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "月耀");
        lang.add(LangType.EN_CH, "Moonlight");
    }

    @Override
    public void defaultConfig() {
        color = ColorPrefab.MOONLIGHT_COLOR;
        manaLevel = ManaLevel.t1;
        isCrysta = true;
        hasDust = true;
        defaultMana = 8192;
        isLevelAcceptable = List.of(ManaLevel.t1);
        mineralBlockData = new MineralBlockData()
                .addOrePlacedFeatureConfig(new IOrePlacedFeatureConfig.GenerateData().useDefaultConfig(this, 12, 6));
        decorateBlockData = new DecorateBlockData();
        fluidData = new FluidData()
                .setCanCopy(true);
    }
}

