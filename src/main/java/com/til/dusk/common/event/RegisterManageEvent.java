package com.til.dusk.common.event;

import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;

/**
 * @author til
 */
public class RegisterManageEvent extends Event implements IModBusEvent {

    /***
     * 初始化状态
     */
    public static class Init extends RegisterManageEvent {
    }

    /***
     * 注册进表
     */
    public static class InMap extends RegisterManageEvent {
    }

    /***
     * 会调
     */
    public static class Back extends RegisterManageEvent {
    }

    /***
     * 第二次回调
     */
    public static class BackToBack extends RegisterManageEvent {
    }
}
