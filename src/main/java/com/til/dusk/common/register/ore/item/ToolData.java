package com.til.dusk.common.register.ore.item;

public class ToolData {

    /***
     * 耐久
     */
    public int uses = 64;

    /***
     * 储罐最大液体
     */
    public int tankMax = 6400;

    public ToolData setUses(int uses) {
        this.uses = uses;
        return this;
    }
}
