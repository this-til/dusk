package com.til.dusk.common.register.bind_type;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.DuskColor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * @author til
 */
public abstract class BindTypeBindCapability<C> extends BindType {

    public CapabilityRegister<C> capability;
    public Supplier<CapabilityRegister<C>> capabilityRegisterSupplier;

    public BindTypeBindCapability(ResourceLocation name, Supplier<CapabilityRegister<C>> capabilityRegisterSupplier) {
        super(name);
        this.capabilityRegisterSupplier = capabilityRegisterSupplier;
    }

    public BindTypeBindCapability(String name, Supplier<CapabilityRegister<C>> capabilityRegisterSupplier) {
        this(new ResourceLocation(Dusk.MOD_ID, name), capabilityRegisterSupplier);
    }

    @Override
    protected void registerBlackToBack() {
        capability = capabilityRegisterSupplier.get();
        capabilityRegisterSupplier = null;
    }

    @Nullable
    @Override
    public Component filter(IControl iControl, IPosTrack blockEntity) {
        LazyOptional<C> lazyOptional = blockEntity.getAsCapabilityProvider().getCapability(capability.capability);
        if (lazyOptional.isPresent()) {
            return null;
        }
        return Component.translatable("绑定失败，绑定方块没有[%s]的能力", Component.translatable(capability.name.toLanguageKey()));
    }
}
