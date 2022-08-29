package com.til.dusk.common.capability.mana_handle;

import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.util.tag_tool.TagTool;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author til
 */
public class ManaHandle implements IManaHandle {

    public final long maxMana;
    public final long maxRate;
    public long inRate;
    public long outRate;
    public long mana;

    public ManaHandle(long maxMana, long maxRate, IUp iUp) {
        this.maxMana = maxMana;
        this.maxRate = maxRate;
        iUp.addUpBlack(this::up);
    }

    public void up() {
        inRate = maxRate;
        outRate = maxRate;
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
    public long addMana(long mana) {
        if (mana == 0) {
            return 0;
        }
        long addMana = Math.min(Math.min(mana, this.getInCurrentRate()), this.getRemainMana());
        this.mana += addMana;
        MinecraftForge.EVENT_BUS.post(new EventManaHandle.Add(this, addMana));
        return addMana;
    }

    @Override
    public long extractMana(long demand) {
        if (demand == 0) {
            return 0;
        }
        long extractMana = Math.min(Math.min(demand, this.getOutCurrentRate()), this.getMana());
        this.mana -= extractMana;
        MinecraftForge.EVENT_BUS.post(new EventManaHandle.Extract(this, extractMana));
        return extractMana;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbtTagCompound = new CompoundTag();
        TagTool.manaTag.set(nbtTagCompound, mana);
        return nbtTagCompound;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        mana = TagTool.manaTag.get(nbt);
    }
}
