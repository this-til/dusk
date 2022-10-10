package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemArmsBasics;
import com.til.dusk.common.register.ore.item.OreItemDust;

/**
 * @author til
 */
public class SwordOreItemArmsBasics extends OreItemArmsBasics {

    public SwordOreItemArmsBasics() {
        super("sword_basics");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "剑刃");
        lang.add(LangType.EN_CH, "The blade");
    }
}
