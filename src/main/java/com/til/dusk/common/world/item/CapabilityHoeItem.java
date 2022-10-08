package com.til.dusk.common.world.item;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.IItemDefaultCapability;
import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * @author til
 */
public class CapabilityHoeItem extends HoeItem implements IItemDefaultCapability.ArmsCapabilityItem {
    public final Ore ore;
    public final OreItem oreItem;
    public final ConfigMap armsData;

    public CapabilityHoeItem(Ore ore, OreItem oreItem, ConfigMap armsData) {
        super(armsData, (int) (armsData.get(Ore.ArmsConfig.ATTACK_DAMAGE_BONUS) * 0.6f), armsData.get(Ore.ArmsConfig.SPEED), new Properties().stacksTo(1).tab(Dusk.TAB).fireResistant());
        this.ore = ore;
        this.oreItem = oreItem;
        this.armsData = armsData;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return Lang.getLang(ore, oreItem);
    }

    @Override
    public ConfigMap getConfigMap() {
        return armsData;
    }
}
