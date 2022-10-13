package com.til.dusk.common.register.mana_level.item.group.groups;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelCrystalGroup;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.prefab.ColorPrefab;

/**
 * @author til
 */
public class FormingManaLevelItemGroup extends ManaLevelCrystalGroup {

    public FormingManaLevelItemGroup(){
        super("forming");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "成型");
        lang.add(LangType.EN_CH, "Forming");
    }

    @Override
    public void defaultConfig() {
        strokeColor = new DuskColor(228, 136, 151);
        coreColor =  ColorPrefab.ITEM_IO;
    }
}
