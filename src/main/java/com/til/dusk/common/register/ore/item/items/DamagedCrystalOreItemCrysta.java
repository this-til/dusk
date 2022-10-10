package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemCrysta;

/**
 * @author til
 */
public class DamagedCrystalOreItemCrysta extends OreItemCrysta {

    public DamagedCrystalOreItemCrysta(){
        super("crystal_damaged");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "破碎晶体");
        lang.add(LangType.EN_CH, "Damaged Crystal");
    }
}
