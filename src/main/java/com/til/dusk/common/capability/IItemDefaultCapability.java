package com.til.dusk.common.capability;

import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.mana_handle.VariableManaHandle;
import com.til.dusk.common.capability.skill.ISkill;
import com.til.dusk.common.capability.skill.ItemStackSkill;
import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.skill.Skill;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.Map;

/***
 * 物品默认拥有的能力，用于构建DuskCapabilityProvider
 * @author til
 */
public interface IItemDefaultCapability {

    /***
     * 为物品添加能力
     * @param event 能力发布事件
     * @param duskCapabilityProvider 能力提供商
     */
    void initCapability(AttachCapabilitiesEvent<ItemStack> event, DuskCapabilityProvider duskCapabilityProvider);

    abstract class CapabilityItem extends Item implements IItemDefaultCapability {
        public CapabilityItem(Properties properties) {
            super(properties);
            init();
        }

        public void init() {
        }
    }

    interface ArmsCapabilityItem extends IItemDefaultCapability {
        ConfigMap getConfigMap();

        @Override
        default void initCapability(AttachCapabilitiesEvent<ItemStack> event, DuskCapabilityProvider duskCapabilityProvider) {
            ConfigMap configMap = getConfigMap();
            IBack iBack = duskCapabilityProvider.addCapability(CapabilityRegister.iBlack.capability, new Back());
            ISkill iSkill = duskCapabilityProvider.addCapability(CapabilityRegister.iSkill.capability, new ItemStackSkill());
            for (Map.Entry<Skill, Integer> entry : configMap.get(Ore.ArmsConfig.DEFAULT_SKILL).entrySet()) {
                iSkill.getSkill(entry.getKey()).originalLevel = entry.getValue();
            }
            if (configMap.get(Ore.ArmsConfig.MANA_BASICS) > 0) {
                duskCapabilityProvider.addCapability(CapabilityRegister.iManaHandle.capability,
                        new VariableManaHandle(configMap.get(Ore.ArmsConfig.MANA_BASICS), configMap.get(Ore.ArmsConfig.RATE_BASICS), iBack,
                        () -> 1 + iSkill.getSkill(Skill.maxManaDilatation).level * 0.2, () -> 1 + iSkill.getSkill(Skill.rateDilatation).level * 0.2));
            }
        }
    }

    interface ArmorCapabilityItem extends IItemDefaultCapability {
        ConfigMap getConfigMap();

        @Override
        default void initCapability(AttachCapabilitiesEvent<ItemStack> event, DuskCapabilityProvider duskCapabilityProvider) {
            ConfigMap configMap = getConfigMap();
            IBack iBack = duskCapabilityProvider.addCapability(CapabilityRegister.iBlack.capability, new Back());
            ISkill iSkill = duskCapabilityProvider.addCapability(CapabilityRegister.iSkill.capability, new ItemStackSkill());
            for (Map.Entry<Skill, Integer> entry : configMap.get(Ore.ArmorConfig.DEFAULT_SKILL).entrySet()) {
                iSkill.getSkill(entry.getKey()).originalLevel = entry.getValue();
            }
            if (configMap.get(Ore.ArmorConfig.MANA_BASICS) > 0) {
                duskCapabilityProvider.addCapability(CapabilityRegister.iManaHandle.capability,
                        new VariableManaHandle(configMap.get(Ore.ArmorConfig.MANA_BASICS), configMap.get(Ore.ArmorConfig.RATE_BASICS), iBack,
                                () -> 1 + iSkill.getSkill(Skill.maxManaDilatation).level * 0.2, () -> 1 + iSkill.getSkill(Skill.rateDilatation).level * 0.2));
            }
        }
    }

}
