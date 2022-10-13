package com.til.dusk.common.register.mana_level.item.group.groups;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelCrystalGroup;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.prefab.ColorPrefab;

/**
 * @author til
 */
public class DestructionManaLevelItemGroup extends ManaLevelCrystalGroup {

    public DestructionManaLevelItemGroup(){
        super("destruction");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "破坏");
        lang.add(LangType.EN_CH, "Destruction");
    }

    @Override
    public void defaultConfig() {
        strokeColor = new DuskColor(141, 116, 130);
        coreColor =  ColorPrefab.ITEM_IO;
    }
}
