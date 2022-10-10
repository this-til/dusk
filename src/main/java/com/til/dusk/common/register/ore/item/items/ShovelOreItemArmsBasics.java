package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemArmsBasics;

/**
 * @author til
 */
public class ShovelOreItemArmsBasics extends OreItemArmsBasics {

    public ShovelOreItemArmsBasics() {
        super("shovel_basics");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "铲头");
        lang.add(LangType.EN_CH, "Shovel Head");
    }
}
