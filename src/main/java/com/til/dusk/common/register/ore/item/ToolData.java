package com.til.dusk.common.register.ore.item;

public class ToolData {

    /***
     * 耐久
     */
    public int uses = 64;

    /***
     * 储罐最大液体
     */
    public int tankMax = 4000;

    public ToolData setUses(int uses) {
        this.uses = uses;
        return this;
    }

    public ToolData setTankMax(int tankMax) {
        this.tankMax = tankMax;
        return this;
    }
}
