package com.til.dusk.common.capability.handle;

import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.util.Lang;
import com.til.dusk.util.TooltipPack;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

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

    public Handle(BlockEntity tileEntity, List<ShapedType> shapedTypes, IControl iControl, IClock iClock, IUp up, ManaLevel maxParallel) {
        this.shapedList = Shaped.get(shapedTypes.toArray(new ShapedType[0]));
        this.tileEntity = tileEntity;
        this.iControl = iControl;
        this.iClock = iClock;
        this.up = up;
        this.maxParallel = maxParallel.parallel;
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
        Map<BlockEntity, IItemHandler> itemIn = iControl.getCapability(BindType.itemIn);
        Map<BlockEntity, IItemHandler> itemOut = iControl.getCapability(BindType.itemOut);
        Map<BlockEntity, IFluidHandler> fluidIn = iControl.getCapability(BindType.fluidIn);
        Map<BlockEntity, IFluidHandler> fluidOut = iControl.getCapability(BindType.fluidOut);

        MinecraftForge.EVENT_BUS.post(new EventHandle.Clock(this, itemIn, itemOut, fluidIn, fluidOut));
        shapedHandles.forEach(h -> {
            EventHandle.EventShapedHandle.Clock clock = new EventHandle.EventShapedHandle.Clock(this, h, itemIn, itemOut, fluidIn, fluidOut);
            h.process.clock(clock);
            MinecraftForge.EVENT_BUS.post(clock);
        });

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
        shapedHandles = AllNBTPack.SHAPED_HANDLE_LIST.get(nbt);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbtTagCompound = new CompoundTag();
        AllNBTPack.SHAPED_HANDLE_LIST.set(nbtTagCompound, shapedHandles);
        return nbtTagCompound;
    }

    @Override
    public BlockEntity getThis() {
        return tileEntity;
    }

    @Nullable
    @Override
    public CompoundTag appendServerData(ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean detailed) {
        CompoundTag compoundTag = serializeNBT();
        AllNBTPack.MAX_PARALLEL.set(compoundTag, getParallelHandle());
        AllNBTPack.SHAPED_DRIVE_LIST.set(compoundTag, getShapedDrive());
        return compoundTag;
    }

    @Override
    public void appendTooltip(TooltipPack iTooltip, CompoundTag compoundTag) {
        iTooltip.add(Lang.getLang(CapabilityRegister.iControl));
        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("最大并行配方")),
                Component.literal(String.valueOf(AllNBTPack.MAX_PARALLEL.get(compoundTag)))));
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
            if (shapedHandle.outItem != null) {
                iTooltip.indent();
                iTooltip.add(Lang.getLang(Lang.getLang(BindType.itemOut), Component.translatable(":")));
                iTooltip.indent();
                for (ItemStack itemStack : shapedHandle.outItem) {
                    iTooltip.add(Lang.getLang(itemStack.getDisplayName(), Component.literal("x" + itemStack.getCount())));
                }
                iTooltip.returnIndent();
                iTooltip.returnIndent();
            }
            if (shapedHandle.outFluid != null) {
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
