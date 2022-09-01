package com.til.dusk.util.nbt.cell;

import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.tags.ITagManager;

import java.util.Objects;
import java.util.function.Supplier;

public class TagKeyNBTCell<E> extends NBTCell<StringTag, TagKey<E>> {
    public final Supplier<ITagManager<E>> registrySupplier;

    public TagKeyNBTCell(Supplier<ITagManager<E>> registrySupplier) {
        this.registrySupplier = registrySupplier;
    }


    @Override
    public StringTag as(TagKey<E> e) {
        return StringTag.valueOf(e.location().toString());
    }

    @Override
    public TagKey<E> from(StringTag stringTag) {
        return registrySupplier.get().createTagKey(new ResourceLocation(stringTag.getAsString()));
    }

    @Override
    public byte getaNBTId() {
        return 8;
    }
}
