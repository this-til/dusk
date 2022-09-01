package com.til.dusk.common.data.shaped;

import com.google.common.collect.Lists;
import com.til.dusk.Dusk;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.Lang;
import com.til.dusk.util.Pos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.*;

import javax.annotation.Nullable;
import java.util.*;

public abstract class Shaped {

    public final ResourceLocation name;

    /***
     * 配方类型
     */
    public final ShapedType shapedType;

    /***
     * 配方id
     */
    public final ShapedDrive shapedDrive;

    /***
     * 配方加工等级
     */
    public final ManaLevel manaLevel;


    public Shaped(ResourceLocation name, ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel) {
        this.name = name;
        this.shapedType = shapedType;
        this.shapedDrive = shapedDrive;
        this.manaLevel = manaLevel;
        ModShaped.add(this);
    }


    @Nullable
    public abstract ShapedHandle get(IHandle iControl, Map<BlockEntity, IItemHandler> items, Map<BlockEntity, IFluidHandler> fluids);

    /***
     * 获取JEI配方
     */
    public abstract IJEIShaped getJEIShaped();

    public abstract List<Component> getComponent();

    /***
     * 在JEI中显示
     */
    public boolean isJEIShow() {
        return true;
    }

    public interface IJEIShaped {

        IJEIShaped EMPTY = new IJEIShaped() {
        };

        @Nullable
        default List<List<ItemStack>> getItemIn() {
            return null;
        }

        @Nullable
        default List<List<FluidStack>> getFluidIn() {
            return null;
        }

        @Nullable
        default List<List<ItemStack>> getItemOut() {
            return null;
        }

        @Nullable
        default List<List<FluidStack>> getFluidOut() {
            return null;
        }

    }

    @Override
    public String toString() {
        return name.toString();
    }

    public static class ShapedOre extends Shaped {

        @Nullable
        Map<TagKey<Item>, Integer> item;
        @Nullable
        Map<TagKey<Fluid>, Integer> fluid;
        long surplusTime;
        long consumeMana;
        long outMana;
        @Nullable
        Map<ItemStack, Double> outItem;
        @Nullable
        Map<FluidStack, Double> outFluid;

        Random random = new Random();

        public ShapedOre(ResourceLocation name, ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel,
                         @Nullable Map<TagKey<Item>, Integer> item, @Nullable Map<TagKey<Fluid>, Integer> fluid,
                         long surplusTime, long consumeMana, long outMana,
                         @Nullable Map<ItemStack, Double> outItem, @Nullable Map<FluidStack, Double> outFluid) {
            super(name, shapedType, shapedDrive, manaLevel);
            this.item = item;
            this.fluid = fluid;
            this.surplusTime = surplusTime;
            this.consumeMana = consumeMana;
            this.outMana = outMana;
            this.outItem = outItem;
            this.outFluid = outFluid;
        }

        public ShapedOre(ResourceLocation name, ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel,
                         @Nullable Map<TagKey<Item>, Integer> item, @Nullable Map<TagKey<Fluid>, Integer> fluid,
                         long surplusTime, long consumeMana, long outMana,
                         @Nullable List<ItemStack> outItem, @Nullable List<FluidStack> outFluid) {
            super(name, shapedType, shapedDrive, manaLevel);
            this.item = item;
            this.fluid = fluid;
            this.surplusTime = surplusTime;
            this.consumeMana = consumeMana;
            this.outMana = outMana;
            if (outItem != null) {
                this.outItem = new HashMap<>(outItem.size());
                for (ItemStack itemStack : outItem) {
                    this.outItem.put(itemStack, 1d);
                }
            }
            if (outFluid != null) {
                this.outFluid = new HashMap<>(outFluid.size());
                for (FluidStack fluidStack : outFluid) {
                    this.outFluid.put(fluidStack, 1d);
                }
            }
        }

