package com.til.dusk.common.event;

import com.til.dusk.client.data.lang.LangProvider;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;

/**
 * @author til
 */
public class RegisterLangEvent extends Event implements IModBusEvent {
    public final LangProvider.LangTool langTool;

    public RegisterLangEvent(LangProvider.LangTool langTool) {
        this.langTool = langTool;
    }
}
