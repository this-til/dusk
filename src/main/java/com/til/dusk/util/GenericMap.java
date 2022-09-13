package com.til.dusk.util;

import javax.annotation.Nullable;
import java.util.HashMap;

/**
 * 一个泛型表
 *
 * @author til
 */
public class GenericMap extends HashMap<GenericMap.IKey<?>, Object> {

    public GenericMap() {
        super();
    }

    @Override
    public boolean containsKey(Object key) {
        if (!super.containsKey(key)) {
            return false;
        }
        return this.get(key) != null;
    }

    public <V> V get(IKey<V> key) {
        Object obj = this.get((Object) key);
        return key.as(obj);
    }

    public <V> void set(IKey<V> key, V v) {
        this.put(key, v);
    }

    public interface IKey<V> {
        V as(@Nullable Object obj);

        boolean has(@Nullable Object obj);

        class Key<V> implements IKey<V> {
            @Override
            public V as(@Nullable Object obj) {
                if (obj == null) {
                    return null;
                }
                return Util.forcedConversion(obj);
            }

            @Override
            public boolean has(@Nullable Object obj) {
                return obj != null;
            }
        }

        class BoolKey implements IKey<Boolean> {
            @Override
            public Boolean as(@Nullable Object obj) {
                if (obj == null) {
                    return false;
                }
                return obj.equals(false);
            }

            @Override
            public boolean has(@Nullable Object obj) {
                return Boolean.FALSE.equals(obj);
            }
        }
    }

}
