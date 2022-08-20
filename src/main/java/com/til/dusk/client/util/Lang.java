package com.til.dusk.client.util;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import net.minecraft.client.resources.language.I18n;


public class Lang {

    public static String getLang(RegisterBasics<?> registerBasics, RegisterBasics<?> registerBasics1) {
        return (I18n.get(registerBasics.name.getNamespace() + "." + registerBasics.name.getPath() + ".name") +
                I18n.get(registerBasics1.name.getNamespace() + "." + registerBasics1.name.getPath() + ".name").trim());
    }

    public static String getOreBlockLang(RegisterBasics<?> ore) {
        return (I18n.get(ore.name.getNamespace() + "." + ore.name.getPath() + ".name") +
        I18n.get(Dusk.MOD_ID + ".ore.name").trim());
    }

    public static String getLang(RegisterBasics<?> registerBasics) {
      return   I18n.get(registerBasics.name.getNamespace() + "." + registerBasics.name.getPath() + ".name");
    }

    public static String getLang(String tile) {
        return I18n.get(Dusk.MOD_ID + "." + tile + ".name");
    }



}
