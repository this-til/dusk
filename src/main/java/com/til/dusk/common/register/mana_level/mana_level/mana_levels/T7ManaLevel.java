package com.til.dusk.common.register.mana_level.mana_level.mana_levels;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.util.DuskColor;

/**
 * @author til
 */
public class T7ManaLevel extends ManaLevel {
    public T7ManaLevel() {
        super("t7");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(ManaLevel.LEVEL, 7)
                .setConfigOfV(ManaLevel.CLOCK, 40)
                .setConfigOfV(ManaLevel.PARALLEL, 7)
                .setConfigOfV(ManaLevel.MAX_BIND, 7)
                .setConfigOfV(ManaLevel.MANA_LOSS, 0.04)
                .setConfigOfV(ManaLevel.MAX_RANGE, 28)
                .setConfigOfV(ManaLevel.COLOR,  new DuskColor(225, 200, 100));
    }
}
