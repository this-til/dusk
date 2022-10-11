package com.til.dusk.common.register.mana_level.item.packs;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
import com.til.dusk.util.DuskColor;

/**
 * @author til
 */
public class OperationBasicsManaLevelItemPack extends ManaLevelItemPack {

    public OperationBasicsManaLevelItemPack() {
        super("operation_basics");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "基体");
        lang.add(LangType.EN_CH, "Basics");
    }

    @Override
    public void defaultConfig() {
        strokeColor = new DuskColor(25, 25, 25);
        coreColor = new DuskColor(25, 25, 25);
    }
}
