package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemArmsBasics;

/**
 * @author til
 */
public class AxeOreItemArmsBasics extends OreItemArmsBasics {

    public AxeOreItemArmsBasics() {
        super("axe_basics");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "斧头");
        lang.add(LangType.EN_CH, "Axe Head");
    }
}
