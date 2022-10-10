package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemCrysta;

/**
 * @author til
 */
public class CrystalSeedOreItemCrysta extends OreItemCrysta {

    public CrystalSeedOreItemCrysta() {
        super("crystal_seed");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "晶体种子");
        lang.add(LangType.EN_CH, "Crystal Seed");
    }
}
