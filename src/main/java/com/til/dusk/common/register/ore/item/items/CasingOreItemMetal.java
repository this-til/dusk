package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemMetal;

/**
 * @author til
 */
public class CasingOreItemMetal extends OreItemMetal {

    public CasingOreItemMetal(){
        super("casing");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "外壳");
        lang.add(LangType.EN_CH, "Casing");
    }
}
