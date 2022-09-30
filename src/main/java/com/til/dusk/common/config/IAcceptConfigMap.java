package com.til.dusk.common.config;

import com.til.dusk.util.GenericMap;

/***
 * 此接口表示对象支持配置MAP
 * @author til
 */
public interface IAcceptConfigMap {
    /***
     * 创建好之后调用传入配置表
     * @param configMap 反序列化后的配置表
     */
    void init(ConfigMap configMap);

    /***
     * 返回配置表
     * 用于反序列化
     * @return 默认配置表
     */
    ConfigMap configMap();
}
