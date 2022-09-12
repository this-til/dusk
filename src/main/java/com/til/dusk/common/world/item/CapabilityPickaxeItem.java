package com.til.dusk.common.world.item;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.IItemDefaultCapability;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;

public class CapabilityPickaxeItem extends PickaxeItem implements IItemDefaultCapability {
    public final Ore ore;
    public final OreItem oreItem;
    public OreItem.ArmsData armsData;

    public CapabilityPickaxeItem(Ore ore, OreItem oreItem, OreItem.ArmsData armsData) {
        super(armsData, (int) (armsData.attackDamageBonus * 0.8f), armsData.speed, new Properties().stacksTo(1).tab(Dusk.TAB));
        this.ore = ore;
        this.oreItem = oreItem;
        this.armsData = armsData;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return Lang.getLang(ore, oreItem);
    }

    @Override
    public void initCapability(AttachCapabilitiesEvent<ItemStack> event, DuskCapabilityProvider duskCapabilityProvider) {
        armsData.initCapability(event, duskCapabilityProvider);
    }
}
