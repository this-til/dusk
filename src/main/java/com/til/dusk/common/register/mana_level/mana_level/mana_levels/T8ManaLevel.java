package com.til.dusk.common.register.mana_level.mana_level.mana_levels;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.util.DuskColor;

/**
 * @author til
 */
public class T8ManaLevel extends ManaLevel {
    public T8ManaLevel() {
        super("t8");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(ManaLevel.LEVEL, 8)
                .setConfigOfV(ManaLevel.CLOCK, 20)
                .setConfigOfV(ManaLevel.PARALLEL, 8)
                .setConfigOfV(ManaLevel.MAX_BIND, 8)
                .setConfigOfV(ManaLevel.MANA_LOSS, 0.03)
                .setConfigOfV(ManaLevel.MAX_RANGE, 30)
                .setConfigOfV(ManaLevel.COLOR,  new DuskColor(255, 255, 50));
    }
}
