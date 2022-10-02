package com.til.dusk.common.capability;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;

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

}
