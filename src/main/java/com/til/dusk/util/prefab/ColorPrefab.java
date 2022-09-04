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
        float fAlp1 = cor1.getAlpha() / 255.0f;
        float fAlp2 = cor2.getAlpha() / 255.0f;
        float fAlpBlend = fAlp1 + fAlp2 - fAlp1 * fAlp2;
        float fRed1 = cor1.getRed() / 255.0f;
        float fRed2 = cor2.getRed() / 255.0f;
        float fRedBlend = crCalculateBlend(fAlp1, fAlp2, fRed1, fRed2);
        float fGreen1 = cor1.getGreen() / 255.0f;
        float fGreen2 = cor2.getGreen() / 255.0f;
        float fGreenBlend = crCalculateBlend(fAlp1, fAlp2, fGreen1, fGreen2);
        float fBlue1 = cor1.getBlue() / 255.0f;
        float fBlue2 = cor2.getBlue() / 255.0f;
        float fBlueBlend = crCalculateBlend(fAlp1, fAlp2, fBlue1, fBlue2);
        return new Color(fAlpBlend, fRedBlend, fGreenBlend, fBlueBlend);
    }


    public static float crCalculateBlend(float a1, float a2, float c1, float c2) {
        return (c1 * a1 * (1.0f - a2) + c2 * a2) / (a1 + a2 - a1 * a2);
    }

}
