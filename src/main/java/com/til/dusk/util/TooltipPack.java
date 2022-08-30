package com.til.dusk.util;

import net.minecraft.network.chat.Component;
import snownee.jade.api.ITooltip;

/**
 * @author til
 */
public class TooltipPack {

    public final ITooltip iTooltip;
    public int indent;

    public TooltipPack(ITooltip iTooltip) {
        this.iTooltip = iTooltip;
    }

    public void indent() {
        indent++;
    }

    public void returnIndent() {
        indent--;
        if (indent < 0) {
            indent = 0;
        }
    }

    public void add(Component component) {
        iTooltip.add(Component.translatable("%s%s", Component.literal("  ".repeat(Math.max(0, indent))), component));
    }

}
