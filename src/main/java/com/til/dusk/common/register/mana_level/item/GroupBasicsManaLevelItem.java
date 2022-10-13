package com.til.dusk.common.register.mana_level.item;

import com.til.dusk.common.register.mana_level.item.ManaLevelItem;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelItemGroup;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraft.resources.ResourceLocation;

/**
 * @author til
 */
public abstract class GroupBasicsManaLevelItem extends ManaLevelItem {

    public final ManaLevelItemGroup manaLevelItemGroup;
    public final ResourceLocation suffix;

    public GroupBasicsManaLevelItem(final ResourceLocation suffix, ManaLevelItemGroup manaLevelItemGroup) {
        super(ResourceLocationUtil.fuseName(manaLevelItemGroup.name.getNamespace(), "_", new String[]{manaLevelItemGroup.name.getPath(), suffix.getPath()}));
        this.manaLevelItemGroup = manaLevelItemGroup;
        this.suffix = suffix;
    }
}
