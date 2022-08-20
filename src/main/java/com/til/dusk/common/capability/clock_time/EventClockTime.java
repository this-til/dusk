package com.til.dusk.common.capability.clock_time;

import net.minecraftforge.eventbus.api.Event;

/***
 * 时钟触发事件
 * @author til
 */
public class EventClockTime extends Event {
    public final IClockTime clockTime;

    public EventClockTime(IClockTime clockTime) {
        this.clockTime = clockTime;
    }

    /***
     * 触发时钟
     */
    public static class Clock extends EventClockTime{
        public Clock(IClockTime clockTime) {
            super(clockTime);
        }
    }
}
