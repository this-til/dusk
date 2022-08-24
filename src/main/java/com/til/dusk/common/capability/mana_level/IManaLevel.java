package com.til.dusk.common.capability.mana_level;

import com.til.dusk.common.capability.IThis;
import com.til.dusk.common.register.mana_level.ManaLevel;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * 代表该实体方块有一个等级
 * @author til
 */
public interface IManaLevel  {

    /***
     * 获取等级
     * @return 等级
     */
    ManaLevel manaLevel();

}
