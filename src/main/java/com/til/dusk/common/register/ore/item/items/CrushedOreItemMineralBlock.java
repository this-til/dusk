package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemMineralBlock;

/**
 * @author til
 */
public class CrushedOreItemMineralBlock extends OreItemMineralBlock {

    public CrushedOreItemMineralBlock() {
        super("crushed");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "粉碎矿物");
        lang.add(LangType.EN_CH, "Crushed");
    }
}
