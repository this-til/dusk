package com.til.dusk.common.register.attribute.entity.attributes;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.attribute.entity.ContainOriginalAttribute;
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
        range = new INumberPack.IDoublePack.Range(new INumberPack.IDoublePack.Constant(0), null);
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "攻击力");
        lang.add(LangType.EN_CH, "Attack");
    }
}
