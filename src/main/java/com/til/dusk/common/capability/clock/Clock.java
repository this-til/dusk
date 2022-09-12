package com.til.dusk.common.capability.clock;

import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.util.Lang;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.Extension;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author til
 */
public class Clock implements IClock {

    public final List<Extension.Action> blackRun = new ArrayList<>();
    public final int clock;

    public int time;

    public Clock(IBack back, int clock) {
        addBlock(() -> MinecraftForge.EVENT_BUS.post(new EventClock.Clock(this)));
        this.clock = clock;
        back.add(IBack.UP, v -> up());
    }

    public Clock(IBack back, ManaLevel iManaLevel) {
        this(back, iManaLevel.clock);
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
        AllNBTPack.TIME.set(nbtTagCompound, time);
        return nbtTagCompound;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        time = AllNBTPack.TIME.get(nbt);
    }

    @Nullable
    @Override
    public CompoundTag appendServerData() {
        CompoundTag compoundTag = serializeNBT();
        AllNBTPack.CYCLE_TIME.set(compoundTag, getCycleTime());
        return compoundTag;
    }

    @Override
    public void appendTooltip(IComponentPack iTooltip, CompoundTag compoundTag) {
        iTooltip.add(Lang.getLang(Lang.getLang(CapabilityRegister.iClock), Component.literal(AllNBTPack.TIME.get(compoundTag) + "/" + AllNBTPack.CYCLE_TIME.get(compoundTag))));
    }
}
