package com.til.dusk.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author til
 */
public class ReflectionUtil {
    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        return fieldList;
    }

    /***
     * 获取所有继承类和接口
     */
    public static List<Class<?>> getAllExtends(Class<?> c) {
        return getAllExtends(c, new ArrayList<>(8));
    }

    public static List<Class<?>> getAllExtends(Class<?> c, List<Class<?>> list) {
        if (!list.contains(c)) {
            list.add(c);
        }
        for (Class<?> aClass : c.getInterfaces()) {
            getAllExtends(aClass, list);
        }
        Class<?> basics = c.getSuperclass();
        if (basics != null) {
            getAllExtends(basics, list);
        }
        return list;
    }
}
