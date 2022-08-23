package com.til.dusk.util;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;


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
            return Component.translatable(getKey(registerBasics[0]));
        }
        List<Component> componentList = new ArrayList<>();
        for (RegisterBasics<?> registerBasic : registerBasics) {
            componentList.add(Component.translatable(getKey(registerBasic)));
        }
        return Component.translatable("%s".repeat(registerBasics.length), componentList.toArray());
    }

/*    public static Component getLangFormat(String key, Object... objects) {
        return Component.translatable(key, objects);
    }*/

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

    public static String getKey(RegisterBasics<?> registerBasics) {
        return registerBasics.name.getNamespace() + "." + registerBasics.getLangKey() + ".name";
    }

    public static String getKey(String s) {
        return Dusk.MOD_ID + "." + s + ".name";
    }

    public static String getKey(ResourceLocation resourceLocation) {
        return resourceLocation.getNamespace() + "." + resourceLocation.getPath() + ".name";
    }

}
