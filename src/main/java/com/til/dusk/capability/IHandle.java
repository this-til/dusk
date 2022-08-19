package com.til.dusk.capability;


import com.google.gson.JsonObject;
import com.til.dusk.particle.CommonParticle;
import com.til.dusk.register.*;
import com.til.dusk.util.AllNBT;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Pos;
import com.til.dusk.util.data.MessageData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.*;
import java.util.List;

public interface IHandle extends IControl, INBT, IThis<BlockEntity>, ITack, IManaLevel, IClockTime {

    /***
     * 获取所有的配方
     */
    List<ShapedType> getShapedTypes();

    void addShapedHandle(ShapedHandle shaped);

    /***
     * 获取最大配方并行
     */
    int getParallelHandle();

    List<ShapedDrive> getShapedDrive();

    class Handle implements IHandle {

        public final List<ShapedType> shapedTypes;
        public final BlockEntity tileEntity;
        public final IControl iControl;
        public final IManaLevel iManaLevel;
        public final IClockTime iClockTime;

        /***
         * 正在生产、输出的配方
         */
        public List<ShapedHandle> shapedHandles = new ArrayList<>();

        public Handle(BlockEntity tileEntity, List<ShapedType> shapedTypes, List<BindType> bindTypes, IControl iControl, IManaLevel iManaLevel, IClockTime iClockTime) {
            this.shapedTypes = shapedTypes;
            this.tileEntity = tileEntity;
            this.iControl = iControl;
            this.iManaLevel = iManaLevel;
            this.iClockTime = iClockTime;
        }

        /***
         * 获取所有的配方
         */
        @Override
        public List<ShapedType> getShapedTypes() {
            return shapedTypes;
        }

        @Override
        public void addShapedHandle(ShapedHandle shaped) {
            shapedHandles.add(shaped);
        }

        @Override
        public List<ShapedDrive> getShapedDrive() {
            List<ShapedDrive> list = new ArrayList<>();
            getCapability(BindType.modelStore).forEach((k, v) -> list.addAll(v.get()));
            return list;
        }

        @Override
        public void time() {
            Map<BlockEntity, IManaHandle> manaIn = getCapability(BindType.manaIn);
            Map<BlockEntity, IManaHandle> manaOut = getCapability(BindType.manaOut);
            shapedHandles.forEach(shapedHandle -> shapedHandle.up(this, manaIn, manaOut));
        }

        /***
         * 回调
         */
        @Override
        public void clockTriggerRun() {
            Map<BlockEntity, IItemHandler> itemIn = getCapability(BindType.itemIn);
            Map<BlockEntity, IItemHandler> itemOut = getCapability(BindType.itemOut);
            Map<BlockEntity, IFluidHandler> fluidIn = getCapability(BindType.fluidIn);
            Map<BlockEntity, IFluidHandler> fluidOut = getCapability(BindType.fluidOut);

            shapedHandles.forEach(shapedHandle -> shapedHandle.clockTime(this, itemOut, fluidOut));

            List<ShapedHandle> rShapedHandle = new ArrayList<>();
            shapedHandles.forEach(h -> {
                if (h.isEmpty()) {
                    rShapedHandle.add(h);
                }
            });
            rShapedHandle.forEach(r -> shapedHandles.remove(r));

            if (shapedHandles.size() >= getParallelHandle()) {
                return;
            }

            List<ShapedDrive> shapedDrives = getShapedDrive();

            List<Shaped> shapeds = new ArrayList<>();
            List<Shaped> rShaped = new ArrayList<>();

            Shaped.MAP.forEach((k, v) -> {
                if (shapedTypes.contains(k)) {
                    v.forEach((_k, _v) -> {
                        if (shapedDrives.contains(_k)) {
                            shapeds.addAll(_v);
                        }
                    });
                }
            });

            ShapedHandle shapedHandle;
            do {
                shapedHandle = null;
                if (!rShaped.isEmpty()) {
                    shapeds.removeAll(rShaped);
                    rShaped.clear();
                }

                for (Shaped shaped : shapeds) {
                    shapedHandle = shaped.get(this, getManaLevel(), itemIn, fluidIn);
                    if (shapedHandle != null) {
                        addShapedHandle(shapedHandle);
                        break;
                    } else {
                        rShaped.add(shaped);
                    }
                }
            }
            while (shapedHandle != null && shapedHandles.size() < getParallelHandle());
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            shapedHandles.clear();
            for (Tag nbtBase : nbt.getList("shapedHandles", 10)) {
                if (nbtBase instanceof CompoundTag) {
                    shapedHandles.add(new ShapedHandle((CompoundTag) nbtBase));
                }
            }
            setClockTime(nbt.getInt("clockTime"));
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag nbtTagCompound = new CompoundTag();
            ListTag nbtBases = new ListTag();
            for (ShapedHandle shapedHandle : shapedHandles) {
                nbtBases.add(shapedHandle.serializeNBT());
            }
            nbtTagCompound.put("shapedHandles", nbtBases);
            nbtTagCompound.putInt("clockTime", getClockTime());
            return nbtTagCompound;
        }

