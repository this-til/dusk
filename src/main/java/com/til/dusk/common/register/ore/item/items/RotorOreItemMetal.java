package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemMetal;

/**
 * @author til
 */
public class RotorOreItemMetal extends OreItemMetal {

    public RotorOreItemMetal(){
        super("rotor");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "转子");
        lang.add(LangType.EN_CH, "Rotor");
    }
}
