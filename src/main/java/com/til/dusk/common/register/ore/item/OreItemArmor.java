package com.til.dusk.common.register.ore.item;

import com.til.dusk.Dusk;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.world.item.CapabilityArmorItem;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;

public class OreItemArmor extends OreItem {

    public final EquipmentSlot equipmentSlot;

    public OreItemArmor(ResourceLocation name, EquipmentSlot equipmentSlot) {
        super(name);
        this.equipmentSlot = equipmentSlot;
        setSet(IS_ARMOR, null);
        setSet(asArmorKey(equipmentSlot), null);
    }

    public OreItemArmor(String name, EquipmentSlot equipmentSlot) {
        this(new ResourceLocation(Dusk.MOD_ID, name), equipmentSlot);
    }

    @Override
    public @Nullable ItemPack create(Ore ore) {
        if (ore.hasSet(Ore.ARMOR_DATA)) {
            return super.create(ore);
        }
        return null;
    }

    @Override
    public Item createItem(Ore ore) {
        CapabilityArmorItem item = new CapabilityArmorItem(ore.getSet(Ore.ARMOR_DATA), equipmentSlot, new Item.Properties().stacksTo(1).tab(Dusk.TAB).fireResistant(), ore, this);
        ItemTag.addTag(Tags.Items.ARMORS, item);
        ItemTag.addTag(asTag(equipmentSlot), item);
        return item;
    }
}