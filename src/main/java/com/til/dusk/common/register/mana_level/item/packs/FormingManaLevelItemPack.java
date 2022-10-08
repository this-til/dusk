package com.til.dusk.common.register.mana_level.item.packs;

import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.prefab.ColorPrefab;

/**
 * @author til
 */
public class FormingManaLevelItemPack extends ManaLevelItemPack {

    public FormingManaLevelItemPack(){
        super("forming");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "成型核心");
        lang.add(LangType.EN_CH, "Forming");
    }

    @Override
    public void defaultConfig() {
        strokeColor = new DuskColor(228, 136, 151);
        coreColor =  ColorPrefab.ITEM_IO;
    }
}
