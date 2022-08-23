package com.til.dusk.common.register.tag_tool;

import com.til.dusk.Dusk;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class RegisterItemListTool<T> extends TagTool<List<T>> {
    public final Supplier<IForgeRegistry<T>> registrySupplier;
    public final ListStringTool listStringTool;

    public RegisterItemListTool(ResourceLocation name, Supplier<IForgeRegistry<T>> registrySupplier) {
        super(name);
        this.registrySupplier = registrySupplier;
        listStringTool = new ListStringTool(new ResourceLocation(name.getPath(), name.getPath() + "_registry_item_list"));
    }

    public RegisterItemListTool(String name, Supplier<IForgeRegistry<T>> registrySupplier) {
        this(new ResourceLocation(Dusk.MOD_ID, name), registrySupplier);
    }

    @Override
    public List<T> get(CompoundTag nbt) {
        List<T> list = new ArrayList<>();
        for (String s : listStringTool.get(nbt)) {
            T t = registrySupplier.get().getValue(new ResourceLocation(s));
            if (t != null) {
                list.add(t);
            }
        }
        return list;
    }

    @Override
    public void set(CompoundTag nbt, List<T> ts) {
        List<String> list = new ArrayList<>();
        for (T t : ts) {
            ResourceLocation resourceLocation = registrySupplier.get().getKey(t);
            if (resourceLocation != null) {
                list.add(resourceLocation.toString());
            }
        }
        listStringTool.set(nbt, list);
    }
}
