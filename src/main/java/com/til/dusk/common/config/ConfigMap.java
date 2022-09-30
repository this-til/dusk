package com.til.dusk.common.config;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * @author til
 */
public class ConfigMap extends HashMap<IConfigKey<?>, Supplier<?>> {

    public ConfigMap() {
        super();
    }

}
