package com.til.dusk.util.nbt.cell;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author til
 */
public class RegisterItemNBTCell<E> extends NBTCell<StringTag, E> {

    public final Supplier<IForgeRegistry<E>> registrySupplier;
    public final Supplier<E> defaultItem;

    public RegisterItemNBTCell(Supplier<IForgeRegistry<E>> registrySupplier, Supplier<E> defaultItem) {
        this.registrySupplier = registrySupplier;
        this.defaultItem = defaultItem;
    }

    @Override
    public StringTag as(E e) {
        try {
            return StringTag.valueOf(Objects.requireNonNull(registrySupplier.get().getKey(e)).toString());
        } catch (Exception exception) {
            return StringTag.valueOf(defaultItem.get().toString());
        }
    }

    @Override
    public E from(StringTag stringTag) {
        try {
            E e = registrySupplier.get().getValue(new ResourceLocation(stringTag.getAsString()));
            assert e != null;
            return e;
        } catch (Exception exception) {
            return defaultItem.get();
        }
    }

    @Override
    public byte getaNBTId() {
        return 8;
    }
}
