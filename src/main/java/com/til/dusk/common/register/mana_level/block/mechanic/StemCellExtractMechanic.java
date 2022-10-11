package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.Set;

/**
 * @author til
 */
public class StemCellExtractMechanic extends HandleMechanic {
    public StemCellExtractMechanic(){
        super("stem_cell_extract", () -> Set.of(ShapedType.stemCellExtract));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "干细胞提晶体");
        lang.add(LangType.EN_CH, "Stem Cell Extract Crystal");
    }

    @Override
    public void defaultConfig() {
    }
}
