package com.til.dusk.common.register.mana_level.block.mechanic.handle_mechanic;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;

/**
 * @author til
 */
public class DialysisMechanic extends HandleMechanic {
    public DialysisMechanic(){
        super("dialysis");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "ιζζΆδ½");
        lang.add(LangType.EN_CH, "Dialysis Crystal");
    }

    @Override
    public void defaultConfig() {
        shapedTypeList = List.of(ShapedType.dialysis);
    }
}
