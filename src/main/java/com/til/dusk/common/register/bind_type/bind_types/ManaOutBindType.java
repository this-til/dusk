package com.til.dusk.common.register.bind_type.bind_types;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.register.bind_type.BindTypeBindCapability;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.DuskColor;

/**
 * @author til
 */
public class ManaOutBindType extends BindTypeBindCapability<IManaHandle> {
    public ManaOutBindType() {
        super("mana_out", () -> CapabilityRegister.iManaHandle);
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "灵气输出");
        lang.add(LangType.EN_CH, "Mana Out");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(129, 129, 72);
    }
}
