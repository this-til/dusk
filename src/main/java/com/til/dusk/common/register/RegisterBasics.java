package com.til.dusk.common.register;

import com.til.dusk.Dusk;
import com.til.dusk.util.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * @author til
 */
public abstract class RegisterBasics<T extends RegisterBasics<?>> {

    public final ResourceLocation name;
    public final Supplier<IForgeRegistry<T>> registrySupplier;

    protected boolean isRegister;
    protected boolean isRegisterSubsidiary;

    public RegisterBasics(ResourceLocation name, Supplier<IForgeRegistry<T>> registrySupplier) {
        this.name = name;
        this.registrySupplier = registrySupplier;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(EventPriority.HIGH, this::registerEvent);
        modEventBus.addListener(getRegisterBlackPriority(), this::registerSubsidiary);
    }

    public void registerEvent(RegisterEvent event) {
        if (!isRegister) {
            isRegister = true;
            registrySupplier.get().register(name, Util.forcedConversion(this));
        }
    }

    /***
     * 注册回调
     * @param event 注册事件
     */
    public void registerSubsidiary(RegisterEvent event) {
        if (!isRegisterSubsidiary) {
            isRegisterSubsidiary = true;
            registerSubsidiaryBlack();
        }
    }

    /***
     * 注册回调的触发事件
     */
    public void registerSubsidiaryBlack() {
    }

    /***
     * 返回注册回调的触发时间
     * @return 时间
     */
    public EventPriority getRegisterBlackPriority() {
        return EventPriority.LOW;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof RegisterBasics registerBasics) {
            return name.equals(registerBasics.name);
        }
        return false;
    }

    public static ResourceLocation fuseName(String splicing, RegisterBasics<?>... registerBasics) {
        String[] stringArrayList = new String[registerBasics.length];
        for (int i = 0; i < registerBasics.length; i++) {
            stringArrayList[i] = registerBasics[i].name.getPath();
        }
        return new ResourceLocation(Dusk.MOD_ID, String.join(splicing, Arrays.asList(stringArrayList)));
    }

    public static ResourceLocation fuseName(RegisterBasics<?>... registerBasics) {
        return fuseName("_", registerBasics);
    }
}
