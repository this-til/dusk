package com.til.dusk.common.register.bind_type.bind_types;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.bind_type.BindTypeBindCapability;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.DuskColor;
import net.minecraftforge.items.IItemHandler;

/**
 * @author til
 */
public class ItemOutBindType extends BindTypeBindCapability<IItemHandler> {
    public ItemOutBindType() {
        super("item_out", () -> CapabilityRegister.iItemHandler);
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "物品输出");
        lang.add(LangType.EN_CH, "Item Out");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(65, 112, 62);
    }
}