        @Override
        public int getParallelHandle() {
            return getManaLevel().level;
        }

        /***
         * 返回自己
         */
        @Override
        public BlockEntity getThis() {
            return tileEntity;
        }

        @Override
        public AllNBT.IGS<Tag> getNBTBase() {
            return AllNBT.iHandleNBT;
        }

        @Override
        public ManaLevel getManaLevel() {
            return iManaLevel.getManaLevel();
        }

        @Override
        public void unBundlingAll() {
            iControl.unBundlingAll();
        }

        /***
         * 获得可以绑定的类型
         */
        @Override
        public List<BindType> getCanBindType() {
            return iControl.getCanBindType();
        }

        @Override
        public MessageData binding(BlockEntity tileEntity, BindType iBindType) {
            return iControl.binding(tileEntity, iBindType);
        }

        @Override
        public MessageData unBindling(BlockEntity tileEntity, BindType iBindType) {
            return iControl.unBindling(tileEntity, iBindType);
        }

        @Override
        public boolean hasBundling(BlockEntity tileEntity, BindType bindType) {
            return iControl.hasBundling(tileEntity, bindType);
        }

        @Override
        public List<BlockEntity> getAllTileEntity(BindType iBindType) {
            return iControl.getAllTileEntity(iBindType);
        }

        @Override
        public <C> Map<BlockEntity, C> getCapability(Capability<C> capability, BindType iBindType) {
            return iControl.getCapability(capability, iBindType);
        }

        @Override
        public <C> Map<BlockEntity, C> getCapability(BindType.BindTypeBindCapability<C> bundTypeBindCapability) {
            return iControl.getCapability(bundTypeBindCapability);
        }

        @Override
        public int getMaxRange() {
            return iControl.getMaxRange();
        }

        @Override
        public int getMaxBind() {
            return iControl.getMaxBind();
        }

        @Override
        public int getClockTime() {
            return iClockTime.getClockTime();
        }

        @Override
        public int getCycleTime() {
            return iClockTime.getCycleTime();
        }

        @Override
        public void setClockTime(int clockTime) {
            iClockTime.setClockTime(clockTime);
        }
    }

    class ShapedHandle {

        public static final String SURPLUS_TIME = "surplusTime";
        public static final String CONSUME_MANA = "consumeMana";
        public static final String _SURPLUS_TIME = "_surplusTime";
        public static final String PROCESS = "process";
        public static final String OUT_ITEM = "outItem";
        public static final String OUT_FLUID = "outFluid";
        public static final String OUT_MANA = "outMana";


        public final long consumeMana;

        public final long surplusTime;
        public long _surplusTime;

        public Process process;

        @Nullable
        public List<ItemStack> outItem;
        @Nullable
        public List<FluidStack> outFluid;
        public long outMana;

        public ShapedHandle(long surplusTime, long consumeMana, long outMana, @Nullable List<ItemStack> outItemStack, @Nullable List<FluidStack> outFluid) {
            this.surplusTime = surplusTime;
            this.consumeMana = consumeMana;
            this.outMana = outMana;
            this.outItem = outItemStack;
            this.outFluid = outFluid;
            this._surplusTime = surplusTime;
            process = Process.production;
        }

