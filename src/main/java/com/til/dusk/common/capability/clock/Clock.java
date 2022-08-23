package com.til.dusk.common.capability.clock;

import com.til.dusk.common.capability.mana_level.IManaLevel;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.register.tag_tool.TagTool;
import com.til.dusk.util.Extension;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.List;

/**
 * @author til
 */
public class Clock implements IClock {

    public final List<Extension.Action> blackRun = new ArrayList<>();
    public final int clock;

    public int time;

    public Clock(IUp up, int clock) {
        addBlock(() -> MinecraftForge.EVENT_BUS.post(new EventClock.Clock(this)));
        this.clock = clock;
        up.addUpBlack(this::up);
    }

    public Clock(IUp up, IManaLevel iManaLevel) {
        this(up, iManaLevel.manaLevel().clock);
    }

    @Override
    public void addBlock(Extension.Action black) {
        blackRun.add(black);
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public int getCycleTime() {
        return clock;
    }

    public void up() {
        time--;
        if (time <= 0) {
            time = clock;
            blackRun.forEach(Extension.Action::action);
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbtTagCompound = new CompoundTag();
        TagTool.timeTag.set(nbtTagCompound, time);
        return nbtTagCompound;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        time = TagTool.timeTag.get(nbt);
    }
}
