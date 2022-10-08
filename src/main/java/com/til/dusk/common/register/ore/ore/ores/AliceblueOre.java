package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.DecorateBlockData;
import com.til.dusk.common.register.ore.block.MineralBlockData;
import com.til.dusk.common.register.ore.fluid.FluidData;
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
    public void defaultConfig() {
        color = new DuskColor(240, 248, 255);
        manaLevel = ManaLevel.t1;
        isMetal = true;
        hasDust = true;
        isLevelAcceptable = List.of(ManaLevel.t1);
        mineralBlockData = new MineralBlockData()
                .addOrePlacedFeatureConfig(new IOrePlacedFeatureConfig.GenerateData().useDefaultConfig(this, 12, 4));
        decorateBlockData = new DecorateBlockData();
        fluidData = new FluidData()
                .setCanCopy(true);
    }
}

