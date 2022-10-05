package com.til.dusk.common.world.item;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.IItemDefaultCapability;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.item.ArmsData;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;

public class CapabilityHoeItem extends HoeItem implements IItemDefaultCapability {
    public final Ore ore;
    public final OreItem oreItem;
    public final ArmsData armsData;

    public CapabilityHoeItem(Ore ore, OreItem oreItem, ArmsData armsData) {
        super(armsData, (int) (armsData.attackDamageBonus * 0.6f), armsData.speed, new Properties().stacksTo(1).tab(Dusk.TAB).fireResistant());
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
