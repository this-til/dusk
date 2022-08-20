package com.til.dusk.common.capability;

import net.minecraft.world.level.block.entity.BlockEntity;

public interface IThis<T> {
    /***
     * 返回自己
     */
    T getThis();
}
