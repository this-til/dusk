package com.til.dusk.util.nbt.cell;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.tags.ITagManager;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author til
 */
public class TagKeyNBTCell<E> extends NBTCell<TagKey<E>> {
    public final Supplier<ITagManager<E>> registrySupplier;

    public TagKeyNBTCell(Supplier<ITagManager<E>> registrySupplier) {
        this.registrySupplier = registrySupplier;
    }


    @Override
    public StringTag as(TagKey<E> e) {
        return StringTag.valueOf(e.location().toString());
    }

    @Override
    public TagKey<E> from(Tag stringTag) {
        return registrySupplier.get().createTagKey(new ResourceLocation(stringTag.getAsString()));
    }

    @Override
    public JsonElement asJson(TagKey<E> eTagKey) {
        return new JsonPrimitive(eTagKey.location().toString());
    }

    @Override
    public TagKey<E> fromJson(JsonElement json) {
       return registrySupplier.get().createTagKey(new ResourceLocation(json.getAsString()));
    }
}
