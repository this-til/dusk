package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemCrysta;

/**
 * @author til
 */
public class CrystalDelicateOreItemCrysta extends OreItemCrysta {

    public CrystalDelicateOreItemCrysta(){
        super("crystal_delicate");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "精致晶体");
        lang.add(LangType.EN_CH, "Delicate Crystal");
    }
}
