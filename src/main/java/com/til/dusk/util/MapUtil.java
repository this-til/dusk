package com.til.dusk.util;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * @author til
 */
public class MapUtil {

    public static <K> void addAll(Map<K, Integer> map, @Nullable Map<K, Integer> add) {
        if (add == null) {
            return;
        }
        for (Map.Entry<K, Integer> entry : add.entrySet()) {
            add(map, entry.getKey(), entry.getValue());
        }
    }

    public static <K> void add(Map<K, Integer> map, K k, int i) {
        if (!map.containsKey(k)) {
            map.put(k, i);
            return;
        }
        map.put(k, map.get(k) + i);
    }

}
