package com.til.dusk.common.world.item;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.IItemDefaultCapability;
import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.item.ArmsData;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.OreConfig;
import com.til.dusk.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author til
 */
public class CapabilityPickaxeItem extends PickaxeItem implements IItemDefaultCapability.ArmsCapabilityItem {
    public final Ore ore;
    public final OreItem oreItem;
    public final ConfigMap armsData;

    public CapabilityPickaxeItem(Ore ore, OreItem oreItem, ConfigMap armsData) {
        super(armsData, (int) (armsData.get(OreConfig.ArmsConfig.ATTACK_DAMAGE_BONUS) * 0.8f), armsData.get(OreConfig.ArmsConfig.SPEED), new Properties().stacksTo(1).tab(Dusk.TAB).fireResistant());
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
