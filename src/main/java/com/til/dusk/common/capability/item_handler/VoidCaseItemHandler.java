package com.til.dusk.common.capability.item_handler;

import com.til.dusk.common.capability.ITooltipCapability;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.Lang;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * @author til
 */
public class VoidCaseItemHandler implements IItemHandler, INBTSerializable<CompoundTag>, ITooltipCapability {

    /***
     * 最大数量
     */
    public final long maxCount;

    public long count;

    /***
     * 物品站标签
     * 只有一个
     */
    @Nullable
    public ItemStack itemStackTag;

    public VoidCaseItemHandler(long maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    public int getSlots() {
        return 1;
    }

    @Override
    public int getSlotLimit(int slot) {
        return (int) maxCount;
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        if (itemStackTag == null) {
            return ItemStack.EMPTY;
        }
        ItemStack itemStack = itemStackTag.copy();
        itemStack.setCount(Math.min((int) count, itemStackTag.getMaxStackSize()));
        return itemStack;
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        if (slot != 0) {
            return stack.copy();
        }
        if (itemStackTag != null) {
            if (ItemHandlerHelper.canItemStacksStack(itemStackTag,stack)) {
                long in = Math.min(stack.getCount(), maxCount - count);
                if (!simulate) {
                    count += in;
                }
                ItemStack outItemStack = stack.copy();
                outItemStack.setCount((int) (outItemStack.getCount() - in));
                return outItemStack;
            }
        } else {
            long in = Math.min(stack.getCount(), maxCount);
            if (!simulate) {
                itemStackTag = stack.copy();
                itemStackTag.setCount(1);
                count = in;
            }
            ItemStack outItemStack = stack.copy();
            outItemStack.setCount((int) (outItemStack.getCount() - in));
            return outItemStack;
        }
        return stack.copy();
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (slot != 0) {
            return ItemStack.EMPTY;
        }
        if (itemStackTag == null) {
            return ItemStack.EMPTY;
        }
        long out = Math.min(Math.min(amount, itemStackTag.getMaxStackSize()), count);
        ItemStack outItemStack = itemStackTag.copy();
        outItemStack.setCount((int) out);
        if (!simulate) {
            count -= out;
            if (count <= 0) {
                itemStackTag = null;
                count = 0;
            }
        }
        return outItemStack;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return true;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        AllNBTPack.ITEM_STACK.set(compoundTag, itemStackTag == null ? ItemStack.EMPTY : itemStackTag);
        AllNBTPack.COUNT.set(compoundTag, count);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        count = AllNBTPack.COUNT.get(nbt);
        itemStackTag = AllNBTPack.ITEM_STACK.get(nbt);
        if (itemStackTag.isEmpty() || count <= 0) {
            itemStackTag = null;
            count = 0;
        }
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public CompoundTag appendServerData() {
        return serializeNBT();
    }

    @Override
    public void appendTooltip(IComponentPack iTooltip, CompoundTag compoundTag) {
        ItemStack itemStack = AllNBTPack.ITEM_STACK.get(compoundTag);
        long c = AllNBTPack.COUNT.get(compoundTag);
        if (!itemStack.isEmpty() && c > 0) {
            iTooltip.add(Lang.getLang(Lang.getLang(CapabilityRegister.iItemHandler), itemStack.getDisplayName(), Component.literal("x" + c)));
        } else {
            iTooltip.add(Lang.getLang(Lang.getLang(CapabilityRegister.iItemHandler)));
        }
    }
}
