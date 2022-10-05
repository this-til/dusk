package com.til.dusk.common.config;

import com.til.dusk.util.Util;

import javax.annotation.Nullable;
import java.util.List;

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
    ConfigMap defaultConfigMap();

    abstract class RetainConfigMap implements IAcceptConfigMap {
        public ConfigMap configMap = new ConfigMap();

        public RetainConfigMap() {
            List<ConfigKey<?>> configKeyList = defaultKey();
            if (configKeyList == null) {
                return;
            }
            for (ConfigKey<?> configKey : configKeyList) {
                configMap.setConfig(configKey, Util.forcedConversion(configKey.defaultValue));
            }
        }

        @Override
        public void init(ConfigMap configMap) {
            this.configMap = configMap;
            List<ConfigKey<?>> configKeyList = defaultKey();
            if (configKeyList == null) {
                return;
            }
            for (ConfigKey<?> configKey : configKeyList) {
                if (configMap.containsKey(configKey)) {
                    continue;
                }
                configMap.setConfig(configKey, Util.forcedConversion(configKey.defaultValue));
            }
        }


        @Override
        public ConfigMap defaultConfigMap() {
            return configMap;
        }

        /***
         * 获取默认存在的key
         */
        @Nullable
        public abstract List<ConfigKey<?>> defaultKey();
    }
}
