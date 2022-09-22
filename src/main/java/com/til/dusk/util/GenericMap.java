package com.til.dusk.util;

import com.til.dusk.common.register.RegisterBasics;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * 一个泛型表
 *
 * @author til
 */
public class GenericMap extends HashMap<GenericMap.IKey<?>, Extension.VariableData_2<Supplier<?>, ?>> {

    public GenericMap() {
        super();
    }

    public <V> V get(IKey<V> key) {
        if (!this.containsKey(key)) {
            return null;
        }
        Extension.VariableData_2<Supplier<V>, V> pack = Util.forcedConversion(this.get((Object) key));
        if (pack.d2 == null && pack.d1 != null) {
            pack.d2 = pack.d1.get();
        }
        return pack.d2;
    }

    public <V> void set(IKey<V> key, V v) {
        this.put(key, new Extension.VariableData_2<>(null, v));
    }

    public <V> void set(IKey<V> key, Supplier<V> v) {
        this.put(key, new Extension.VariableData_2<>(v, null));
    }

    public interface IKey<V> {

        class Key<V> implements IKey<V> {
        }

    }

    public interface IGenericMapSupplier {
        <V> V getSet(IKey<V> key);

        boolean hasSet(IKey<?> key);
    }

}
