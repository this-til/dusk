package com.til.dusk.common.register.shaped.shapeds;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.til.dusk.Dusk;
import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.Extension;
import com.til.dusk.util.ListUtil;
import com.til.dusk.util.MapUtil;
import com.til.dusk.util.RoutePack;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.*;

public class ShapedOre extends ShapedMiddle {

    @Nullable
    public List<Extension.VariableData_2<TagKey<Item>, Integer>> item;
    @Nullable
    public List<Extension.VariableData_2<TagKey<Fluid>, Integer>> fluid;
    @Nullable
    public List<Extension.VariableData_2<ItemStack, Double>> outItem;
    @Nullable
    public List<Extension.VariableData_2<FluidStack, Double>> outFluid;
    @Nullable
    public TagKey<Item> itemScreen;
    @Nullable
    public TagKey<Fluid> fluidScreen;

    public final Random random = new Random();

    public ShapedOre(ResourceLocation resourceLocation, ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel) {
        super(resourceLocation, shapedType, shapedDrive, manaLevel);
    }

    @Override
    public ShapedHandle get(IHandle iHandle, Map.Entry<IPosTrack, IItemHandler> iItemHandlers, Map<IPosTrack, IFluidHandler> fluidHandlers) {
        if (item != null && iItemHandlers == null) {
            return null;
        }
        if (fluid != null && (fluidHandlers == null || fluidHandlers.isEmpty())) {
            return null;
        }
        if (extractFluid(iHandle, fluidHandlers, true)) {
            if (item != null) {
                if (extractItem(iHandle, iItemHandlers, true)) {
                    extractFluid(iHandle, fluidHandlers, false);
                    extractItem(iHandle, iItemHandlers, false);
                    return new ShapedHandle(surplusTime, consumeMana, outMana, makeOutItem(), makeOutFluid());
                }
            } else {
                extractFluid(iHandle, fluidHandlers, false);
                return new ShapedHandle(surplusTime, consumeMana, outMana, makeOutItem(), makeOutFluid());
            }
        }
        return null;
    }

    @Override
    public boolean screenOfItem(ItemStack itemStack) {
        return itemScreen == null || itemStack.is(itemScreen);
    }

    @Override
    public boolean screenOfFluid(FluidStack fluidStack) {
        return fluidScreen == null || fluidStack.getFluid().is(fluidScreen);
    }

    @Override
    public boolean hasItemIn() {
        return itemScreen != null;
    }

    @Override
    public boolean hasFluidIn() {
        return fluidScreen != null;
    }

    @Nullable
    public List<FluidStack> makeOutFluid() {
        if (outFluid == null) {
            return null;
        }
        List<FluidStack> fluidStacks = new ArrayList<>(outFluid.size());
        for (Map.Entry<FluidStack, Double> entry : outFluid) {
            if (random.nextDouble() < entry.getValue()) {
                fluidStacks.add(entry.getKey().copy());
            }
        }
        return fluidStacks;
    }

    @Nullable
    public List<ItemStack> makeOutItem() {
        if (outItem == null) {
            return null;
        }
        List<ItemStack> itemStackList = new ArrayList<>(outItem.size());
        for (Map.Entry<ItemStack, Double> entry : outItem) {
            if (random.nextDouble() < entry.getValue()) {
                itemStackList.add(entry.getKey().copy());
            }
        }
        return itemStackList;
    }

