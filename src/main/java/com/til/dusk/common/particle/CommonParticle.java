package com.til.dusk.common.particle;

import com.til.dusk.util.Pos;
import net.minecraft.world.level.Level;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 粒子效果的服务端控制
 *
 * @author til
 */

public enum CommonParticle implements IParticleRegister {
    /***
     * 空粒子效果
     */
    AIR,
    /***
     * 灵气转移
     */
    MANA_TRANSFER,
    /***
     * 物品转移
     */
    ITEM_TRANSFER,
    /***
     * 流体转移
     */
    FLUID_TRANSFER;


    public static final Map<Level, List<Data>> MAP = new HashMap<>();

    @Override
    public String type() {
        return toString();
    }

    public static class Data {
        //public static final String TYPS = "type";
        //public static final String START = "start";
        //public static final String END = "end";
        //public static final String COLOR = "color";
        //public static final String DENSITY = "density";

        /***
         * 粒子类型
         */
        public String type = "";
        /***
         * 开始点
         */
        public Pos start = new Pos();
        /***
         * 结束点
         */
        public Pos end = new Pos();
        /***
         * 颜色
         */
        public Color color = new Color(255, 255, 255, 255);
        /***
         * 密度
         */
        public double density;

        public Data() {
        }

        public Data(String type, Pos start, Pos end, Color color, double density) {
            this.type = type;
            this.start = start;
            this.end = end;
            this.color = color;
            this.density = density;
        }


    }
}
