package com.til.dusk.common.world.item;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.IItemDefaultCapability;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;

public class CapabilityArmorItem extends DyeableArmorItem implements IItemDefaultCapability {
    public final Ore ore;
    public final OreItem.ArmorData armorData;

    public CapabilityArmorItem(OreItem.ArmorData armorMaterial, EquipmentSlot equipmentSlot, Properties properties, Ore ore) {
        super(armorMaterial, equipmentSlot, properties);
        this.ore = ore;
        this.armorData = armorMaterial;
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
    public void initCapability(AttachCapabilitiesEvent<ItemStack> event, DuskCapabilityProvider duskCapabilityProvider) {
        armorData.initCapability(event, duskCapabilityProvider);
    }
}
