package com.til.dusk.common.capability.fluid_handler;

import com.til.dusk.common.capability.ITooltipCapability;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.Lang;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nullable;

/**
 * @author til
 */
public class VoidTankFluidHandler extends FluidTank implements INBTSerializable<CompoundTag>, ITooltipCapability {
    public VoidTankFluidHandler(int maxAmount) {
        super(maxAmount);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        AllNBTPack.FLUID_STACK.set(compoundTag, fluid);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        fluid = AllNBTPack.FLUID_STACK.get(nbt);
    }

    @Nullable
    @Override
    public CompoundTag appendServerData() {
        CompoundTag compoundTag = serializeNBT();
        AllNBTPack.MAX_AMOUNT.set(compoundTag, capacity);
        return compoundTag;
    }

    @Override
    public void appendTooltip(IComponentPack iTooltip, CompoundTag compoundTag) {
        FluidStack fluidStack = AllNBTPack.FLUID_STACK.get(compoundTag);
        if (!fluidStack.isEmpty()) {
            iTooltip.add(Lang.getLang(Lang.getLang(CapabilityRegister.iFluidHandler),
                    fluidStack.getDisplayName(),
                    Component.literal("x"),
                    Component.literal(fluidStack.getAmount() + "mb"),
                    Component.literal("/"),
                    Component.translatable(AllNBTPack.MAX_AMOUNT.get(compoundTag) + "mb")));
        } else {
            iTooltip.add(Lang.getLang(CapabilityRegister.iFluidHandler));
        }

    }
}
