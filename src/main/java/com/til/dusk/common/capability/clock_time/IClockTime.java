package com.til.dusk.common.capability.clock_time;


import com.til.dusk.common.capability.INBT;
import com.til.dusk.common.capability.ITack;
import com.til.dusk.common.capability.mana_level.IManaLevel;
import com.til.dusk.util.AllNBT;
import com.til.dusk.util.Extension;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

import java.util.ArrayList;
import java.util.List;

/***
 * 时钟时间，在周期结束后触发回调
 * @author til
 */
public interface IClockTime extends INBT, ITack {

    /***
     * 在能力初始化时，添加回调
     * @param black 会调函数
     */
    void addBlock(Extension.Action black);

}
