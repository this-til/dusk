package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemDust;

/**
 * @author til
 */
public class DustTinyOreItemDust extends OreItemDust {

    public DustTinyOreItemDust() {
        super("dust_tiny");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "小戳粉");
        lang.add(LangType.EN_CH, "Tiny Dust");
    }
}
