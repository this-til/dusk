package com.til.dusk.common.register.mana_level.block.mechanic.handle_mechanic;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.Set;

/**
 * @author til
 */
public class UUGenerateMechanic extends HandleMechanic {
    public UUGenerateMechanic(){
        super("uu_generate", () -> Set.of(ShapedType.uuGenerate));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "UU生成晶体");
        lang.add(LangType.EN_CH, "UU Generate Crystal");
    }

    @Override
    public void defaultConfig() {

    }
}
