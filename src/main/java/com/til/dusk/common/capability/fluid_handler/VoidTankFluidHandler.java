package com.til.dusk.common.capability.fluid_handler;

import com.til.dusk.common.register.tag_tool.TagTool;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class VoidTankFluidHandler implements IFluidHandler, INBTSerializable<CompoundTag> {

    public final int maxAmount;
    @Nullable
    public FluidStack fluidStack;

    public VoidTankFluidHandler(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public int getTanks() {
        return 1;
    }

    @Override
    public int getTankCapacity(int tank) {
        if (tank == 0) {
            return maxAmount;
        }
        return 0;
    }

    @Override
    public @NotNull FluidStack getFluidInTank(int tank) {
        if (tank == 0 && fluidStack != null) {
            return fluidStack;
        }
        return FluidStack.EMPTY;
    }

    @Override
    public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
        return true;
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        if (resource.isEmpty()) {
            return 0;
        }
        if (fluidStack != null && !fluidStack.isEmpty()) {
            if (fluidStack.isFluidEqual(resource)) {
                int fill = Math.min(maxAmount - fluidStack.getAmount(), resource.getAmount());
                if (action.execute()) {
                    fluidStack.setAmount(fluidStack.getAmount() + fill);
                }
                return fill;
            } else {
                return 0;
            }
        } else {
            int fill = Math.min(maxAmount, resource.getAmount());
            if (action.execute()) {
                fluidStack = resource.copy();
                fluidStack.setAmount(fill);
            }
            return fill;
        }
    }

    @Override
    public @NotNull FluidStack drain(FluidStack resource, FluidAction action) {
        if (resource.isEmpty()) {
            return FluidStack.EMPTY;
        }
        if (fluidStack == null || fluidStack.isEmpty() || !fluidStack.isFluidEqual(resource)) {
            return FluidStack.EMPTY;
        }
        int out = Math.min(fluidStack.getAmount(), resource.getAmount());
        if (action.execute()) {
            fluidStack.setAmount(fluidStack.getAmount() - out);
            if (fluidStack.isEmpty()) {
                fluidStack = null;
            }
        }
        FluidStack outFluidStack = resource.copy();
        outFluidStack.setAmount(out);
        return outFluidStack.isEmpty() ? FluidStack.EMPTY : outFluidStack;
    }

    @Override
    public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
        if (fluidStack == null || fluidStack.isEmpty()) {
            return FluidStack.EMPTY;
        }
        int out = Math.min(fluidStack.getAmount(), maxDrain);
        if (action.execute()) {
            fluidStack.setAmount(fluidStack.getAmount() - out);
            if (fluidStack.isEmpty()) {
                fluidStack = null;
            }
        }
        FluidStack outFluidStack = fluidStack.copy();
        outFluidStack.setAmount(outFluidStack.getAmount() - out);
        return outFluidStack.isEmpty() ? FluidStack.EMPTY : outFluidStack;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        TagTool.fluidStackTag.set(compoundTag, fluidStack == null ? FluidStack.EMPTY : fluidStack);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        fluidStack = TagTool.fluidStackTag.get(nbt);
        if (fluidStack.isEmpty()) {
            fluidStack = null;
        }
    }
}
