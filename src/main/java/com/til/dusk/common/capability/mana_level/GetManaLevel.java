package com.til.dusk.common.capability.mana_level;

import com.til.dusk.common.register.mana_level.ManaLevel;
import net.minecraft.world.level.block.entity.BlockEntity;

public record GetManaLevel(BlockEntity tileEntity, ManaLevel manaLevel) implements IManaLevel {

    /***
     * 返回自己
     */
    @Override
    public BlockEntity getThis() {
        return tileEntity;
    }
}
