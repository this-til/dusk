package com.til.dusk.common.config;

import com.til.dusk.util.Util;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * @author til
 */
public class ConfigMap extends HashMap<IConfigKey<?>, Supplier<?>> {

    public ConfigMap() {
        super();
    }

    public <V> V get(IConfigKey<V> configKey) {
        if (this.containsKey(configKey)) {
            return Util.forcedConversion(this.get((Object) configKey).get());
        }
        if (configKey.defaultValue != null) {
            this.put(configKey, configKey.defaultValue);
            return get(configKey);
        }
        throw new RuntimeException("配置中发现空配置项，但他的默认配置值为空");
    }

}
