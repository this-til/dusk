package com.til.dusk.common.capability.handle;

import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.block_attribute.IBlockAttribute;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.bind_type.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.util.Lang;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * @author til
 */
public class Handle implements IHandle {

    public final IPosTrack posTrack;
    public final Set<Shaped> shapedList;
    public final IControl iControl;
    public final IBlockAttribute iBlockAttribute;
    public final IClock iClock;
    public final IBack iBack;
    public final int maxParallel;
    public List<ShapedHandle> shapedHandles = new ArrayList<>();

    public Handle(IPosTrack iPosTrack, Set<Shaped> shapedTypes, IControl iControl, IBlockAttribute iBlockAttribute, IClock iClock, IBack iBack, int maxParallel) {
        this.shapedList = shapedTypes;
        this.posTrack = iPosTrack;
        this.iControl = iControl;
        this.iBlockAttribute = iBlockAttribute;
        this.iClock = iClock;
        this.iBack = iBack;
        this.maxParallel = maxParallel;
        iClock.addBlock(this::clockTriggerRun);
        iBack.add(IBack.UP, v -> up());
    }

    public Handle(IPosTrack iPosTrack, Set<ShapedType> shapedTypes, IControl iControl, IBlockAttribute iBlockAttribute, IClock iClock, IBack iBack, ManaLevel maxParallel) {
        this.shapedList = Shaped.get(shapedTypes.toArray(new ShapedType[0]));
        this.posTrack = iPosTrack;
        this.iControl = iControl;
        this.iBlockAttribute = iBlockAttribute;
        this.iClock = iClock;
        this.iBack = iBack;
        this.maxParallel = maxParallel.parallel;
        iClock.addBlock(this::clockTriggerRun);
        iBack.add(IBack.UP, v -> up());
    }

    @Override

    public int getParallelHandle() {
        return maxParallel;
    }

    @Override
    public IBlockAttribute getBlockAttribute() {
        return iBlockAttribute;
    }

    @Override
    public IControl getControl() {
        return iControl;
    }

    @Override
    public IClock getClockTime() {
        return iClock;
    }

    @Override
    public Set<Shaped> getShaped() {
        return shapedList;
    }

    @Override
    public void addShapedHandle(ShapedHandle shaped) {
        shapedHandles.add(shaped);
        MinecraftForge.EVENT_BUS.post(new EventHandle.EventShapedHandle.AddShaped(this, shaped));
    }

    @Override
    public Set<ShapedDrive> getShapedDrive() {
        Set<ShapedDrive> set = new HashSet<>();
        iControl.getCapability(BindType.modelStore).forEach((k, v) -> set.addAll(v.get()));
        return set;
    }

    @Override
    public IBack getBack() {
        return iBack;
    }

    public void up() {
        Map<IPosTrack, IManaHandle> manaIn = iControl.getCapability(BindType.manaIn);
        Map<IPosTrack, IManaHandle> manaOut = iControl.getCapability(BindType.manaOut);
        MinecraftForge.EVENT_BUS.post(new EventHandle.Up(this, manaIn, manaOut));
        shapedHandles.forEach(h -> {
            EventHandle.EventShapedHandle.Up up = new EventHandle.EventShapedHandle.Up(this, h, manaIn, manaOut);
            h.process.up(up);
            MinecraftForge.EVENT_BUS.post(up);
        });
    }

