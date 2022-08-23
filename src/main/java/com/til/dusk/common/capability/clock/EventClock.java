package com.til.dusk.common.capability.clock;

import net.minecraftforge.eventbus.api.Event;

/***
 * 时钟触发事件
 * @author til
 */
public class EventClock extends Event {
    public final IClock clockTime;

    public EventClock(IClock clockTime) {
        this.clockTime = clockTime;
    }

    /***
     * 触发时钟
     */
    public static class Clock extends EventClock {
        public Clock(IClock clockTime) {
            super(clockTime);
        }
    }
}
