package com.til.dusk.common.world.item;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.IItemDefaultCapability;
import com.til.dusk.common.register.ore.item.ArmorData;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * @author til
 */
public class CapabilityArmorItem extends DyeableArmorItem implements IItemDefaultCapability.ArmorCapabilityItem {
    public final Ore ore;
    public final OreItem oreItem;
    public final ArmorData armorData;


    public CapabilityArmorItem(ArmorData armorData, EquipmentSlot equipmentSlot, Properties properties, Ore ore, OreItem oreItem) {
        super(armorData, equipmentSlot, properties);
        this.ore = ore;
        this.armorData = armorData;
        this.oreItem = oreItem;

    }

    public static final String OVERLAY = "overlay";

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if (OVERLAY.equals(type)) {
            return Dusk.MOD_ID + ":textures/air.png";
        }
        return switch (slot) {
            case HEAD, CHEST, FEET -> Dusk.MOD_ID + ":textures/armor/model_layer_1.png";
            case LEGS -> Dusk.MOD_ID + ":textures/armor/model_layer_2.png";
            default -> null;
        };
    }

    @Override
    public boolean hasCustomColor(@NotNull ItemStack itemStack) {
        return true;
    }

    @Override
    public int getColor(@NotNull ItemStack itemStack) {
        return ore.color.getRGB();
    }

    @Override
    public void clearColor(@NotNull ItemStack itemStack) {
    }

    @Override
    public void setColor(@NotNull ItemStack itemStack, int color) {
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return Lang.getLang(ore, oreItem);
    }

    @Override
    public ArmorData getArmorData() {
        return armorData;
    }
}
