package com.til.dusk.common.register.bind_type.bind_types;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.capability.shaped_drive.IShapedDrive;
import com.til.dusk.common.register.bind_type.BindTypeBindCapability;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.DuskColor;

/**
 * @author til
 */
public class ModelStoreBindType extends BindTypeBindCapability<IShapedDrive> {

    public ModelStoreBindType() {
        super("model_store", () -> CapabilityRegister.iShapedDrive);
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "配方模式");
        lang.add(LangType.EN_CH, "Mana Out");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(204, 147, 255);
    }
}
