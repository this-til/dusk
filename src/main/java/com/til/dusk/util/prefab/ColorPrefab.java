package com.til.dusk.util.prefab;

import java.awt.*;

public class ColorPrefab {

    /***
     * 物品流
     */
    public static final Color ITEM_IO = new Color(43, 255, 33, 255);

    /***
     * 灵气流
     */
    public static final Color MANA_IO = new Color(255, 255, 0, 255);

    /***
     * 流体流
     */
    public static final Color FLUID_IO = new Color(29, 237, 255, 255);

    /***
     * 控制器选择颜色
     */
    public static final Color CONTROL_TAG = new Color(255, 255, 0, 255);

    /***
     * 耀阳晶体染色
     */
    public static final Color SUNLIGHT_COLOR = new Color(255, 255, 0, 255);

    /***
     * 耀月晶体染色
     */
    public static final Color MOONLIGHT_COLOR = new Color(0, 125, 255, 255);

    public static final Color RAIN_COLOR = new Color(110, 174, 255, 255);

    public static final Color GRAYSCALE_REDUCTION_1 = new Color(200, 200, 200, 255);

    public static final Color GRAYSCALE_REDUCTION_2 = new Color(175, 175, 175, 255);

    public static final Color GRAYSCALE_REDUCTION_3 = new Color(150, 150, 150, 255);

    /***
     * 混合颜色，不考虑a通道
     */
    public static Color blend(Color cor1, Color cor2) {
        return new Color(cor1.getRed() / 255.0f * cor2.getRed() / 255.0f,
                cor1.getGreen() / 255.0f * cor2.getGreen() / 255.0f,
                cor1.getBlue() / 255.0f * cor2.getBlue() / 255.0f,
                cor1.getAlpha() / 255.0f);
    }



}
