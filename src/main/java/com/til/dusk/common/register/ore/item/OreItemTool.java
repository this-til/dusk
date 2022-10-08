package com.til.dusk.common.register.ore.item;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.Lang;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author til
 */
public class OreItemTool extends OreItem {
    public OreItemTool(ResourceLocation name) {
        super(name);
    }

    public OreItemTool(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public @Nullable ItemPack create(Ore ore) {
        if (ore.hasConfig(Ore.ToolDataConfig.TOOL_DATA_CONFIG) && ore.hasConfig(Ore.IS_METAL)) {
            return super.create(ore);
        }
        return null;
    }

    @Override
    public Item createItem(Ore ore) {
        return createToolItem(ore, ore.getConfig(Ore.ToolDataConfig.TOOL_DATA_CONFIG));
    }

    /***
     * 创建工具物品
     * @param ore 矿物
     * @param configMap 工具数据
     * @return 工具
     */
    public Item createToolItem(Ore ore, ConfigMap configMap) {
        return new Item(new Item.Properties().tab(Dusk.TAB).durability(configMap.get(Ore.ToolDataConfig.USES)).fireResistant()) {

            @Override
            public @NotNull Component getName(@NotNull ItemStack stack) {
                return Lang.getLang(ore, OreItemTool.this);
            }

            @Override
            public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
                ItemStack outItemStack = new ItemStack(this);
                outItemStack.setDamageValue(itemStack.getDamageValue() + 1);
                if (outItemStack.getDamageValue() >= outItemStack.getMaxDamage()) {
                    return ItemStack.EMPTY;
                }
                return outItemStack;
            }

            @Override
            public boolean hasCraftingRemainingItem(ItemStack stack) {
                return true;
            }

            @Override
            public boolean isRepairable(@NotNull ItemStack itemstack) {
                return false;
            }
        };
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return super.defaultConfigMap()
                .setConfig(IS_TOOL);
    }
}
