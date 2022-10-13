package com.til.dusk.common.register.mana_level.item;

import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelCrystalGroup;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.world.item.DuskItem;
import net.minecraft.resources.ResourceLocation;

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
    public DuskItem.ICustomModel getItemMoldMapping(ManaLevel manaLevel) {
        return () -> suffix;
    }
}
