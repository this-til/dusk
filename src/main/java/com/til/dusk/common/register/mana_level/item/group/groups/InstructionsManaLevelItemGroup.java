package com.til.dusk.common.register.mana_level.item.group.groups;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelCrystalGroup;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.prefab.ColorPrefab;

/**
 * @author til
 */
public class InstructionsManaLevelItemGroup extends ManaLevelCrystalGroup {

    public InstructionsManaLevelItemGroup(){
        super("instructions");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "指令");
        lang.add(LangType.EN_CH, "Instructions");
    }
    @Override
    public void defaultConfig() {
        strokeColor = new DuskColor(244, 233, 215);
        coreColor =  ColorPrefab.CONTROL_TAG;
    }
}
