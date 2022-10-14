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
    public Map<TagKey<Item>, Integer> item;
    @Nullable
    public Map<TagKey<Fluid>, Integer> fluid;
    @Nullable
    public Map<ItemStack, Double> outItem;
    @Nullable
    public Map<FluidStack, Double> outFluid;
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
        for (Map.Entry<FluidStack, Double> entry : outFluid.entrySet()) {
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
        for (Map.Entry<ItemStack, Double> entry : outItem.entrySet()) {
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
        for (Map.Entry<TagKey<Item>, Integer> tagKeyIntegerEntry : item.entrySet()) {
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
        for (Map.Entry<TagKey<Fluid>, Integer> tagKeyIntegerEntry : fluid.entrySet()) {
            int need = tagKeyIntegerEntry.getValue();
            for (Fluid fluid1 : Dusk.instance.FLUID_TAG.getTag(tagKeyIntegerEntry.getKey())) {
                FluidStack fluidStack = new FluidStack(fluid1, need);
                FluidStack out = CapabilityHelp.drain(iControl.getPosTrack(), routePack, fluidHandlers, fluidStack, isSimulated);
                need -= out.getAmount();
                if (need == 0) {
                    break;
                }
            }
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
                item.forEach((k, v) -> {
                    List<ItemStack> itemStackList = new ArrayList<>();
                    for (Item item1 : ForgeRegistries.ITEMS.tags().getTag(k)) {
                        itemStackList.add(new ItemStack(item1, v));
                    }
                    list.add(itemStackList);
                });
                return list;

            }

            @Nullable
            @Override
            public List<List<FluidStack>> getFluidIn() {
                if (fluid == null) {
                    return null;
                }
                List<List<FluidStack>> list = new ArrayList<>();
                fluid.forEach((k, v) -> {
                    List<FluidStack> itemStackList = new ArrayList<>();
                    for (Fluid fluid1 : Dusk.instance.FLUID_TAG.getTag(k)) {
                        CompoundTag compoundTag = new CompoundTag();
                        AllNBTPack.MB.set(compoundTag, v);
                        itemStackList.add(new FluidStack(fluid1, v, compoundTag));
                    }
                    list.add(itemStackList);
                });
                return list;
            }


            @Nullable
            @Override
            public List<List<ItemStack>> getItemOut() {
                if (outItem == null) {
                    return null;
                }
                List<List<ItemStack>> list = new ArrayList<>();
                outItem.forEach((k, v) -> {
                    CompoundTag compoundTag = new CompoundTag();
                    AllNBTPack.PROBABILITY.set(compoundTag, v);
                    ItemStack itemStack = k.copy();
                    itemStack.setTag(compoundTag.copy());
                    list.add(Lists.newArrayList(itemStack));
                });
                return list;
            }

            @Nullable
            @Override
            public List<List<FluidStack>> getFluidOut() {
                if (outFluid == null) {
                    return null;
                }
                List<List<FluidStack>> list = new ArrayList<>();
                outFluid.forEach((k, v) -> {
                    CompoundTag compoundTag = new CompoundTag();
                    AllNBTPack.PROBABILITY.set(compoundTag, v);
                    AllNBTPack.MB.set(compoundTag, k.getAmount());
                    FluidStack fluidStack = k.copy();
                    fluidStack.setTag(compoundTag.copy());
                    list.add(Lists.newArrayList(fluidStack));
                });
                return list;
            }
        };
    }

    public ShapedOre addInItem(TagKey<Item> item, Integer i) {
        if (this.item == null) {
            this.item = new HashMap<>();
        }
        MapUtil.add(this.item, item, i);
        if (itemScreen == null) {
            itemScreen = item;
        }
        return this;
    }

    public ShapedOre addInFluid(TagKey<Fluid> fluid, Integer i) {
        if (this.fluid == null) {
            this.fluid = new HashMap<>();
        }
        MapUtil.add(this.fluid, fluid, i);
        if (fluidScreen == null) {
            fluidScreen = fluid;
        }
        return this;
    }

    public ShapedOre addOutItem(ItemStack item, Double i) {
        if (this.outItem == null) {
            this.outItem = new HashMap<>();
        }
        this.outItem.put(item, i);
        return this;
    }

    public ShapedOre addOutFluid(FluidStack fluid, Double i) {
        if (this.outFluid == null) {
            this.outFluid = new HashMap<>();
        }
        this.outFluid.put(fluid, i);
        return this;
    }
}
