package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemMetal;

/**
 * @author til
 */
public class CircularSawBladeOreItemMetal extends OreItemMetal {

    public CircularSawBladeOreItemMetal(){
        super("circular_saw_blade");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "圆锯");
        lang.add(LangType.EN_CH, "Circular Saw Blade");
    }
}
