package com.til.dusk.common.register.mana_level.item.items;

import com.til.dusk.Dusk;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.item.CrystalGroupBasicsManaLevelItem;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelCrystalGroup;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * @author til
 */
public class ProcessorManaLevelItem extends CrystalGroupBasicsManaLevelItem {
    public static final ResourceLocation PROCESSOR = new ResourceLocation(Dusk.MOD_ID, "processor");

    public ProcessorManaLevelItem(ManaLevelCrystalGroup manaLevelCrystalGroup) {
        super(PROCESSOR, manaLevelCrystalGroup);
    }
    @Override
    public Item createItem(ManaLevel manaLevel) {
        Item item = new Item(new Item.Properties().tab(Dusk.TAB)) {
            @Override
            public @NotNull Component getName(@NotNull ItemStack stack) {
                return Lang.getLang(Lang.getLang(manaLevel),
                        Component.translatable(manaLevelCrystalGroup.name.toLanguageKey()),
                        Component.translatable(suffix.toLanguageKey()));
            }
        };
        ItemTag.addTag(manaLevel.acceptableTagPack.getTagPack(manaLevelCrystalGroup.name).itemTagKey(), item);
        return item;
    }

}