    protected boolean extractItem(IHandle iControl, Map.Entry<IPosTrack, IItemHandler> entry, boolean isSimulated) {
        if (item == null) {
            return true;
        }
        RoutePack<ItemStack> routePack = new RoutePack<>();
        for (Map.Entry<TagKey<Item>, Integer> tagKeyIntegerEntry : item) {
            int needItem = tagKeyIntegerEntry.getValue();
            for (int i = 0; i < entry.getValue().getSlots(); i++) {
                ItemStack oldItemStack = entry.getValue().getStackInSlot(i);
                if (oldItemStack.isEmpty()) {
                    continue;
                }
                if (oldItemStack.is(tagKeyIntegerEntry.getKey())) {
                    ItemStack outItemStack = CapabilityHelp.extractItem(iControl.getPosTrack(), routePack, entry, i, needItem, isSimulated);
                    needItem = needItem - outItemStack.getCount();
                }
                if (needItem == 0) {
                    break;
                }
            }
            if (needItem > 0) {
                return false;
            }
        }
        if (!isSimulated) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Item(iControl.getPosTrack().getLevel(), routePack));
        }
        return true;
    }

    protected boolean extractFluid(IHandle iControl, Map<IPosTrack, IFluidHandler> fluidHandlers, boolean isSimulated) {
        if (fluid == null) {
            return true;
        }
        RoutePack<FluidStack> routePack = new RoutePack<>();
        for (Map.Entry<TagKey<Fluid>, Integer> tagKeyIntegerEntry : fluid) {
            int need = tagKeyIntegerEntry.getValue();
            need -= CapabilityHelp.drain(iControl.getPosTrack(), null, fluidHandlers, tagKeyIntegerEntry, isSimulated);
            if (need > 0) {
                return false;
            }
        }
        if (!isSimulated) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(iControl.getPosTrack().getLevel(), routePack));
        }
        return true;
    }

    @Override
    public IJEIShaped getJEIShaped() {
        return new IJEIShaped() {
            @Nullable
            @Override
            public List<List<ItemStack>> getItemIn() {
                if (item == null) {
                    return null;
                }
                List<List<ItemStack>> list = new ArrayList<>();
                for (Extension.VariableData_2<TagKey<Item>, Integer> entry : item) {
                    List<ItemStack> itemStackList = new ArrayList<>();
                    for (Item item1 : Dusk.instance.ITEM_TAG.getTag(entry.getKey())) {
                        itemStackList.add(new ItemStack(item1, entry.getValue()));
                    }
                    list.add(itemStackList);
                }
                return list;

            }

            @Nullable
            @Override
            public List<List<FluidStack>> getFluidIn() {
                if (fluid == null) {
                    return null;
                }
                List<List<FluidStack>> list = new ArrayList<>();
                for (Extension.VariableData_2<TagKey<Fluid>, Integer> entry : fluid) {
                    List<FluidStack> itemStackList = new ArrayList<>();
                    for (Fluid fluid1 : Dusk.instance.FLUID_TAG.getTag(entry.getKey())) {
                        CompoundTag compoundTag = new CompoundTag();
                        AllNBTPack.MB.set(compoundTag, entry.getValue());
                        itemStackList.add(new FluidStack(fluid1, entry.getValue(), compoundTag));
                    }
                    list.add(itemStackList);
                }
                return list;
            }


            @Nullable
            @Override
            public List<List<ItemStack>> getItemOut() {
                if (outItem == null) {
                    return null;
                }
                List<List<ItemStack>> list = new ArrayList<>();
                for (Extension.VariableData_2<ItemStack, Double> entry : outItem) {
                    CompoundTag compoundTag = new CompoundTag();
                    AllNBTPack.PROBABILITY.set(compoundTag, entry.getValue());
                    ItemStack itemStack = entry.getKey().copy();
                    itemStack.setTag(compoundTag.copy());
                    list.add(Lists.newArrayList(itemStack));
                }
                return list;
            }

            @Nullable
            @Override
            public List<List<FluidStack>> getFluidOut() {
                if (outFluid == null) {
                    return null;
                }
                List<List<FluidStack>> list = new ArrayList<>();
                for (Extension.VariableData_2<FluidStack, Double> entry : outFluid) {
                    CompoundTag compoundTag = new CompoundTag();
                    AllNBTPack.PROBABILITY.set(compoundTag, entry.getValue());
                    AllNBTPack.MB.set(compoundTag, entry.getKey().getAmount());
                    FluidStack fluidStack = entry.getKey().copy();
                    fluidStack.setTag(compoundTag.copy());
                    list.add(Lists.newArrayList(fluidStack));
                }
                return list;
            }
        };
    }

    public ShapedOre addInItem(TagKey<Item> item, Integer i) {
        if (this.item == null) {
            this.item = new ArrayList<>();
        }
        ListUtil.add(this.item, item, i);
        if (itemScreen == null) {
            itemScreen = item;
        }
        return this;
    }

    public ShapedOre addInFluid(TagKey<Fluid> fluid, Integer i) {
        if (this.fluid == null) {
            this.fluid = new ArrayList<>();
        }
        ListUtil.add(this.fluid, fluid, i);
        if (fluidScreen == null) {
            fluidScreen = fluid;
        }
        return this;
    }

    public ShapedOre addOutItem(ItemStack item, Double i) {
        if (this.outItem == null) {
            this.outItem = new ArrayList<>();
        }
        this.outItem.add(new Extension.VariableData_2<>(item, i));
        return this;
    }

    public ShapedOre addOutFluid(FluidStack fluid, Double i) {
        if (this.outFluid == null) {
            this.outFluid = new ArrayList<>();
        }
        this.outFluid.add(new Extension.VariableData_2<>(fluid, i));
        return this;
    }
}
