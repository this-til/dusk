package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemArmsBasics;

/**
 * @author til
 */
public class PickaxeOreItemArmsBasics extends OreItemArmsBasics {

    public PickaxeOreItemArmsBasics() {
        super("pickaxe_basics");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "镐头");
        lang.add(LangType.EN_CH, "Pickaxe Head");
    }
}
