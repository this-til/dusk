package com.til.dusk.common.capability.energy_storage;

import com.til.dusk.common.capability.ITooltipCapability;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.Lang;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.Nullable;

/**
 * @author til
 */
public class DuskEnergyStorage implements IEnergyStorage, INBTSerializable<CompoundTag>, ITooltipCapability {

    public final int maxStorage;
    public final int maxRate;
    public int inRate;
    public int outRate;
    public int energy;

    public DuskEnergyStorage(int maxStorage, int maxRate, IBack back) {
        this.maxStorage = maxStorage;
        this.maxRate = maxRate;
        up();
        back.add(IBack.UP, e -> up());
    }

    public void up() {
        inRate = maxRate;
        outRate = maxRate;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean isSimulate) {
        if (maxReceive <= 0) {
            return 0;
        }
        int addExtract = Math.min(Math.min(maxReceive, inRate), this.getRemainEnergy());
        if (addExtract != 0) {
            if (!isSimulate) {
                this.energy += addExtract;
                this.inRate -= addExtract;
            }
        }
        return addExtract;
    }


    @Override
    public int extractEnergy(int maxExtract, boolean isSimulate) {
        if (maxExtract <= 0) {
            return 0;
        }
        int extractExtract = Math.min(Math.min(maxExtract, outRate), this.getEnergyStored());
        if (extractExtract != 0) {
            if (!isSimulate) {
                this.energy -= extractExtract;
                this.outRate -= extractExtract;
            }
        }
        return extractExtract;
    }

    public int getRemainEnergy() {
        return Math.max(0, getMaxEnergyStored() - getEnergyStored());
    }

    @Override
    public int getEnergyStored() {
        return energy;
    }

    @Override
    public int getMaxEnergyStored() {
        return maxStorage;
    }

    @Override
    public boolean canExtract() {
        return outRate > 0;
    }

    @Override
    public boolean canReceive() {
        return inRate < 0;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        AllNBTPack.ENERGY.set(compoundTag, energy);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        energy = AllNBTPack.ENERGY.get(nbt);
    }

    @Nullable
    @Override
    public CompoundTag appendServerData() {
        CompoundTag compoundTag = serializeNBT();
        AllNBTPack.MAX_ENERGY.set(compoundTag, getMaxEnergyStored());
        AllNBTPack.RATE_ENERGY.set(compoundTag, maxRate);
        return compoundTag;
    }

    @Override
    public void appendTooltip(IComponentPack iTooltip, CompoundTag compoundTag) {
        iTooltip.add(Lang.getLang(CapabilityRegister.iEnergyStorage));
        iTooltip.indent();
        int energy = AllNBTPack.ENERGY.get(compoundTag);
        int maxEnergy = AllNBTPack.MAX_ENERGY.get(compoundTag);
        int rate = AllNBTPack.RATE_ENERGY.get(compoundTag);
        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("现存FE")), Component.literal(energy + "/" + maxEnergy)));
        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("最大FE流速")), Component.literal(String.valueOf(rate))));

    }
}
