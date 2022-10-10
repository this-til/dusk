package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemMetal;

/**
 * @author til
 */
public class PlateOreItemMetal extends OreItemMetal {

    public PlateOreItemMetal(){
        super("plate");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "板");
        lang.add(LangType.EN_CH, "Plate");
    }
}
