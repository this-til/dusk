package com.til.dusk.common.config;

import com.til.dusk.util.Extension;
import com.til.dusk.util.Util;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * @author til
 */
public class ConfigMap extends HashMap<ConfigKey<?>, Extension.VariableData_2<Supplier<?>, ?>> {

    public ConfigMap() {
        super();
    }

    public <V> V get(ConfigKey<V> configKey) {
        if (this.containsKey(configKey)) {
            Extension.VariableData_2<Supplier<V>, V> pack = Util.forcedConversion(get((Object) configKey));
            if (pack.d2 == null && pack.d1 != null) {
                pack.d2 = pack.d1.get();
                pack.d1 = null;
            }
            return pack.d2;
        }
        if (configKey.defaultValue != null) {
            this.setConfig(configKey, configKey.defaultValue);
            return get(configKey);
        }
        throw new RuntimeException("配置中发现空配置项，但他的默认配置值为空");
    }

    public <V> ConfigMap setConfigOfV(ConfigKey<V> key, V v) {
        put(key, new Extension.VariableData_2<>(null, v));
        return this;
    }

    public <V> ConfigMap setConfig(ConfigKey<V> key, Supplier<V> v) {
        put(key, new Extension.VariableData_2<>(v, null));
        return this;
    }

    public ConfigMap setConfig(ConfigKey.VoidConfigKey key) {
        this.setConfig(key, null);
        return this;
    }

}
