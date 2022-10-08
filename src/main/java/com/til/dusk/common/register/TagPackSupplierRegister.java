package com.til.dusk.common.register;

import com.til.dusk.common.world.tag.TagPackSupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Supplier;

/**
 * @author til
 */
public abstract class TagPackSupplierRegister<V extends TagPackSupplierRegister<?>> extends RegisterBasics<V> {

    public final TagPackSupplier tagPackSupplier;

    public TagPackSupplierRegister(ResourceLocation name, Supplier<IForgeRegistry<V>> iForgeRegistrySupplier) {
        super(name, iForgeRegistrySupplier);
        tagPackSupplier = new TagPackSupplier(name, null);
    }
}