    /***
     * 回调
     */
    public void clockTriggerRun() {
        Map<IPosTrack, IItemHandler> itemIn = iControl.getCapability(BindType.itemIn);
        Map<IPosTrack, IItemHandler> itemOut = iControl.getCapability(BindType.itemOut);
        Map<IPosTrack, IFluidHandler> fluidIn = iControl.getCapability(BindType.fluidIn);
        Map<IPosTrack, IFluidHandler> fluidOut = iControl.getCapability(BindType.fluidOut);

        MinecraftForge.EVENT_BUS.post(new EventHandle.Clock(this, itemIn, itemOut, fluidIn, fluidOut));
        for (ShapedHandle shapedHandle : shapedHandles) {
            EventHandle.EventShapedHandle.Clock clock = new EventHandle.EventShapedHandle.Clock(this, shapedHandle, itemIn, itemOut, fluidIn, fluidOut);
            shapedHandle.process.clock(clock);
            MinecraftForge.EVENT_BUS.post(clock);
        }

        List<ShapedHandle> rShapedHandle = new ArrayList<>();
        for (ShapedHandle h : shapedHandles) {
            if (h.isEmpty()) {
                rShapedHandle.add(h);
            }
        }

        if (!rShapedHandle.isEmpty()) {
            for (ShapedHandle r : rShapedHandle) {
                shapedHandles.remove(r);
                MinecraftForge.EVENT_BUS.post(new EventHandle.EventShapedHandle.Complete(this, r));
            }
        }

        if (shapedHandles.size() >= getParallelHandle()) {
            return;
        }

        Set<ShapedDrive> shapedDrives = getShapedDrive();
        Set<Shaped> needMate = new HashSet<>();
        Set<Shaped> cache = new HashSet<>();
        Set<Shaped> canUse = new HashSet<>();
        ShapedHandle shapedHandle;
        do {
            shapedHandle = null;
            for (Shaped s : this.shapedList) {
                if (!shapedDrives.contains(s.shapedDrive)) {
                    continue;
                }
                if (!s.hasItemIn() && !s.hasFluidIn()) {
                    shapedHandle = s.get(this, null, null);
                    if (shapedHandle != null) {
                        addShapedHandle(shapedHandle);
                        break;
                    }
                }
                if (s.hasItemIn() && itemIn.isEmpty()) {
                    continue;
                }
                if (s.hasFluidIn() && fluidIn.isEmpty()) {
                    continue;
                }
                needMate.add(s);
            }
            if (shapedHandle != null) {
                continue;
            }
            if (needMate.isEmpty()) {
                return;
            }
            for (Shaped shaped : needMate) {
                if (!shaped.hasFluidIn()) {
                    cache.add(shaped);
                    canUse.add(shaped);
                }
            }
            if (!cache.isEmpty()) {
                for (Shaped shaped : cache) {
                    needMate.remove(shaped);
                }
                cache.clear();
            }
            if (!fluidIn.isEmpty()) {
                for (Map.Entry<IPosTrack, IFluidHandler> entry : fluidIn.entrySet()) {
                    IFluidHandler iFluidHandler = entry.getValue();
                    for (int i = 0; i < iFluidHandler.getTanks(); i++) {
                        FluidStack fluidStack = iFluidHandler.getFluidInTank(i);
                        for (Shaped shaped : needMate) {
                            if (!shaped.screenOfFluid(fluidStack)) {
                                continue;
                            }
                            cache.add(shaped);
                            canUse.add(shaped);
                        }
                        if (!cache.isEmpty()) {
                            for (Shaped shaped : cache) {
                                needMate.remove(shaped);
                            }
                            cache.clear();
                        }
                    }
                }
            }
            if (canUse.isEmpty()) {
                return;
            }
            if (itemIn.isEmpty()) {
                for (Shaped shaped : canUse) {
                    if (shaped.hasItemIn()) {
                        continue;
                    }
                    shapedHandle = shaped.get(this, null, fluidIn);
                    if (shapedHandle != null) {
                        addShapedHandle(shapedHandle);
                        break;
                    }
                }
            } else {
                for (Map.Entry<IPosTrack, IItemHandler> entry : itemIn.entrySet()) {
                    IItemHandler iItemHandler = entry.getValue();
                    for (int i = 0; i < iItemHandler.getSlots(); i++) {
                        ItemStack itemStack = iItemHandler.getStackInSlot(i);
                        for (Shaped shaped : canUse) {
                            if (shaped.screenOfItem(itemStack)) {
                                shapedHandle = shaped.get(this, entry, fluidIn);
                                if (shapedHandle != null) {
                                    addShapedHandle(shapedHandle);
                                    break;
                                }
                            }
                        }
                        if (shapedHandle != null) {
                            break;
                        }
                    }
                }
            }
        } while (shapedHandle != null && shapedHandles.size() < getParallelHandle());
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        shapedHandles = AllNBTPack.SHAPED_HANDLE_LIST.get(nbt);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbtTagCompound = new CompoundTag();
        AllNBTPack.SHAPED_HANDLE_LIST.set(nbtTagCompound, shapedHandles);
        return nbtTagCompound;
    }

