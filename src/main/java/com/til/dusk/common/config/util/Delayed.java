package com.til.dusk.common.config.util;

import java.util.function.Supplier;

/***
 * 延迟获取值
 * @author til
 */
public class Delayed<E> {
    public Supplier<E> supplier;
    public E e;

    public Delayed(Supplier<E> supplier) {
        this.supplier = supplier;
    }

    public E get() {
        if (e == null) {
            if (supplier != null) {
                e = supplier.get();
            }
            supplier = null;
        }
        return e;
    }

}
