package com.til.dusk.common.capability.control;

import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.other.BindType;
import net.minecraftforge.eventbus.api.Event;

/***
 * 和控制器相关的事件
 * @author til
 */
public class EventControl extends Event {

    public final IControl control;

    public EventControl(IControl control) {
        this.control = control;
    }

    /***
     * 解绑全部时
     * 在行动前触发
     */
    public static class UnBundlingAll extends EventControl {
        public UnBundlingAll(IControl control) {
            super(control);
        }
    }

    /***
     * 绑定成功
     */
    public static class Binding extends EventControl {
        public final IPosTrack blockPos;
        public final BindType bindType;

        public Binding(IControl control, IPosTrack blockPos, BindType bindType) {
            super(control);
            this.blockPos = blockPos;
            this.bindType = bindType;
        }
    }

    /***
     * 解绑成功
     */
    public static class UnBinding extends EventControl {
        public final IPosTrack blockPos;
        public final BindType bindType;

        public UnBinding(IControl control, IPosTrack blockPos, BindType bindType) {
            super(control);
            this.blockPos = blockPos;
            this.bindType = bindType;
        }
    }
}
