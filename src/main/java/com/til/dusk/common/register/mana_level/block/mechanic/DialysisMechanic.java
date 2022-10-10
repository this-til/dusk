package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.Set;

/**
 * @author til
 */
public class DialysisMechanic extends HandleMechanic {
    public DialysisMechanic(){
        super("dialysis", () -> Set.of(ShapedType.dialysis));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "透析晶体");
        lang.add(LangType.EN_CH, "Dialysis Crystal");
    }

    @Override
    public void defaultConfig() {

    }
}
