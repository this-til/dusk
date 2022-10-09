package com.til.dusk.common.register.mana_level.mana_level.mana_levels;

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
    public void defaultConfig() {
        level = 8;
        clock = 20;
        parallel = 8;
        maxBind = 8;
        manaLoss = 0.03;
        maxRange = 30;
        color = new DuskColor(255, 255, 50);
    }
}
