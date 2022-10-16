package com.til.dusk.common.register.attribute.attributes;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.attribute.ContainOriginalAttribute;
import com.til.dusk.util.math.INumberPack;

/**
 * @author til
 */
public class AttackAttribute extends ContainOriginalAttribute {

    public AttackAttribute() {
        super("attribute", null);
        //TODO
    }

    @Override
    public void defaultConfig() {
        range = new INumberPack.Range(new INumberPack.Constant(0), null);
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "攻击力");
        lang.add(LangType.EN_CH, "Attack");
    }
}
