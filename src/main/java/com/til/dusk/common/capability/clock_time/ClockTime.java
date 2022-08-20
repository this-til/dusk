package com.til.dusk.common.capability.clock_time;

import com.til.dusk.common.capability.mana_level.IManaLevel;
import com.til.dusk.util.AllNBT;
import com.til.dusk.util.Extension;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.List;

/**
 * @author til
 */
public class ClockTime implements IClockTime {

    public List<Extension.Action> blackRun = new ArrayList<>();
    public final IManaLevel iManaLevel;

    public int time;

    public ClockTime(IManaLevel iManaLevel) {
        this.iManaLevel = iManaLevel;
        addBlock(() -> MinecraftForge.EVENT_BUS.post(new EventClockTime.Clock(this)));
    }

    @Override
    public void addBlock(Extension.Action black) {
        blackRun.add(black);
    }

    @Override
    public void up() {
        time--;
        if (time <= 0) {
            time = iManaLevel.manaLevel().clock;
            blackRun.forEach(Extension.Action::action);
        }
    }

    @Override
    public AllNBT.IGS<Tag> getNBTBase() {
        return AllNBT.iClockTime;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbtTagCompound = new CompoundTag();
        nbtTagCompound.putInt("time", time);
        return nbtTagCompound;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        time = nbt.getInt("time");
    }
}
