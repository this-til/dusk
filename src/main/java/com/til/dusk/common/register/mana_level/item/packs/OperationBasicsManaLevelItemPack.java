package com.til.dusk.common.register.mana_level.item.packs;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
import com.til.dusk.util.DuskColor;

/**
 * @author til
 */
public class OperationBasicsManaLevelItemPack extends ManaLevelItemPack {

    public OperationBasicsManaLevelItemPack(){
        super("operation_basics");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "运算基体");
        lang.add(LangType.EN_CH, "Operation Basics");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(STROKE_COLOR, new DuskColor(25, 25, 25))
                .setConfigOfV(CORE_COLOR, new DuskColor(25, 25, 25));
    }
}
