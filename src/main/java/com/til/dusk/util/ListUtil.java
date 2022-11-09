package com.til.dusk.util;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class ListUtil {

    public static <K> void addAll(List<Extension.VariableData_2<K, Integer>> map, @Nullable List<Extension.VariableData_2<K, Integer>> add) {
        if (add == null) {
            return;
        }
        for (Map.Entry<K, Integer> entry : add) {
            add(map, entry.getKey(), entry.getValue());
        }
    }

    public static <K> void add(List<Extension.VariableData_2<K, Integer>> map, K k, int i) {
        for (Extension.VariableData_2<K, Integer> entry : map) {
            if (entry.k.equals(k)) {
                entry.v += i;
                return;
            }
        }

        map.add(new Extension.VariableData_2<>(k, i));
    }
}
