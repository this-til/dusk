package com.til.dusk.common.register.mana_level.item.packs;

import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
import com.til.dusk.util.prefab.ColorPrefab;

/**
 * @author til
 */
public class OperationManaLevelItemPack extends ManaLevelItemPack {

    public OperationManaLevelItemPack(){
        super("operation");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "运算");
        lang.add(LangType.EN_CH, "Operation");
    }

    @Override
    public void defaultConfig() {
        strokeColor =  ColorPrefab.MANA_IO;
        coreColor =  ColorPrefab.MANA_IO;
    }
}