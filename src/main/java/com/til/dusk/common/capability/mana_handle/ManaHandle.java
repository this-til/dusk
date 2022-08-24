package com.til.dusk.common.capability.mana_handle;

import com.til.dusk.common.register.tag_tool.TagTool;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author til
 */
public class ManaHandle implements IManaHandle {

    public final long maxMana;
    public final long rate;
    public long mana;

    public ManaHandle(long maxMana, long rate) {
        this.maxMana = maxMana;
        this.rate = rate;
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
        return rate;
    }

    @Override
    public long addMana(long mana) {
        if (mana == 0) {
            return 0;
        }
        long addMana = Math.min(Math.min(mana, this.getMaxRate()), this.getRemainMana());
        this.mana += addMana;
        MinecraftForge.EVENT_BUS.post(new EventManaHandle.Add(this, addMana));
        return addMana;
    }

    @Override
    public long extractMana(long demand) {
        if (demand == 0) {
            return 0;
        }
        long extractMana = Math.min(Math.min(demand, this.getMaxRate()), this.getMana());
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