    @Override
    public IPosTrack getPosTrack() {
        return posTrack;
    }

    @Nullable
    @Override
    public CompoundTag appendServerData() {
        CompoundTag compoundTag = serializeNBT();
        AllNBTPack.MAX_PARALLEL.set(compoundTag, getParallelHandle());
        AllNBTPack.SHAPED_DRIVE_LIST.set(compoundTag, new ArrayList<>(getShapedDrive()));
        return compoundTag;
    }

    @Override
    public void appendTooltip(IComponentPack iTooltip, CompoundTag compoundTag) {
        iTooltip.add(Lang.getLang(Lang.getLang(CapabilityRegister.iControl), Component.literal(":")));
        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("最大并行配方")), Component.literal(String.valueOf(AllNBTPack.MAX_PARALLEL.get(compoundTag)))));
        List<ShapedDrive> shapedDriveList = AllNBTPack.SHAPED_DRIVE_LIST.get(compoundTag);
        StringBuilder stringBuilder = new StringBuilder();
        if (shapedDriveList.isEmpty()) {
            stringBuilder.append('[').append(']');
        } else {
            stringBuilder.append('[');
            for (ShapedDrive shapedDrive : shapedDriveList) {
                stringBuilder.append(shapedDrive.name.getPath());
                stringBuilder.append(',');
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append(']');
        }
        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("使用配方集")), Component.literal(stringBuilder.toString())));

        List<ShapedHandle> shapedHandles = AllNBTPack.SHAPED_HANDLE_LIST.get(compoundTag);
        if (shapedHandles.isEmpty()) {
            return;
        }
        for (int i = 0; i < shapedHandles.size(); i++) {
            ShapedHandle shapedHandle = shapedHandles.get(i);
            iTooltip.indent();
            iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("配方处理")), Component.literal(String.valueOf(i))));
            iTooltip.indent();
            iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("状态")), Lang.getLang(shapedHandle.process)));
            if (shapedHandle.consumeMana > 0) {
                iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("消耗灵气")), Component.literal(String.valueOf(shapedHandle.consumeMana))));
            }
            if (shapedHandle._surplusTime > 0) {
                iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("剩余时间")), Component.literal(String.valueOf(shapedHandle._surplusTime))));
            }
            if (shapedHandle.outMana > 0) {
                iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("输出灵气")), Component.literal(String.valueOf(shapedHandle.outMana))));
            }
            if (shapedHandle.outItem != null && !shapedHandle.outItem.isEmpty()) {
                iTooltip.indent();
                iTooltip.add(Lang.getLang(Lang.getLang(BindType.itemOut), Component.translatable(":")));
                iTooltip.indent();
                for (ItemStack itemStack : shapedHandle.outItem) {
                    iTooltip.add(Lang.getLang(itemStack.getDisplayName(), Component.literal("x" + itemStack.getCount())));
                }
                iTooltip.returnIndent();
                iTooltip.returnIndent();
            }
            if (shapedHandle.outFluid != null && !shapedHandle.outFluid.isEmpty()) {
                iTooltip.indent();
                iTooltip.add(Lang.getLang(Lang.getLang(BindType.fluidOut), Component.translatable(":")));
                iTooltip.indent();
                for (FluidStack fluidStack : shapedHandle.outFluid) {
                    iTooltip.add(Lang.getLang(fluidStack.getDisplayName(), Component.literal("x" + fluidStack.getAmount() + "ml")));
                }
                iTooltip.returnIndent();
                iTooltip.returnIndent();
            }
            iTooltip.returnIndent();
            iTooltip.returnIndent();
        }
    }
}
