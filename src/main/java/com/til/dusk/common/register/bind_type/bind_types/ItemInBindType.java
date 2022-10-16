package com.til.dusk.common.register.bind_type.bind_types;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.bind_type.BindType;
import com.til.dusk.common.register.bind_type.BindTypeBindCapability;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.DuskColor;
import net.minecraftforge.items.IItemHandler;

/**
 * @author til
 */
public class ItemInBindType extends BindTypeBindCapability<IItemHandler> {
    public ItemInBindType() {
        super("item_in", () -> CapabilityRegister.iItemHandler);
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "物品输入");
        lang.add(LangType.EN_CH, "Item In");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(43, 255, 33);
    }
}
