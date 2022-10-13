package com.til.dusk.common.register.mana_level.item.group.groups;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelCrystalGroup;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.prefab.ColorPrefab;

/**
 * @author til
 */
public class GatherManaLevelItemGroup extends ManaLevelCrystalGroup {

    public GatherManaLevelItemGroup(){
        super("gather");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "聚集");
        lang.add(LangType.EN_CH, "Gather");
    }

    @Override
    public void defaultConfig() {
        strokeColor = new DuskColor(107, 197, 191);
        coreColor =  ColorPrefab.FLUID_IO;
    }
}
