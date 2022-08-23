package com.til.dusk.common.capability.handle;

import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.mana_level.IManaLevel;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.tag_tool.TagTool;
import com.til.dusk.common.register.shaped.Shaped;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.ShapedType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class Handle implements IHandle {

    public final List<Shaped> shapedList;
    public final BlockEntity tileEntity;
    public final IControl iControl;
    public final IClock iClock;
    public final IUp up;
    public final int maxParallel;
    public List<ShapedHandle> shapedHandles = new ArrayList<>();

    public Handle(BlockEntity tileEntity, List<Shaped> shapedTypes, IControl iControl, IClock iClock, IUp up, int maxParallel) {
        this.shapedList = shapedTypes;
        this.tileEntity = tileEntity;
        this.iControl = iControl;
        this.iClock = iClock;
        this.up = up;
        this.maxParallel = maxParallel;
        iClock.addBlock(this::clockTriggerRun);
        up.addUpBlack(this::up);
    }

    public Handle(BlockEntity tileEntity, List<ShapedType> shapedTypes, IControl iControl, IClock iClock, IUp up, IManaLevel maxParallel) {
        this.shapedList = new ArrayList<>();
        Shaped.SHAPED.get().forEach(s -> {
            if (shapedTypes.contains(s.shapedType) && maxParallel.manaLevel().level >= s.manaLevel.level) {
                shapedList.add(s);
            }
        });
        this.tileEntity = tileEntity;
        this.iControl = iControl;
        this.iClock = iClock;
        this.up = up;
        this.maxParallel = maxParallel.manaLevel().parallel;
        iClock.addBlock(this::clockTriggerRun);
        up.addUpBlack(this::up);
    }

    @Override

    public int getParallelHandle() {
        return maxParallel;
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
    public List<Shaped> getShaped() {
        return shapedList;
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
    public IUp getUp() {
        return up;
    }

    public void up() {
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

        List<Shaped> shapedList = new ArrayList<>(this.shapedList.size());
        this.shapedList.forEach(s -> {
            if (shapedDrives.contains(s.shapedDrive)) {
                shapedList.add(s);
            }
        });
        List<Shaped> rShaped = new ArrayList<>();

        ShapedHandle shapedHandle;
        do {
            shapedHandle = null;
            if (!rShaped.isEmpty()) {
                shapedList.removeAll(rShaped);
                rShaped.clear();
            }

            for (Shaped shaped : shapedList) {
                shapedHandle = shaped.get(this, itemIn, fluidIn);
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
        shapedHandles = TagTool.shapedHandleListTag.get(nbt);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbtTagCompound = new CompoundTag();
        TagTool.shapedHandleListTag.set(nbtTagCompound, shapedHandles);
        return nbtTagCompound;
    }

    @Override
    public BlockEntity getThis() {
        return tileEntity;
    }

}
