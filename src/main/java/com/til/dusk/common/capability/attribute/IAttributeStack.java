package com.til.dusk.common.capability.attribute;

import com.til.dusk.common.register.attribute.entity.Attribute;
import com.til.dusk.common.register.attribute.entity.limit.AttributeLimit;

/**
 * @author til
 */
public interface IAttributeStack {

    /***
     * 获取一个基础属性
     * @param attribute 属性名
     * @return 属性值，这个值是原始的
     */
    double getBasics(Attribute attribute);

    /***
     * 获取一个基础属性
     * @param attributeLimit 属性名
     * @return 属性值，这个值是原始的[0,1]
     */
    double getBasics(AttributeLimit attributeLimit);

    /***
     * 设置基础值
     * @param attribute 属性名
     * @param d 值
     * @return 原来值
     */
    double setBasics(Attribute attribute, double d);

    /***
     * 设置基础值
     * @param attributeLimit 属性名
     * @param d 值[0,1]
     * @return 原来值
     */
    double setBasics(AttributeLimit attributeLimit, double d);

    /***
     * 增加基础值
     * @param attribute 属性名
     * @param add 值[0,1]
     * @return 原来值
     */
    double addBasics(Attribute attribute, double add);

    /***
     * 获取当前值
     * @param attribute 属性名
     * @return 当前值
     */
    double get(Attribute attribute);

    /***
     * 获取当前值
     * @param attributeLimit 属性名
     * @return 当前值
     */
    double get(AttributeLimit attributeLimit);

    /***
     * 这是属性
     * @param attributeLimit 属性名
     * @param d 属性当前值（将根据绑定属性当前值转为[0,1]）
     * @return 之前基础值
     */
    double set(AttributeLimit attributeLimit, double d);

    /***
     * 增加属性
     * @param attributeLimit 属性名
     * @param d 属性当前值（将根据绑定属性当前值转为[0,1]）
     * @return 之前基础值
     */
    double add(AttributeLimit attributeLimit, double d);
}
