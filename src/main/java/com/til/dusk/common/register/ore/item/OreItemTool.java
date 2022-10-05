package com.til.dusk.common.register.ore.item;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.Lang;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OreItemTool extends OreItem {
    public OreItemTool(ResourceLocation name) {
        super(name);
        setConfig(IS_TOOL);
    }

    public OreItemTool(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public @Nullable ItemPack create(Ore ore) {
        if (ore.hasSet(Ore.TOOL_DATA, Ore.IS_METAL)) {
            return super.create(ore);
        }
        return null;
    }

    @Override
    public Item createItem(Ore ore) {
        return createToolItem(ore, ore.getSet(Ore.TOOL_DATA));
    }

    /***
     * 创建工具物品
     * @param ore 矿物
     * @param toolData 工具数据
     * @return 工具
     */
    public Item createToolItem(Ore ore, ToolData toolData) {
        return new Item(new Item.Properties().tab(Dusk.TAB).durability(toolData.uses).fireResistant()) {

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
}
