package com.til.dusk.common.capability.clock_time;


import com.til.dusk.common.capability.INBT;
import com.til.dusk.common.capability.IUp;
import com.til.dusk.util.Extension;

/***
 * 时钟时间，在周期结束后触发回调
 * @author til
 */
public interface IClockTime extends INBT, IUp {

    /***
     * 在能力初始化时，添加回调
     * @param black 会调函数
     */
    void addBlock(Extension.Action black);

}
