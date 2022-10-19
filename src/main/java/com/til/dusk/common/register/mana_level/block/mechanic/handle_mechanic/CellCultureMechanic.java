package com.til.dusk.common.register.mana_level.block.mechanic.handle_mechanic;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.Set;

/**
 * @author til
 */
public class CellCultureMechanic extends HandleMechanic {
    public CellCultureMechanic(){
        super("cell_culture", () -> Set.of(ShapedType.cellCulture));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "细胞培养晶体");
        lang.add(LangType.EN_CH, "Cell Culture Crystal");
    }

    @Override
    public void defaultConfig() {

    }
}
