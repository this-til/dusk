package com.til.dusk.common.register.mana_level.item.packs;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.prefab.ColorPrefab;

/**
 * @author til
 */
public class PowerManaLevelItemPack extends ManaLevelItemPack {

    public PowerManaLevelItemPack(){
        super("power");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "动力");
        lang.add(LangType.EN_CH, "Power");
    }

    @Override
    public void defaultConfig() {
        strokeColor = new DuskColor(242, 124, 99);
        coreColor =  ColorPrefab.CONTROL_TAG;
    }
}
