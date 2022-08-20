package com.til.dusk.common.capability.mana_handle;

import net.minecraftforge.eventbus.api.Event;

/***
 * 和灵气处理器相关的事件
 * @author til
 */
public class EventManaHandle extends Event {

    public final IManaHandle manaHandle;

    public EventManaHandle(IManaHandle manaHandle) {
        this.manaHandle = manaHandle;
    }

    /***
     * 灵气添加时
     */
    public static class Add extends EventManaHandle {
        public final long addMana;

        public Add(IManaHandle manaHandle, long addMana) {
            super(manaHandle);
            this.addMana = addMana;
        }
    }

    /***
     * 灵气提取时
     */
    public static class Extract extends EventManaHandle {
        public final long extractMana;

        public Extract(IManaHandle manaHandle, long extractMana) {
            super(manaHandle);
            this.extractMana = extractMana;
        }
    }

}
