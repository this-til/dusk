package com.til.dusk.common.register.bind_type.bind_types;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.register.bind_type.BindTypeBindCapability;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.DuskColor;
import net.minecraftforge.items.IItemHandler;

/**
 * @author til
 */
public class ManaInBindType extends BindTypeBindCapability<IManaHandle> {
    public ManaInBindType() {
        super("mana_in", () -> CapabilityRegister.iManaHandle);
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "灵气输入");
        lang.add(LangType.EN_CH, "Mana In");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(255, 255, 0);
    }
}
