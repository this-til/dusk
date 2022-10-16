package com.til.dusk.common.register.bind_type.bind_types;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.bind_type.BindTypeBindCapability;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.DuskColor;
import net.minecraftforge.fluids.capability.IFluidHandler;

/**
 * @author til
 */
public class FluidOutBindType extends BindTypeBindCapability<IFluidHandler> {
    public FluidOutBindType() {
        super("fluid_out", () -> CapabilityRegister.iFluidHandler);
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "流体输出");
        lang.add(LangType.EN_CH, "Fluid Out");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(68, 124, 129);
    }
}
