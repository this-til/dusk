package com.til.dusk.common.capability.clock;

import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.register.shaped.ShapedHandleProcess;

/**
 * @author til
 */
public interface IManaClock extends IClock {
    /***
     * 需要控制器提供灵气输入
     * @return 控制器
     */
    IControl getControl();

    /***
     * 获取每次更新消耗的灵气
     * @return 消耗灵气
     */
    long getConsumeMana();

    /***
     * 获取处理状态
     * @return 处理状态
     */
    ShapedHandleProcess getProcess();

    /***
     * 设置状态
     * @param process 新状态
     */
    void setProcess(ShapedHandleProcess process);
}
