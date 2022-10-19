package com.til.dusk.common.register.mana_level.block.mechanic.handle_mechanic;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.Set;

/**
 * @author til
 */
public class SplittingMechanic extends HandleMechanic {
    public SplittingMechanic(){
        super("splitting", () -> Set.of(ShapedType.splitting));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "裂解晶体");
        lang.add(LangType.EN_CH, "Splitting Crystal");
    }

    @Override
    public void defaultConfig() {

    }
}
