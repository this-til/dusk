package com.til.dusk.util;

import com.til.dusk.common.register.RegisterBasics;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author til
 */
public class ResourceLocationUtil {

    public static ResourceLocation fuseName(String namespace, String splicing, String[] strings) {
        return new ResourceLocation(namespace, String.join(splicing, Arrays.asList(strings)));
    }

    public static ResourceLocation fuseName(String splicing, RegisterBasics<?>... registerBasics) {
        ResourceLocation[] strings = new ResourceLocation[registerBasics.length];
        for (int i = 0; i < registerBasics.length; i++) {
            strings[i] = registerBasics[i].name;
        }
        return fuseName(splicing, strings);
    }

    public static ResourceLocation fuseName(RegisterBasics<?>... registerBasics) {
        return fuseName("_", registerBasics);
    }

    public static ResourceLocation fuseName(String splicing, ResourceLocation... name) {
        List<String> namespace = new ArrayList<>(name.length);
        String[] path = new String[name.length];
        for (int i = 0; i < name.length; i++) {
            ResourceLocation resourceLocation = name[i];
            if (!namespace.contains(resourceLocation.getNamespace())) {
                namespace.add(resourceLocation.getNamespace());
            }
            path[i] = resourceLocation.getPath();
        }
        return fuseName(String.join("_", namespace), splicing, path);
    }

    public static ResourceLocation fuseName(String namespace, String processor) {
        return new ResourceLocation(namespace, processor);
    }
}
