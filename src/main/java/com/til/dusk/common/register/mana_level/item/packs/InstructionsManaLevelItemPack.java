package com.til.dusk.common.register.mana_level.item.packs;

import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.prefab.ColorPrefab;

/**
 * @author til
 */
public class InstructionsManaLevelItemPack extends ManaLevelItemPack {

    public InstructionsManaLevelItemPack(){
        super("instructions");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "指令核心");
        lang.add(LangType.EN_CH, "Instructions");
    }
    @Override
    public void defaultConfig() {
        strokeColor = new DuskColor(244, 233, 215);
        coreColor =  ColorPrefab.CONTROL_TAG;
    }
}
