package com.til.dusk.common.register.key;

import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/***
 * 当按键按下去时触发
 * @author til
 */
public class EventKey extends Event {
    public final KeyRegister keyRegister;
    public final Supplier<NetworkEvent.Context> contextSupplier;

    public EventKey(KeyRegister keyRegister, Supplier<NetworkEvent.Context> contextSupplier) {
        this.keyRegister = keyRegister;
        this.contextSupplier = contextSupplier;
    }
}
