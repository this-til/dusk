package com.til.dusk.util.tooltip_pack;

import net.minecraft.network.chat.Component;

import java.util.List;

/**
 * @author til
 */
public class ComponentPack implements IComponentPack {

    public final List<Component> iTooltip;
    public int indent;

    public ComponentPack(List<Component> iTooltip) {
        this.iTooltip = iTooltip;
    }

    @Override
    public void indent() {
        indent++;
    }

    @Override
    public void returnIndent() {
        indent--;
        if (indent < 0) {
            indent = 0;
        }
    }

    @Override
    public void resetIndent() {
        indent = 0;
    }

    @Override
    public void add(Component component) {
        iTooltip.add(Component.translatable("%s%s", Component.literal("  ".repeat(Math.max(0, indent))), component));
    }

}
