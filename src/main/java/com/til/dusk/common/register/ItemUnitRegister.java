package com.til.dusk.common.register;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.Lang;
import com.til.dusk.util.ResourceLocationUtil;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @author til
 */
public abstract class ItemUnitRegister<T extends ItemUnitRegister<T, O>, O extends TagPackSupplierRegister<?>> extends TagPackSupplierRegister<T> {

    public ItemUnitRegister(ResourceLocation name, Supplier<IForgeRegistry<T>> iForgeRegistrySupplier) {
        super(name, iForgeRegistrySupplier);
    }

    @Nullable
    public ItemPack create(O o) {
        Item item = createItem(o);
        return new ItemPack(item, createItemTag(o));
    }

    public TagKey<Item> createItemTag(O o) {
        return ItemTags.create(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{"item", o.name.getPath(), name.getPath()}));
    }

    public Item createItem(O o) {
        return new Item(new Item.Properties().tab(Dusk.TAB)) {
            @Override
            public @NotNull Component getName(@NotNull ItemStack stack) {
                return Lang.getLang(o, com.til.dusk.common.register.ItemUnitRegister.this);
            }
        };
    }


    /***
     * 获取方块物品模型映射
     */
    public DuskItem.ICustomModel getItemMoldMapping(O o) {
        return () -> name;
    }

    /***
     * 染色回调
     */
    public abstract void dyeBlack(O o, ColorProxy.ItemColorPack itemColorPack);
}
