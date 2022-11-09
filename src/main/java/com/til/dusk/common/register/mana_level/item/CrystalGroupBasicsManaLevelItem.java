package com.til.dusk.common.register.mana_level.item;

import com.google.gson.JsonObject;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelCrystalGroup;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.ModelJsonUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

/**
 * @author til
 */
public abstract class CrystalGroupBasicsManaLevelItem extends GroupBasicsManaLevelItem {
    public final ManaLevelCrystalGroup manaLevelCrystalGroup;

    public CrystalGroupBasicsManaLevelItem(ResourceLocation suffix, ManaLevelCrystalGroup manaLevelCrystalGroup) {
        super(suffix, manaLevelCrystalGroup);
        this.manaLevelCrystalGroup = manaLevelCrystalGroup;
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
        super.dyeBlack(manaLevel, itemColorPack);
        itemColorPack.addColor(1, itemStack -> manaLevelCrystalGroup.coreColor);
        itemColorPack.addColor(2, itemStack -> manaLevelCrystalGroup.strokeColor);
    }

    @Override
    public JsonObject createModel(Item item, ManaLevel manaLevel) {
        return ModelJsonUtil.createItemFather(suffix);
    }
}
