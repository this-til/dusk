package com.til.dusk.common.register.multiblock;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.util.Pos;
import com.til.dusk.util.gson.AcceptTypeJson;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

/**
 * @author til
 */
@AcceptTypeJson
public class BlockPosPack {

    /***
     * 满足的方块
     */
    public Delayed<TagKey<Block>> tag;

    /***
     * 距离主控的偏移
     */
    public Pos deviation;

    /***
     * 是必要的
     */
    public boolean integral;
}
