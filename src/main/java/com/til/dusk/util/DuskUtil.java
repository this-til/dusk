package com.til.dusk.util;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * 工具类
 * @author til
 */
public class DuskUtil {
    @SuppressWarnings("unchecked")
    public static <T> T forcedConversion(Object obj) {
        if (obj == null) {
            return null;
        }
        return (T) obj;
    }

    /***
     * 获取所有字段
     * @param type 反射类型
     * @return 字段
     */
    public static List<Field> getAllField(Class<?> type) {
        Class<?> clazz = type;
        List<Field> fieldsList = new ArrayList<>();
        while (clazz != null) {
            Field[] declaredFields = clazz.getFields();
            Collections.addAll(fieldsList, declaredFields);
            clazz = clazz.getSuperclass();
        }
        return fieldsList;
    }
}
