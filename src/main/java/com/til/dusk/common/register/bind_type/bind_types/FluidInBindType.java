package com.til.dusk.common.register.bind_type.bind_types;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.bind_type.BindTypeBindCapability;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.DuskColor;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

/**
 * @author til
 */
public class FluidInBindType extends BindTypeBindCapability<IFluidHandler> {
    public FluidInBindType() {
        super("fluid_in", () -> CapabilityRegister.iFluidHandler);
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "流体输入");
        lang.add(LangType.EN_CH, "Fluid In");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(29, 237, 255);
    }
}
