package com.til.dusk.util.nbt;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;

/**
 * 一个供对象灵活调用的序列化工具
 *
 * @author til
 */
public interface ISerialize {

    /***
     * 转为Tag
     * @return tag
     */
    CompoundTag as();

    /***
     * 初始化
     * @param t Tag数据
     */
    void init(CompoundTag t);

    /***
     * 转为Json
     * @return Json
     */
    JsonObject asJson();

    /***
     * 初始化
     * @param json json数据
     */
    void init(JsonObject json);

}
