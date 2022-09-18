package com.til.dusk.common.register.shaped.shapeds;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.til.dusk.Dusk;
import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.Extension;
import com.til.dusk.util.GenericMap;
import com.til.dusk.util.MapUtil;
import com.til.dusk.util.RoutePack;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.pack.DataPack;
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
import java.util.function.Supplier;

public class ShapedOre extends ShapedMiddle {

    @Nullable
    public Map<TagKey<Item>, Integer> item;
    @Nullable
    public Map<TagKey<Fluid>, Integer> fluid;
    @Nullable
    public Map<ItemStack, Double> outItem;
    @Nullable
    public Map<FluidStack, Double> outFluid;

    Random random = new Random();

    public ShapedOre(ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel) {
        super(shapedType, shapedDrive, manaLevel);
        this.item = new HashMap<>();
        this.fluid = new HashMap<>();
        this.outItem = new HashMap<>();
        this.outFluid = new HashMap<>();
    }

    public ShapedOre(ResourceLocation name, JsonObject jsonObject) throws Exception {
        super(name, jsonObject);
        if (AllNBTPack.ITEM_IN_MAP.contains(jsonObject)) {
            item = AllNBTPack.ITEM_IN_MAP.get(jsonObject);
        } else {
            item = null;
        }
        if (AllNBTPack.FLUID_IN_MAP.contains(jsonObject)) {
            fluid = AllNBTPack.FLUID_IN_MAP.get(jsonObject);
        } else {
            fluid = null;
        }
        if (AllNBTPack.ITEM_OUT_MAP.contains(jsonObject)) {
            outItem = AllNBTPack.ITEM_OUT_MAP.get(jsonObject);
        } else {
            outItem = null;
        }
        if (AllNBTPack.FLUID_OUT_MAP.contains(jsonObject)) {
            outFluid = AllNBTPack.FLUID_OUT_MAP.get(jsonObject);
        } else {
            outFluid = null;
        }
    }

    @Override
    public @Nullable JsonObject writ(JsonObject jsonObject) {
        super.writ(jsonObject);
        if (item != null && !item.isEmpty()) {
            AllNBTPack.ITEM_IN_MAP.set(jsonObject, item);
        }
        if (fluid != null && !fluid.isEmpty()) {
            AllNBTPack.FLUID_IN_MAP.set(jsonObject, fluid);
        }
        if (outItem != null && !outItem.isEmpty()) {
            AllNBTPack.ITEM_OUT_MAP.set(jsonObject, outItem);
        }
        if (outFluid != null && !outFluid.isEmpty()) {
            AllNBTPack.FLUID_OUT_MAP.set(jsonObject, outFluid);
        }
        return jsonObject;
    }

    @Override
    public ShapedHandle get(IHandle iControl, Map<IPosTrack, IItemHandler> iItemHandlers, Map<IPosTrack, IFluidHandler> fluidHandlers) {
        if (item != null && iItemHandlers.isEmpty()) {
            return null;
        }
        if (fluid != null && fluidHandlers.isEmpty()) {
            return null;
        }
        if (extractFluid(iControl, fluidHandlers, true)) {
            if (item != null) {
                for (Map.Entry<IPosTrack, IItemHandler> entry : iItemHandlers.entrySet()) {
                    if (extractItem(iControl, entry, true)) {
                        extractFluid(iControl, fluidHandlers, false);
                        extractItem(iControl, entry, false);
                        return new ShapedHandle(surplusTime, consumeMana, outMana, makeOutItem(), makeOutFluid());
                    }
                }
            } else {
                extractFluid(iControl, fluidHandlers, false);
                return new ShapedHandle(surplusTime, consumeMana, outMana, makeOutItem(), makeOutFluid());
            }
        }
        return null;
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
                    ItemStack outItemStack = CapabilityHelp.extractItem(iControl.getPosTrack(), routePack, entry.getValue(), entry.getKey().getPos(), i, needItem, isSimulated);
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
        assert this.item != null;
        MapUtil.add(this.item, item, i);
        return this;
    }

    public ShapedOre addInFluid(TagKey<Fluid> fluid, Integer i) {
        assert this.fluid != null;
        MapUtil.add(this.fluid, fluid, i);
        return this;
    }

    public ShapedOre addOutItem(ItemStack item, Double i) {
        assert this.outItem != null;
        this.outItem.put(item, i);
        return this;
    }

    public ShapedOre addOutFluid(FluidStack fluid, Double i) {
        assert this.outFluid != null;
        this.outFluid.put(fluid, i);
        return this;
    }

    public ShapedOre runThis(Extension.Action_1V<ShapedOre> run) {
        run.action(this);
        return this;
    }

    public <OTHER_DATA> ShapedOre runThis(GenericMap.IKey<? extends Supplier<? extends DataPack<?, OTHER_DATA>>> key, GenericMap.IGenericMapSupplier supplier, OTHER_DATA otherData) {
        if (supplier.hasSet(key)) {
            supplier.getSet(key).get().run(this, otherData);
        }
        return this;
    }
}
