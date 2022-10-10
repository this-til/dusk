package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.Set;

/**
 * @author til
 */
public class QualityGenerateMechanic extends HandleMechanic {
    public QualityGenerateMechanic(){
        super("quality_generate", () -> Set.of(ShapedType.qualityGenerate));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "质量生成晶体");
        lang.add(LangType.EN_CH, "Quality Generate Crystal");
    }

    @Override
    public void defaultConfig() {

    }
}