        public ShapedHandle(CompoundTag nbtTagCompound) {
            surplusTime = nbtTagCompound.getLong(SURPLUS_TIME);
            consumeMana = nbtTagCompound.getLong(CONSUME_MANA);
            _surplusTime = nbtTagCompound.getLong(_SURPLUS_TIME);
            process = Process.MAP.get(nbtTagCompound.getString(PROCESS));
            if (process == null) {
                process = Process.production;
            }

            if (nbtTagCompound.contains(OUT_ITEM)) {
                outItem = new ArrayList<>();
                for (Tag nbtBase : nbtTagCompound.getList(OUT_ITEM, 10)) {
                    if (nbtBase instanceof CompoundTag _n) {
                        ItemStack itemStack = ItemStack.of(_n);
                        if (!itemStack.isEmpty()) {
                            outItem.add(ItemStack.of(_n));
                        }
                    }
                }
            }

            if (nbtTagCompound.contains(OUT_FLUID)) {
                outFluid = new ArrayList<>();
                for (Tag nbtBase : nbtTagCompound.getList(OUT_FLUID, 10)) {
                    if (nbtBase instanceof CompoundTag _n) {
                        FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(_n);
                        if (fluidStack.getFluid() != null && fluidStack.getAmount() > 0) {
                            outFluid.add(fluidStack);
                        }
                    }
                }
            }
            outMana = nbtTagCompound.getLong(OUT_MANA);
        }

        public CompoundTag serializeNBT() {
            CompoundTag nbtTagCompound = new CompoundTag();

            nbtTagCompound.putLong(SURPLUS_TIME, surplusTime);
            nbtTagCompound.putLong(CONSUME_MANA, consumeMana);
            nbtTagCompound.putLong(_SURPLUS_TIME, _surplusTime);
            nbtTagCompound.putString("process", PROCESS.toString());

            if (outItem != null && !outItem.isEmpty()) {
                ListTag _outItem = new ListTag();
                for (ItemStack itemStack : outItem) {
                    _outItem.add(itemStack.serializeNBT());
                }
                nbtTagCompound.put(OUT_ITEM, _outItem);
            }

            if (outFluid != null && !outFluid.isEmpty()) {
                ListTag _outFuid = new ListTag();
                for (FluidStack fluidStack : outFluid) {
                    _outFuid.add(fluidStack.writeToNBT(new CompoundTag()));
                }
                nbtTagCompound.put(OUT_FLUID, _outFuid);
            }

            nbtTagCompound.putLong(OUT_MANA, outMana);

            return nbtTagCompound;
        }

        public void up(IHandle iControl, Map<BlockEntity, IManaHandle> inMana, Map<BlockEntity, IManaHandle> outMana) {
            Extension.Action_4V<IHandle, ShapedHandle, Map<BlockEntity, IManaHandle>, Map<BlockEntity, IManaHandle>> shapedHandleListListAction_4V = upRun.get(PROCESS);
            if (shapedHandleListListAction_4V != null) {
                shapedHandleListListAction_4V.action(iControl, this, inMana, outMana);
            }
        }

        public void clockTime(IHandle iControl, Map<BlockEntity, IItemHandler> outItem, Map<BlockEntity, IFluidHandler> outFluid) {
            Extension.Action_4V<IHandle, ShapedHandle, Map<BlockEntity, IItemHandler>, Map<BlockEntity, IFluidHandler>> shapedHandleListListAction_4V = clockTimeRun.get(PROCESS);
            if (shapedHandleListListAction_4V != null) {
                shapedHandleListListAction_4V.action(iControl, this, outItem, outFluid);
            }
        }

        public boolean isEmpty() {
            return (outItem == null || outItem.isEmpty()) && (OUT_FLUID == null || OUT_FLUID.isEmpty()) && outMana == 0;
        }

        public enum Process {
            production, out, trippingOperation;

            public static final Map<String, Process> MAP = new HashMap<>();

            static {
                for (Process value : values()) {
                    MAP.put(value.toString(), value);
                }
            }
        }

        public final static Map<Process, Extension.Action_4V<IHandle, ShapedHandle, Map<BlockEntity, IManaHandle>, Map<BlockEntity, IManaHandle>>> upRun = new HashMap<>();

        public final static Random rand = new Random();

