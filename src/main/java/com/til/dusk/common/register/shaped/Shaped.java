package com.til.dusk.common.register.shaped;

import com.google.common.collect.Lists;
import com.til.dusk.Dusk;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Pos;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.*;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class Shaped extends RegisterBasics<Shaped> {

    public static Supplier<IForgeRegistry<Shaped>> SHAPED;
    public static final Map<ShapedType, Map<ShapedDrive, List<Shaped>>> MAP = new HashMap<>();

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        SHAPED = event.create(new RegistryBuilder<Shaped>().setName(new ResourceLocation(Dusk.MOD_ID, "shaped")));
    }

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
        super(name, SHAPED);
        this.shapedType = shapedType;
        this.shapedDrive = shapedDrive;
        this.manaLevel = manaLevel;
        MAP.computeIfAbsent(shapedType, k -> new HashMap<>()).computeIfAbsent(shapedDrive, k -> new ArrayList<>()).add(this);
        IForgeRegistry<Shaped> iForgeRegistry = SHAPED.get();
        if (iForgeRegistry != null) {
            iForgeRegistry.register(name, this);
        }
        isRegister = true;
        registerSubsidiaryBlack();
    }

    @Override
    public void registerEvent(RegisterEvent event) {
    }

    public abstract ShapedHandle get(IHandle iControl, Map<BlockEntity, IItemHandler> items, Map<BlockEntity, IFluidHandler> fluids);

    /***
     * 获取JEI配方
     */
    public abstract IJEIShaped getJEIShaped();

    public interface IJEIShaped {

        IJEIShaped empty = new IJEIShaped() {
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

    public static class ShapedOre extends Shaped {

        @Nullable
        Map<TagKey<Item>, Integer> item;
        @Nullable
        Map<TagKey<Fluid>, Integer> fluid;
        long surplusTime;
        long consumeMana;
        long outMana;
        @Nullable
        List<ItemStack> outItem;
        @Nullable
        List<FluidStack> outFluid;

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
            this.outItem = outItem;
            this.outFluid = outFluid;
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
            outFluid.forEach(f -> fluidStacks.add(f.copy()));
            return fluidStacks;
        }

        @Nullable
        public List<ItemStack> makeOutItem() {
            if (outItem == null) {
                return null;
            }
            List<ItemStack> itemStackList = new ArrayList<>(outItem.size());
            outItem.forEach(i -> itemStackList.add(i.copy()));
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
                            MinecraftForge.EVENT_BUS.post(new EventIO.Item(iControl.getThis().getLevel(), new Pos(entry.getKey().getBlockPos()),
                                    new Pos(iControl.getThis().getBlockPos()), outItemStack.copy()));
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
                                    new Pos(blockEntityIFluidHandlerEntry.getKey().getBlockPos()),
                                    new Pos(iControl.getThis().getBlockPos()),
                                    out.copy()
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
                            itemStackList.add(new FluidStack(fluid1, v));
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
                    outItem.forEach(i -> list.add(Lists.newArrayList(i)));
                    return list;
                }

                @Nullable
                @Override
                public List<List<FluidStack>> getFluidOut() {
                    if (outFluid == null) {
                        return null;
                    }
                    List<List<FluidStack>> list = new ArrayList<>();
                    outFluid.forEach(i -> list.add(Lists.newArrayList(i)));
                    return list;
                }
            };
        }
    }

    public static class RandOutOreShaped extends ShapedOre {

        public static final String PROBABILITY = "probability";
        public static final String MB = "mb";

        @Nullable
        List<Extension.Data_2<ItemStack, Double>> outItem;
        @Nullable
        List<Extension.Data_2<FluidStack, Double>> outFluid;

        Random random = new Random();

        public RandOutOreShaped(
                ResourceLocation name,
                ShapedType shapedType,
                ShapedDrive shapedDrive,
                ManaLevel manaLevel,
                @Nullable Map<TagKey<Item>, Integer> item,
                @Nullable Map<TagKey<Fluid>, Integer> fluid,
                long surplusTime,
                long consumeMana,
                long outMana,
                @Nullable List<Extension.Data_2<ItemStack, Double>> outItem,
                @Nullable List<Extension.Data_2<FluidStack, Double>> outFluid) {
            super(name, shapedType, shapedDrive, manaLevel, item, fluid, surplusTime, consumeMana, outMana, null, null);
            this.outFluid = outFluid;
            this.outItem = outItem;
        }

        @Nullable
        @Override
        public List<FluidStack> makeOutFluid() {
            if (outFluid == null) {
                return null;
            }
            List<FluidStack> fluidStacks = new ArrayList<>(outFluid.size());
            outFluid.forEach(f -> {
                if (random.nextDouble() < f.b) {
                    fluidStacks.add(f.a.copy());
                }
            });
            return fluidStacks;
        }

        @Nullable
        @Override
        public List<ItemStack> makeOutItem() {
            if (outItem == null) {
                return null;
            }
            List<ItemStack> itemStackList = new ArrayList<>(outItem.size());
            outItem.forEach(f -> {
                if (random.nextDouble() < f.b) {
                    itemStackList.add(f.a.copy());
                }
            });
            return itemStackList;
        }

        @Override
        public IJEIShaped getJEIShaped() {
            IJEIShaped ijeiShaped = super.getJEIShaped();
            return new IJEIShaped() {
                @Nullable
                @Override
                public List<List<ItemStack>> getItemIn() {
                    return ijeiShaped.getItemIn();
                }

                @Nullable
                @Override
                public List<List<FluidStack>> getFluidIn() {
                    return ijeiShaped.getFluidIn();
                }

                @Nullable
                @Override
                public List<List<ItemStack>> getItemOut() {
                    if (outItem == null) {
                        return null;
                    }
                    List<List<ItemStack>> list = new ArrayList<>();
                    outItem.forEach(d -> {
                        CompoundTag compoundTag = new CompoundTag();
                        compoundTag.putDouble(PROBABILITY, d.b);
                        ItemStack itemStack = d.a.copy();
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
                    outFluid.forEach(d -> {
                        CompoundTag compoundTag = new CompoundTag();
                        compoundTag.putDouble(PROBABILITY, d.b);
                        compoundTag.putDouble(MB, d.a.getAmount());
                        FluidStack fluidStack = d.a.copy();
                        fluidStack.setTag(compoundTag.copy());
                        list.add(Lists.newArrayList(fluidStack));
                    });
                    return list;
                }
            };
        }
    }
}
