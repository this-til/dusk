package com.til.dusk.common.capability.black;

import com.til.dusk.util.Extension;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

/**
 * 这是一个回调能力，一般用于当外界发生什么，调用内部注册函数
 *
 * @author til
 */
public interface IBack {

    /***
     * 添加一个回调
     * @param blackType 回调类型
     * @param run 回调函数
     * @param <T> 回调数据类型
     */
    <T> void add(BlackType<T> blackType, Extension.Action_1V<T> run);


    void add( IBack back);

    /***
     * 执行回调
     * @param blackType 回调类型
     * @param t 数据
     * @param <T> 回调数据类型
     */
    <T> void run(BlackType<T> blackType, T t);

    void remove(IBack back);

    /***
     * 空类型
     */
    BlackType<Void> VOID = new BlackType<>();

    /***
     * 当刷新时
     */
    BlackType<Void> UP = new BlackType<>();

    BlackType<LivingEvent.LivingTickEvent> LIVING_TICK_EVENT = new BlackType<>();
    BlackType<LivingEquipmentChangeEvent> LIVING_EQUIPMENT_CHANGE_EVENT = new BlackType<>();

    /***
     * 回调类型
     * @param <T>
     */
    class BlackType<T> {
    }


}
