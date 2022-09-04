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


    public boolean isEmpty() {
        return (outItem == null || outItem.isEmpty()) && (outFluid == null || outFluid.isEmpty()) && outMana == 0;
    }
}
