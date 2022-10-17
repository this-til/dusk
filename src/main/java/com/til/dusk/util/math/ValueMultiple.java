package com.til.dusk.util.math;


import com.til.dusk.common.register.attribute.entity.Attribute;
import com.til.dusk.common.register.attribute.entity.multiple.AttributeMultiple;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
public class ValueMultiple {

    public double basics;
    @Nullable
    public Map<AttributeMultiple, Double> map;

    public ValueMultiple(double basics) {
        this.basics = basics;
    }

    public void addBasics(double d) {
        basics += d;
    }

    public void addMultiple(AttributeMultiple multiple, double d) {
        double old;
        if (map == null) {
            map = new HashMap<>(1);
        }
        if (map.containsKey(multiple)) {
            old = map.get(multiple);
        } else {
            old = multiple.basicsValue;
            map.put(multiple, old);
        }
        map.put(multiple, d + old);
    }

    public double getV() {
        if (map == null) {
            return basics;
        }
        double v = basics;
        for (Map.Entry<AttributeMultiple, Double> entry : map.entrySet()) {
            v *= entry.getKey().range.ofValue(entry.getValue());
        }
        return v;
    }
}
