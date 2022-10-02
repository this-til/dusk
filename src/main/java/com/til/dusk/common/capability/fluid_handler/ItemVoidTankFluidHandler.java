package com.til.dusk.common.capability.fluid_handler;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import org.jetbrains.annotations.NotNull;

/**
 * @author til
 */
public class ItemVoidTankFluidHandler extends VoidTankFluidHandler implements IFluidHandlerItem {

    public ItemStack itemStack;

    public ItemVoidTankFluidHandler(int maxAmount, ItemStack itemStack) {
        super(maxAmount);
        this.itemStack = itemStack;
    }

    @Override
    public @NotNull ItemStack getContainer() {
        return itemStack;
    }
}
