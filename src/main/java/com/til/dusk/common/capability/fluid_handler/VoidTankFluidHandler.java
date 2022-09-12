package com.til.dusk.common.capability.fluid_handler;

import com.til.dusk.common.capability.ITooltipCapability;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.util.Lang;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * @author til
 */
public class VoidTankFluidHandler implements IFluidHandler, INBTSerializable<CompoundTag>, ITooltipCapability {

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
        AllNBTPack.FLUID_STACK.set(compoundTag, fluidStack == null ? FluidStack.EMPTY : fluidStack);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        fluidStack = AllNBTPack.FLUID_STACK.get(nbt);
        if (fluidStack.isEmpty()) {
            fluidStack = null;
        }
    }

    @Nullable
    @Override
    public CompoundTag appendServerData() {
        return serializeNBT();
    }

    @Override
    public void appendTooltip(IComponentPack iTooltip, CompoundTag compoundTag) {
        FluidStack fluidStack = AllNBTPack.FLUID_STACK.get(compoundTag);
        if (!fluidStack.isEmpty()) {
            iTooltip.add(Lang.getLang(Lang.getLang(CapabilityRegister.iFluidHandler),
                    fluidStack.getDisplayName(), Component.literal("x"), Component.literal(fluidStack.getAmount() + "mb")));
        } else {
            iTooltip.add(Lang.getLang(CapabilityRegister.iFluidHandler));
        }

    }

   /* @Override
    public @NotNull CompoundTag appendServerData(ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean detailed, IFluidHandler i) {
        CompoundTag compoundTag = new CompoundTag();
        List<FluidStack> fluidStacks = new ArrayList<>(i.getTanks());
        for (int i1 = 0; i1 < i.getTanks(); i1++) {
            fluidStacks.add(i.getFluidInTank(i1));
        }
        TagTool.fluidStackListTag.set(compoundTag, fluidStacks);
        return compoundTag;
    }

    @Override
    public void appendTooltip(Jade_Interact.TooltipPack iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig, CompoundTag compoundTag) {
        super.appendTooltip(iTooltip, blockAccessor, iPluginConfig, compoundTag);
        Map<Fluid, Integer> integerMap = new HashMap<>();
        TagTool.fluidStackListTag.get(compoundTag).forEach(fluidStack -> {
            if (fluidStack.isEmpty()) {
                return;
            }
            Fluid item = fluidStack.getFluid();
            if (integerMap.containsKey(item)) {
                integerMap.put(item, integerMap.get(item) + fluidStack.getAmount());
            } else {
                integerMap.put(item, fluidStack.getAmount());
            }
        });
        for (Map.Entry<Fluid, Integer> fluidIntegerEntry : integerMap.entrySet()) {
            FluidStack fluidStack = new FluidStack(fluidIntegerEntry.getKey(), fluidIntegerEntry.getValue());
            iTooltip.add(Lang.getLang(fluidStack.getDisplayName(),
                    Component.literal("x" + fluidIntegerEntry.getValue())));
        }
    }*/
}
