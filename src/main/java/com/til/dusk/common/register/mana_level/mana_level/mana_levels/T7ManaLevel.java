package com.til.dusk.common.register.mana_level.mana_level.mana_levels;

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
    public void defaultConfig() {
        level = 7;
        clock = 40;
        parallel = 7;
        maxBind = 7;
        manaLoss = 0.04;
        maxRange = 28;
        color = new DuskColor(225, 200, 100);
    }
}
