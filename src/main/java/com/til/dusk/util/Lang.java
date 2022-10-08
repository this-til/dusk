package com.til.dusk.util;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @author til
 */
public class Lang {

    public static Component getLang(Component... components) {
        if (components.length == 0) {
            return Component.empty();
        }
        if (components.length == 1) {
            return components[0];
        }
        return Component.translatable("%s".repeat(components.length), (Object[]) components);
    }

    public static Component getLang(RegisterBasics<?>... registerBasics) {
        if (registerBasics.length == 0) {
            return Component.empty();
        }
        if (registerBasics.length == 1) {
            return Component.translatable(registerBasics[0].name.toLanguageKey());
        }
        List<Component> componentList = new ArrayList<>();
        for (RegisterBasics<?> registerBasic : registerBasics) {
            componentList.add(Component.translatable(registerBasic.name.toLanguageKey()));
        }
        return Component.translatable("%s".repeat(registerBasics.length), componentList.toArray());
    }

    public static Component getLang(String... strings) {
        if (strings.length == 0) {
            return Component.empty();
        }
        if (strings.length == 1) {
            return Component.translatable(strings[0]);
        }
        List<Component> componentList = new ArrayList<>();
        for (String string : strings) {
            componentList.add(Component.translatable(string));
        }
        return Component.translatable("%s".repeat(strings.length), componentList.toArray());
    }

    public static String getKey(String s) {
        return Dusk.MOD_ID + "." + s;
    }


}
