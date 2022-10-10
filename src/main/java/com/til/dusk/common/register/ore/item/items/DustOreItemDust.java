package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemDust;

/**
 * @author til
 */
public class DustOreItemDust extends OreItemDust {

    public DustOreItemDust() {
        super("dust");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "粉");
        lang.add(LangType.EN_CH, "Dust");
    }
}
