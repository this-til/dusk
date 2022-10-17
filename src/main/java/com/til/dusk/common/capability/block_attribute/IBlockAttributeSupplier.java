package com.til.dusk.common.capability.block_attribute;

import com.google.common.collect.ImmutableMap;
import com.til.dusk.common.register.attribute.block.BlockAttribute;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author til
 */
public interface IBlockAttributeSupplier {
    /***
     * 获取属性表
     * @return 这是一个不可更改表
     */
    @Nullable
    Map<BlockAttribute<?>, ?> get();

    class Pack implements IBlockAttributeSupplier {

        public final Map<BlockAttribute<?>, ?> map;
        public final Supplier<Boolean> canSupplier;

        public Pack(Map<BlockAttribute<?>, ?> map, Supplier<Boolean> canSupplier) {
            this.map = ImmutableMap.copyOf(map);
            this.canSupplier = canSupplier;
        }

        @org.jetbrains.annotations.Nullable
        @Override
        public Map<BlockAttribute<?>, ?> get() {
            if (!canSupplier.get()) {
                return null;
            }
            return map;
        }
    }
}
