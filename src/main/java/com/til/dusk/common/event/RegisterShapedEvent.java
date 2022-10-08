package com.til.dusk.common.event;

import com.til.dusk.common.register.shaped.shapeds.Shaped;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;

import java.util.function.Consumer;

/**
 * @author til
 */
public class RegisterShapedEvent extends Event implements IModBusEvent {
    public final Consumer<Shaped> shapedConsumer;

    public RegisterShapedEvent(Consumer<Shaped> shapedConsumer) {
        this.shapedConsumer = shapedConsumer;
    }
}
