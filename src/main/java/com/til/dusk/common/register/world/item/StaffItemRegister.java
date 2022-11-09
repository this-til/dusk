package com.til.dusk.common.register.world.item;

import com.google.gson.JsonObject;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.ModelJsonUtil;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

/**
 * @author til
 */
public abstract class StaffItemRegister extends ItemPackRegister implements DuskItem.IHasCustomColor {
    public StaffItemRegister(ResourceLocation name) {
        super(name);
    }

    public StaffItemRegister(String name) {
        super(name);
    }

    @Override
    public void itemColorBlack(ColorProxy.ItemColorPack itemColorPack) {
        itemColorPack.addColor(0, itemStack -> {
            CompoundTag compoundTag = itemStack.getTag();
            if (compoundTag != null && AllNBTPack.COLOR.contains(compoundTag)) {
                return new DuskColor(AllNBTPack.COLOR.get(compoundTag));
            }
            return defaultColor;
        });
    }

    @Override
    public JsonObject createModel(Item item, Void unused) {
        return null;
    }

    @Override
    public void defaultConfig() {
        defaultColor = ColorPrefab.FLUID_IO;
    }

    @ConfigField
    public DuskColor defaultColor;
}
