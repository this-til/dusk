package com.til.dusk.common.capability.mana_handle;

import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.Lang;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.Nullable;

/**
 * @author til
 */
public class ManaHandle implements IManaHandle {

    public final long maxMana;
    public final long maxRate;
    public long inRate;
    public long outRate;
    public long mana;

    public ManaHandle(long maxMana, long maxRate, IBack iBack) {
        this.maxMana = maxMana;
        this.maxRate = maxRate;
        up();
        iBack.add(IBack.UP, v -> up());
    }

    public void up() {
        inRate = getMaxRate();
        outRate = getMaxRate();
    }

    @Override
    public long getInCurrentRate() {
        return inRate;
    }

    @Override
    public long getOutCurrentRate() {
        return outRate;
    }

    @Override
    public long getMaxMana() {
        return maxMana;
    }

    @Override
    public long getMana() {
        return mana;
    }

    @Override
    public long getMaxRate() {
        return maxRate;
    }


    @Override
    public long addMana(long mana, boolean isSimulate) {
        if (mana <= 0) {
            return 0;
        }
        long addMana = Math.min(Math.min(mana, this.getInCurrentRate()), this.getRemainMana());
        if (addMana != 0) {
            if (!isSimulate) {
                this.mana += addMana;
                this.inRate -= addMana;
                MinecraftForge.EVENT_BUS.post(new EventManaHandle.Add(this, addMana));
            }
        }
        return addMana;
    }

    @Override
    public long extractMana(long demand, boolean isSimulate) {
        if (demand <= 0) {
            return 0;
        }
        long extractMana = Math.min(Math.min(demand, this.getOutCurrentRate()), this.getMana());
        if (extractMana != 0) {
            if (!isSimulate) {
                this.mana -= extractMana;
                this.outRate -= extractMana;
                MinecraftForge.EVENT_BUS.post(new EventManaHandle.Extract(this, extractMana));
            }
        }
        return extractMana;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbtTagCompound = new CompoundTag();
        AllNBTPack.MANA.set(nbtTagCompound, mana);
        return nbtTagCompound;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        mana = AllNBTPack.MANA.get(nbt);
    }

    @Nullable
    @Override
    public CompoundTag appendServerData() {
        CompoundTag compoundTag = serializeNBT();
        AllNBTPack.MAX_MANA.set(compoundTag, getMaxMana());
        AllNBTPack.RATE.set(compoundTag, getMaxRate());
        return compoundTag;
    }

    @Override
    public void appendTooltip(IComponentPack iTooltip, CompoundTag compoundTag) {
        iTooltip.add(Lang.getLang(
                Lang.getLang(CapabilityRegister.iManaHandle),
                Component.literal(":")));
        iTooltip.indent();
        long mana = AllNBTPack.MANA.get(compoundTag);
        long maxMana = AllNBTPack.MAX_MANA.get(compoundTag);
        long rate = AllNBTPack.RATE.get(compoundTag);
        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("现存灵气")), Component.literal(mana + "/" + maxMana)));
        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("最大灵气流速")), Component.literal(String.valueOf(rate))));

    }
}
