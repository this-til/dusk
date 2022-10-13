package com.til.dusk.common.register.mana_level.item.group.groups;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelCrystalGroup;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.prefab.ColorPrefab;

/**
 * @author til
 */
public class SpreadManaLevelItemGroup extends ManaLevelCrystalGroup {

    public SpreadManaLevelItemGroup(){
        super("spread");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "扩散");
        lang.add(LangType.EN_CH, "Spread");
    }

    @Override
    public void defaultConfig() {
        strokeColor = new DuskColor(136, 209, 142);
        coreColor =  ColorPrefab.FLUID_IO;
    }
}
