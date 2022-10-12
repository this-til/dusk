package com.til.dusk.common.register;


import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Supplier;

/**
 * @author til
 */
public abstract class RegisterPack<T extends RegisterPack<?, ?>, E> extends TagPackSupplierRegister<T> {
    public E pack;

    public RegisterPack(ResourceLocation name, Supplier<IForgeRegistry<T>> iForgeRegistrySupplier) {
        super(name, iForgeRegistrySupplier);
    }

    @Override
    protected void registerBack() {
        pack = create();
        if (pack != null) {
            register(pack);
        }
    }

    /***
     * 创建一个实例
     */
    protected abstract E create();

    protected abstract void register(E e);
}
