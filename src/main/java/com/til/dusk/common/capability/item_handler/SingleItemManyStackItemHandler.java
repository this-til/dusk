package com.til.dusk.common.capability.item_handler;

import com.til.dusk.common.capability.ITooltipCapability;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.Lang;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class SingleItemManyStackItemHandler implements IItemHandler, INBTSerializable<CompoundTag>, ITooltipCapability {


    public final int size;
    public final NonNullList<ItemStack> itemStacks;

    /***
     * 一个物品的标记
     */
    public Item itemTag;

    public SingleItemManyStackItemHandler(int size) {
        this.size = size;
        ItemStack[] list = new ItemStack[size];
        for (int i = 0; i < size; i++) {
            list[i] = ItemStack.EMPTY;
        }
        itemStacks = NonNullList.of(ItemStack.EMPTY, list);
    }

    @Override
    public int getSlots() {
        return size;
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        if (slot < 0 || slot >= itemStacks.size()) {
            return ItemStack.EMPTY;
        }
        return itemStacks.get(slot);
    }

    @Override
    @SuppressWarnings("deprecation")
    public int getSlotLimit(int slot) {
        if (itemTag == null) {
            return 64;
        }
        return itemTag.getMaxStackSize();
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        if (slot < 0 || slot >= itemStacks.size()) {
            return stack.copy();
        }
        if (itemTag == null) {
            if (!simulate) {
                itemTag = stack.getItem();
                itemStacks.set(slot, stack.copy());
            }
            return ItemStack.EMPTY;
        } else if (stack.is(itemTag)) {
            int max = getSlotLimit(slot);
            ItemStack oldItemStack = getStackInSlot(slot);
            if (oldItemStack.areShareTagsEqual(stack)) {
                int in = Math.min(max - oldItemStack.getCount(), Math.min(stack.getCount(), max));
                if (!simulate) {
                    ItemStack newItemStack;
                    if (oldItemStack.isEmpty()) {
                        newItemStack = new ItemStack(itemTag, 0);
                    } else {
                        newItemStack = oldItemStack.copy();
                    }
                    newItemStack.setCount(newItemStack.getCount() + in);
                    itemStacks.set(slot, newItemStack);
                }
                ItemStack outItemStack = stack.copy();
                outItemStack.setCount(outItemStack.getCount() - in);
                return outItemStack;
            }
        }
        return stack.copy();
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (amount == 0) {
            return ItemStack.EMPTY;
        }
        if (slot < 0 || slot >= itemStacks.size()) {
            return ItemStack.EMPTY;
        }
        ItemStack oldItemStack = getStackInSlot(slot);
        if (oldItemStack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        int outA = Math.min(oldItemStack.getCount(), Math.min(amount, oldItemStack.getMaxStackSize()));
        if (!simulate) {
            ItemStack newItemStack = oldItemStack.copy();
            newItemStack.setCount(newItemStack.getCount() - outA);
            itemStacks.set(slot, newItemStack);
            upItemTag();
        }
        ItemStack out = oldItemStack.copy();
        out.setCount(outA);
        return out;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return true;
    }

    public void upItemTag() {
        boolean isEmpty = true;
        for (ItemStack itemStack : itemStacks) {
            isEmpty = itemStack.isEmpty() && isEmpty;
        }
        if (isEmpty) {
            itemTag = null;
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        AllNBTPack.ITEM_STACK_LIST_TAG.set(compoundTag, itemStacks);
        AllNBTPack.ITEM.set(compoundTag, itemTag == null ? Items.AIR : itemTag);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        itemStacks.clear();
        List<ItemStack> list = AllNBTPack.ITEM_STACK_LIST_TAG.get(nbt);
        for (int i = 0; i < list.size(); i++) {
            itemStacks.set(i, list.get(i));
        }
        itemTag = AllNBTPack.ITEM.get(nbt);
        if (itemTag.equals(Items.AIR)) {
            itemTag = null;
        }
        if (itemTag == null) {
            for (ItemStack itemStack : itemStacks) {
                if (!itemStack.isEmpty()) {
                    itemTag = itemStack.getItem();
                    break;
                }
            }
        }
    }

    @Nullable
    @Override
    public CompoundTag appendServerData() {
        CompoundTag compoundTag = new CompoundTag();
        List<ItemStack> itemStackList = new ArrayList<>(getSlots());
        for (int i1 = 0; i1 < getSlots(); i1++) {
            itemStackList.add(getStackInSlot(i1));
        }
        AllNBTPack.ITEM_STACK_LIST_TAG.set(compoundTag, itemStackList);
        return compoundTag;
    }

    @Override
    public void appendTooltip(IComponentPack iTooltip, CompoundTag compoundTag) {
        iTooltip.add(Lang.getLang(CapabilityRegister.iItemHandler));
        iTooltip.indent();
        Map<Item, Integer> integerMap = new HashMap<>();
        AllNBTPack.ITEM_STACK_LIST_TAG.get(compoundTag).forEach(itemStack -> {
            if (itemStack.isEmpty()) {
                return;
            }
            Item item = itemStack.getItem();
            if (integerMap.containsKey(item)) {
                integerMap.put(item, integerMap.get(item) + itemStack.getCount());
            } else {
                integerMap.put(item, itemStack.getCount());
            }
        });
        for (Map.Entry<Item, Integer> itemIntegerEntry : integerMap.entrySet()) {
            ItemStack itemStack = new ItemStack(itemIntegerEntry.getKey(), itemIntegerEntry.getValue());
            iTooltip.add(
                    Lang.getLang(itemStack.getDisplayName(),
                            Component.literal("x" + itemIntegerEntry.getValue())));
        }
    }
}
