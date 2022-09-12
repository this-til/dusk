package com.til.dusk.util.tooltip_pack;

import net.minecraft.network.chat.Component;

public interface IComponentPack {
    void indent();

    void returnIndent();

    void resetIndent();

    void add(Component component);
}
