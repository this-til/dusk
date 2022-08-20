package com.til.dusk.common.capability.handle;

import com.til.dusk.common.capability.clock_time.IClockTime;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.mana_level.IManaLevel;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.shaped.Shaped;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.ShapedType;
import com.til.dusk.util.AllNBT;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Handle implements IHandle {

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
        iClockTime.addBlock(this::clockTriggerRun);
    }

    @Override
    public int getParallelHandle() {
        return iManaLevel.manaLevel().parallel;
    }

    @Override
    public IManaLevel getManaLevel() {
        return iManaLevel;
    }


    @Override
    public IControl getControl() {
        return iControl;
    }

    @Override
    public IClockTime getClockTime() {
        return iClockTime;
    }


    @Override
    public AllNBT.IGS<Tag> getNBTBase() {
        return AllNBT.iHandleNBT;
    }

    @Override
    public List<ShapedType> getShapedTypes() {
        return shapedTypes;
    }

    @Override
    public void addShapedHandle(ShapedHandle shaped) {
        shapedHandles.add(shaped);
        MinecraftForge.EVENT_BUS.post(new EventHandle.EventShapedHandle.Complete(this, shaped));
    }

    @Override
    public List<ShapedDrive> getShapedDrive() {
        List<ShapedDrive> list = new ArrayList<>();
        iControl.getCapability(BindType.modelStore).forEach((k, v) -> list.addAll(v.get()));
        return list;
    }

    @Override
    public void time() {
        Map<BlockEntity, IManaHandle> manaIn = iControl.getCapability(BindType.manaIn);
        Map<BlockEntity, IManaHandle> manaOut = iControl.getCapability(BindType.manaOut);
        MinecraftForge.EVENT_BUS.post(new EventHandle.Up(this, manaIn, manaOut));
        shapedHandles.forEach(h -> MinecraftForge.EVENT_BUS.post(new EventHandle.EventShapedHandle.Up(this, h, manaIn, manaOut)));
    }

    /***
     * 回调
     */
    public void clockTriggerRun() {
        Map<BlockEntity, IItemHandler> itemIn = iControl.getCapability(BindType.itemIn);
        Map<BlockEntity, IItemHandler> itemOut = iControl.getCapability(BindType.itemOut);
        Map<BlockEntity, IFluidHandler> fluidIn = iControl.getCapability(BindType.fluidIn);
        Map<BlockEntity, IFluidHandler> fluidOut = iControl.getCapability(BindType.fluidOut);

        MinecraftForge.EVENT_BUS.post(new EventHandle.Clock(this, itemIn, itemOut, fluidIn, fluidOut));
        shapedHandles.forEach(h -> MinecraftForge.EVENT_BUS.post(new EventHandle.EventShapedHandle.Clock(this, h, itemIn, itemOut, fluidIn, fluidOut)));

        List<ShapedHandle> rShapedHandle = new ArrayList<>();
        shapedHandles.forEach(h -> {
            if (h.isEmpty()) {
                rShapedHandle.add(h);
            }
        });
        rShapedHandle.forEach(r -> {
            shapedHandles.remove(r);
            MinecraftForge.EVENT_BUS.post(new EventHandle.EventShapedHandle.Complete(this, r));
        });

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
                shapedHandle = shaped.get(this, iManaLevel.manaLevel(), itemIn, fluidIn);
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
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbtTagCompound = new CompoundTag();
        ListTag nbtBases = new ListTag();
        for (ShapedHandle shapedHandle : shapedHandles) {
            nbtBases.add(shapedHandle.serializeNBT());
        }
        nbtTagCompound.put("shapedHandles", nbtBases);
        return nbtTagCompound;
    }

    @Override
    public BlockEntity getThis() {
        return tileEntity;
    }

}
