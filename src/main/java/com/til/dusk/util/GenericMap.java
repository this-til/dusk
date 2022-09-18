package com.til.dusk.util;

import com.til.dusk.common.register.RegisterBasics;

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

    public <V> V get(IKey<V> key) {
        Object obj = this.get((Object) key);
        if (obj == null) {
            return null;
        }
        return Util.forcedConversion(obj);
    }

    public <V> void set(IKey<V> key, V v) {
        this.put(key, v);
    }

    public interface IKey<V> {

        class Key<V> implements IKey<V> {
        }

    }

    public interface IGenericMapSupplier{
        <V> V getSet(IKey<V> key);

        boolean hasSet(IKey<?> key);
    }

}
