package com.til.dusk.util;

/***
 * 一个颜色类，他代替{@linkplain java.awt.Color}
 * @author til
 */
public class DuskColor {
    public final int value;

    public DuskColor(int rgb) {
        value = 0xff000000 | rgb;
    }

    public DuskColor(int rgba, boolean hasAlpha) {
        if (hasAlpha) {
            value = rgba;
        } else {
            value = 0xff000000 | rgba;
        }
    }

    public DuskColor(int r, int g, int b, int a) {
        value = ((a & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8) |
                ((b & 0xFF));
    }

    public DuskColor(int r, int g, int b) {
        this(r, g, b, 255);
    }

    public DuskColor(float r, float g, float b) {
        this((int) (r * 255 + 0.5), (int) (g * 255 + 0.5), (int) (b * 255 + 0.5));
    }

    public DuskColor(float r, float g, float b, float a) {
        this((int) (r * 255 + 0.5), (int) (g * 255 + 0.5), (int) (b * 255 + 0.5), (int) (a * 255 + 0.5));
    }


    public int getRed() {
        return (getRGB() >> 16) & 0xFF;
    }

    public int getGreen() {
        return (getRGB() >> 8) & 0xFF;
    }


    public int getBlue() {
        return (getRGB()) & 0xFF;
    }


    public int getAlpha() {
        return (getRGB() >> 24) & 0xff;
    }

    public int getRGB() {
        return value;
    }

    public DuskColor blend(DuskColor baseColor) {
        float sR = this.getRed() / 255f;
        float sG = this.getGreen() / 255f;
        float sB = this.getBlue() / 255f;
        float sA = this.getAlpha() / 255f;
        float dR = baseColor.getRed() / 255f;
        float dG = baseColor.getGreen() / 255f;
        float dB = baseColor.getBlue() / 255f;
        float dA = baseColor.getAlpha() / 255f;
        float rR = sR * sA + dR * (1.0f - sA);
        float rG = sG * sA + dG * (1.0f - sA);
        float rB = sB * sA + dB * (1.0f - sA);
        float rA = dA + sA * (1.0f - dA);
        return new DuskColor(rR, rG, rB, rA);
    }
}
