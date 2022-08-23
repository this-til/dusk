package com.til.dusk.common.capability.handle;

import com.til.dusk.common.register.shaped.ShapedHandleProcess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShapedHandle {

    public static final String SURPLUS_TIME = "surplusTime";
    public static final String CONSUME_MANA = "consumeMana";
    public static final String _SURPLUS_TIME = "_surplusTime";
    public static final String PROCESS = "process";
    public static final String OUT_ITEM = "outItem";
    public static final String OUT_FLUID = "outFluid";
    public static final String OUT_MANA = "outMana";


    public final long consumeMana;

    /***
     * 总共需要的时间
     */
    public final long surplusTime;

    /***
     * 剩余时间
     */
    public long _surplusTime;

    public ShapedHandleProcess process;

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
        process = ShapedHandleProcess.production;
    }



    public CompoundTag serializeNBT() {
        CompoundTag nbtTagCompound = new CompoundTag();

        nbtTagCompound.putLong(SURPLUS_TIME, surplusTime);
        nbtTagCompound.putLong(CONSUME_MANA, consumeMana);
        nbtTagCompound.putLong(_SURPLUS_TIME, _surplusTime);
        nbtTagCompound.putString(PROCESS, process.name.toString());

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

    public boolean isEmpty() {
        return (outItem == null || outItem.isEmpty()) && (outFluid == null || outFluid.isEmpty()) && outMana == 0;
    }

    public static class EmptyShapedHandle extends ShapedHandle {
        public EmptyShapedHandle(long surplusTime, long consumeMana, long outMana) {
            super(surplusTime, consumeMana, outMana, null, null);
            process = ShapedHandleProcess.out;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    }
}