        static {
            upRun.put(Process.production, (c, shapedHandle, inMana, outMana) -> {
                long needGetMana = shapedHandle.consumeMana;
                for (java.util.Map.Entry<BlockEntity, IManaHandle> tileEntityIManaHandleEntry : inMana.entrySet()) {
                    long mana = tileEntityIManaHandleEntry.getValue().extractMana(needGetMana);
                    needGetMana = needGetMana - mana;
                    if (rand.nextFloat() < mana / 320f) {
                        CommonParticle.MANA_TRANSFER.add(c.getThis().getLevel(), new Pos(tileEntityIManaHandleEntry.getKey().getBlockPos()), new Pos(c.getThis().getBlockPos()), new JsonObject());
                    }
                }
                if (needGetMana <= 0) {
                    shapedHandle._surplusTime--;
                    if (shapedHandle._surplusTime <= 0) {
                        shapedHandle.process = Process.out;
                    }
                } else {
                    shapedHandle.process = Process.trippingOperation;
                    shapedHandle._surplusTime = shapedHandle.surplusTime;
                }
            });
            upRun.put(Process.out, (c, shapedHandle, inMana, outMana) -> {
                if (shapedHandle.outMana > 0) {
                    for (java.util.Map.Entry<BlockEntity, IManaHandle> tileEntityIManaHandleEntry : outMana.entrySet()) {
                        long mana = tileEntityIManaHandleEntry.getValue().addMana(shapedHandle.outMana);
                        shapedHandle.outMana = shapedHandle.outMana - mana;
                        if (rand.nextFloat() < mana / 320f) {
                            CommonParticle.MANA_TRANSFER.add(c.getThis().getLevel(), new Pos(c.getThis().getBlockPos()), new Pos(tileEntityIManaHandleEntry.getKey().getBlockPos()), new JsonObject());
                        }
                    }
                }
            });
        }

        public final static Map<Process, Extension.Action_4V<IHandle, ShapedHandle, Map<BlockEntity, IItemHandler>, Map<BlockEntity, IFluidHandler>>> clockTimeRun = new HashMap<>();

        static {
            clockTimeRun.put(Process.trippingOperation, (c, shapedHandle, iItemHandlers, iFluidHandlers) -> shapedHandle.process = Process.production);
            clockTimeRun.put(Process.out, (c, shapedHandle, iItemHandlers, iFluidHandlers) -> {

                if (shapedHandle.outItem != null && !shapedHandle.outItem.isEmpty()) {
                    List<ItemStack> nItemStack = new ArrayList<>();
                    for (ItemStack itemStack : shapedHandle.outItem) {
                        ItemStack needOut = itemStack;
                        for (Map.Entry<BlockEntity, IItemHandler> tileEntityIItemHandlerEntry : iItemHandlers.entrySet()) {
                            ItemStack out = ItemHandlerHelper.insertItemStacked(tileEntityIItemHandlerEntry.getValue(), needOut, false);
                            if (out.getCount() < needOut.getCount()) {
                                CommonParticle.ITEM_TRANSFER.add(c.getThis().getLevel(), new Pos(c.getThis().getBlockPos()), new Pos(tileEntityIItemHandlerEntry.getKey().getBlockPos()), new JsonObject());
                            }
                            needOut = out;
                            if (needOut.isEmpty()) {
                                break;
                            }
                        }
                        if (!needOut.isEmpty()) {
                            nItemStack.add(needOut);
                        }
                    }
                    shapedHandle.outItem = nItemStack;
                }

                if (shapedHandle.outFluid != null && !shapedHandle.outFluid.isEmpty()) {
                    List<FluidStack> nFluidStack = new ArrayList<>();
                    for (FluidStack fluidStack : shapedHandle.outFluid) {
                        FluidStack needOut = fluidStack.copy();
                        for (java.util.Map.Entry<BlockEntity, IFluidHandler> tileEntityIFluidHandlerEntry : iFluidHandlers.entrySet()) {
                            int surplus = tileEntityIFluidHandlerEntry.getValue().fill(needOut, IFluidHandler.FluidAction.EXECUTE);
                            if (surplus > 0) {
                                JsonObject jsonObject = new JsonObject();
                                //Color color = new Color(needOut.getFluid().getColor());
                                //jsonObject.add("rgb", new Pos(color.getRed() / 255d, color.getGreen() / 255d, color.getBlue() / 255d).getJson());
                                for (int i = 0; i < surplus / 32; i++) {
                                    CommonParticle.FLUID_TRANSFER.add(c.getThis().getLevel(), new Pos(c.getThis().getBlockPos()), new Pos(tileEntityIFluidHandlerEntry.getKey().getBlockPos()), jsonObject);
                                }
                            }
                            needOut.setAmount(needOut.getAmount() - surplus);
                            if (needOut.getAmount() <= 0) {
                                break;
                            }
                        }
                        if (needOut.getAmount() > 0) {
                            nFluidStack.add(needOut);
                        }
                    }
                    shapedHandle.outFluid = nFluidStack;
                }

            });
        }

        public static class EmptyShapedHandle extends ShapedHandle {
            public EmptyShapedHandle(long surplusTime, long consumeMana, long outMana) {
                super(surplusTime, consumeMana, outMana, null, null);
                process = Process.out;
            }

            @Override
            public boolean isEmpty() {
                return true;
            }
        }
    }


}
