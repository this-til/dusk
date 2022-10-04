package com.til.dusk.common.register.ore.ores;

import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreFluid;
import com.til.dusk.common.register.ore.block.DecorateBlockData;
import com.til.dusk.common.register.ore.block.MineralBlockData;
import com.til.dusk.util.DuskColor;

/**
 * @author til
 */
public class UltramarineOre extends Ore {
    public UltramarineOre() {
        super("ultramarine", new DuskColor(0, 61, 153), ManaLevel.t1);
        setSet(IS_METAL);
        setSet(HAS_DUST);
        setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1);
        setSet(MINERAL_BLOCK_DATA, () -> new MineralBlockData(this)
                .addCurrencyGenerateData(12, 4));
        setSet(DECORATE_BLOCK_DATA, () -> new DecorateBlockData(this));
        setSet(FLUID_DATA, () -> new OreFluid.FluidData(this)
                .setCanCopy(true));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "群青");
        lang.add(LangType.EN_CH, "Ultramarine");
    }

}
