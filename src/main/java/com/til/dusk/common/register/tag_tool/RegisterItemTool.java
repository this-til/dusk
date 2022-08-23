package com.til.dusk.common.register.tag_tool;

import com.til.dusk.Dusk;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Supplier;

public class RegisterItemTool<T> extends TagTool<T> {
    public final Supplier<IForgeRegistry<T>> registrySupplier;

    public RegisterItemTool(ResourceLocation name, Supplier<IForgeRegistry<T>> registrySupplier) {
        super(name);
        this.registrySupplier = registrySupplier;
    }

    public RegisterItemTool(String name, Supplier<IForgeRegistry<T>> registrySupplier) {
        this(new ResourceLocation(Dusk.MOD_ID, name), registrySupplier);
    }

    @Override
    public T get(CompoundTag nbt) {
        return registrySupplier.get().getValue(new ResourceLocation(nbt.getString(tagName)));
    }

    @Override
    public void set(CompoundTag nbt, T t) {
        ResourceLocation name = registrySupplier.get().getKey(t);
        if (name != null) {
            nbt.putString(tagName, name.toString());
        }
    }
}
