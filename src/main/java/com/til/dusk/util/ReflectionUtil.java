package com.til.dusk.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtil {
    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        return fieldList;
    }

    /***
     * 获取所有继承类和接口
     */
    public static List<Class<?>> getAllExtends(Class<?> c) {
        List<Class<?>> list = new ArrayList<>();
        Class<?> basics = c;
        while (basics != null) {
            list.add(basics);
            list.addAll(List.of(basics.getInterfaces()));
            basics = basics.getSuperclass();
        }
        return list;
    }
}
