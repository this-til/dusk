package com.til.dusk.util.tag_tool;

import com.til.dusk.Dusk;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class RegisterItemTool<T> extends TagTool<T> {
    public final Supplier<IForgeRegistry<T>> registrySupplier;
    public final Supplier<T> defaultItem;

    public RegisterItemTool(ResourceLocation name, Supplier<IForgeRegistry<T>> registrySupplier,  Supplier<T>  defaultItem) {
        super(name);
        this.registrySupplier = registrySupplier;
        this.defaultItem = defaultItem;
    }

    public RegisterItemTool(String name, Supplier<IForgeRegistry<T>> registrySupplier,  Supplier<T>  defaultItem) {
        this(new ResourceLocation(Dusk.MOD_ID, name), registrySupplier, defaultItem);
    }

    @Nullable
    @Override
    public T get(CompoundTag nbt) {
        try {
            T t = registrySupplier.get().getValue(new ResourceLocation(nbt.getString(tagName)));
            if (t == null) {
                t = defaultItem.get();
            }
            return t;
        } catch (Exception e) {
            return defaultItem.get();
        }

    }

    @Override
    public void set(CompoundTag nbt, T t) {
        ResourceLocation name = registrySupplier.get().getKey(t);
        if (name != null) {
            nbt.putString(tagName, name.toString());
        }
    }
}