        @Override
        public ShapedHandle get(IHandle iControl, Map<BlockEntity, IItemHandler> iItemHandlers, Map<BlockEntity, IFluidHandler> fluidHandlers) {
            if (item != null && iItemHandlers.isEmpty()) {
                return null;
            }
            if (fluid != null && fluidHandlers.isEmpty()) {
                return null;
            }
            if (extractFluid(iControl, fluidHandlers, IFluidHandler.FluidAction.SIMULATE)) {
                if (item != null) {
                    for (java.util.Map.Entry<BlockEntity, IItemHandler> tileEntityIItemHandlerEntry : iItemHandlers.entrySet()) {
                        if (extractItem(iControl, tileEntityIItemHandlerEntry, true)) {
                            extractFluid(iControl, fluidHandlers, IFluidHandler.FluidAction.EXECUTE);
                            extractItem(iControl, tileEntityIItemHandlerEntry, false);
                            return new ShapedHandle(surplusTime, consumeMana, outMana, makeOutItem(), makeOutFluid());
                        }
                    }
                } else {
                    extractFluid(iControl, fluidHandlers, IFluidHandler.FluidAction.EXECUTE);
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

        protected boolean extractItem(IHandle iControl, java.util.Map.Entry<BlockEntity, IItemHandler> entry, boolean isSimulated) {
            if (item == null) {
                return true;
            }
            for (Map.Entry<TagKey<Item>, Integer> tagKeyIntegerEntry : item.entrySet()) {
                int needItem = tagKeyIntegerEntry.getValue();
                for (int i = 0; i < entry.getValue().getSlots(); i++) {
                    ItemStack oldItemStack = entry.getValue().getStackInSlot(i);
                    if (oldItemStack.isEmpty()) {
                        continue;
                    }
                    if (oldItemStack.is(tagKeyIntegerEntry.getKey())) {
                        ItemStack outItemStack = entry.getValue().extractItem(i, needItem, isSimulated);
                        needItem = needItem - outItemStack.getCount();
                        if (!isSimulated) {
                            MinecraftForge.EVENT_BUS.post(new EventIO.Item(iControl.getThis().getLevel(), outItemStack.copy(), new Pos(entry.getKey().getBlockPos()),
                                    new Pos(iControl.getThis().getBlockPos())));
                        }
                    }
                    if (needItem == 0) {
                        break;
                    }
                }
                if (needItem > 0) {
                    return false;
                }
            }
            return true;
        }

        protected boolean extractFluid(IHandle iControl, Map<BlockEntity, IFluidHandler> fluidHandlers, IFluidHandler.FluidAction isSimulated) {
            if (fluid == null) {
                return true;
            }
            for (Map.Entry<TagKey<Fluid>, Integer> tagKeyIntegerEntry : fluid.entrySet()) {
                int need = tagKeyIntegerEntry.getValue();
                for (Map.Entry<BlockEntity, IFluidHandler> blockEntityIFluidHandlerEntry : fluidHandlers.entrySet()) {
                    for (Fluid fluid1 : ForgeRegistries.FLUIDS.tags().getTag(tagKeyIntegerEntry.getKey())) {
                        FluidStack fluidStack = new FluidStack(fluid1, need);
                        FluidStack out = blockEntityIFluidHandlerEntry.getValue().drain(fluidStack, isSimulated);
                        need = need - out.getAmount();
                        if (isSimulated.execute()) {
                            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(
                                    iControl.getThis().getLevel(),
                                    out.copy(),
                                    new Pos(blockEntityIFluidHandlerEntry.getKey().getBlockPos()),
                                    new Pos(iControl.getThis().getBlockPos())
                            ));
                        }
                        if (need == 0) {
                            break;
                        }
                    }
                }
                if (need > 0) {
                    return false;
                }

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
                        for (Fluid fluid1 : ForgeRegistries.FLUIDS.tags().getTag(k)) {
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

        @Override
        public List<Component> getComponent() {
            List<Component> componentList = new ArrayList<>();
            componentList.add(Component.literal("message"));
            componentList.add(Lang.getLang(Lang.getKey("需要灵压等级"), Lang.getKey(manaLevel)));
            componentList.add(Lang.getLang(Lang.getKey("需要配方集"), shapedDrive.getLangKey()));
            if (consumeMana > 0) {
                componentList.add(Lang.getLang(Lang.getKey("消耗灵气"), String.valueOf(consumeMana)));
            }
            if (surplusTime > 0) {
                componentList.add(Lang.getLang(Lang.getKey("消耗时间"), String.valueOf(surplusTime)));
            }
            if (outMana > 0) {
                componentList.add(Lang.getLang(Lang.getKey("输出灵气"), String.valueOf(outMana)));
            }
            return componentList;
        }
    }

    public static class EmptyShaped extends Shaped {
        public EmptyShaped() {
            super(new ResourceLocation(Dusk.MOD_ID, "empty"), null, null, null);
        }

        @org.jetbrains.annotations.Nullable
        @Override
        public ShapedHandle get(IHandle iControl, Map<BlockEntity, IItemHandler> items, Map<BlockEntity, IFluidHandler> fluids) {
            return null;
        }

        @Override
        public IJEIShaped getJEIShaped() {
            return null;
        }

        @Override
        public List<Component> getComponent() {
            return null;
        }

        @Override
        public boolean isJEIShow() {
            return false;
        }
    }
}
