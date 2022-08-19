package com.til.dusk.capability;


import com.ibm.icu.impl.locale.Extension;
import com.til.dusk.util.AllNBT;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

public interface IClockTime extends INBT , ITack{

    /***
     * 获取当前时间进度
     */
    int getClockTime();

    /***
     * 获取一个周期的时间
     */
    int getCycleTime();

    /***
     * 设置时钟
     */
    void setClockTime(int clockTime);

    /***
     * 时间流逝
     * 只有这里才会触发时钟方法
     */
    default void time() {
        setClockTime(getClockTime() - 1);
        if (getClockTime() <= 0) {
            clockRun();
        }
    }

    /***
     * 回调
     */
    default void clockTriggerRun() {
    }

    /***
     * 重置周期
     */
    default void clockRun() {
        clockTriggerRun();
        setClockTime(getCycleTime());
    }

    class ClockTime implements IClockTime {

        public final IManaLevel iManaLevel;

        public int time;

        public ClockTime(IManaLevel iManaLevel) {
            this.iManaLevel = iManaLevel;
        }

        @Override
        public int getClockTime() {
            return time;
        }

        @Override
        public int getCycleTime() {
            return iManaLevel.getManaLevel().clock;
        }

        @Override
        public void setClockTime(int clockTime) {
            time = clockTime;
        }

        @Override
        public AllNBT.IGS<Tag> getNBTBase() {
            return AllNBT.iClockTime;
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag nbtTagCompound = new CompoundTag();
            nbtTagCompound.putInt("time", getClockTime());
            return nbtTagCompound;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            setClockTime(nbt.getInt("time"));
        }
    }

}
