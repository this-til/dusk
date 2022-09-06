package com.til.dusk.common.register.ore;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.util.GenericMap;
import com.til.dusk.util.StaticTag;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;


import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Ore extends RegisterBasics.UnitRegister<Ore, OreItem, OreBlock, OreFluid> {

    public static Supplier<IForgeRegistry<Ore>> ORE;

    public static Ore testOre;

    /***
     * 灵银
     */
    public static Ore spiritSilver;

    /***
     * 绯红
     */
    public static Ore crimson;

    /***
     * 群青
     */
    public static Ore ultramarine;

    /***
     * 靛青
     */
    public static Ore indigo;

    /***
     * 紫罗兰
     */
    public static Ore violet;

    /***
     * 蓟
     */
    public static Ore thistle;

    /***
     * 秘鲁
     */
    public static Ore peru;

    /***
     * 金麒麟
     */
    public static Ore goldenrod;

    /***
     * 水鸭
     */
    public static Ore greenTeal;

    /***
     * 间春绿色
     */
    public static Ore mediumspringgreen;

    /***
     * 菊兰
     */
    public static Ore cornflowerblue;

    /***
     * 艾利斯兰
     */
    public static Ore aliceblue;

    /***
     * 玄青
     */
    public static Ore darkGreen;

    /***
     * 星河合金
     */
    public static Ore starRiver;

    /***
     * 实体灵气
     */
    public static Ore mana;


    /***
     * 日耀
     */
    public static Ore sunlight;

    /***
     * 月耀
     */
    public static Ore moonlight;

    /***
     * 雨灵
     */
    public static Ore rain;

    /***
     * 柳黄
     */
    public static Ore willowYellow;

    /***
     * 朱砂
     */
    public static Ore cinnabar;

    /***
     * 桃红
     */
    public static Ore pink;

    /***
     * 藏蓝
     */
    public static Ore tibetanBlue;

    /***
     * 松柏绿
     */
    public static Ore pineCypress;

    /***
     * 黄栌
     */
    public static Ore cotinusCoggygria;

    /***
     * 丁香
     */
    public static Ore clove;

    /***
     * 藕荷
     */
    public static Ore lotusRoot;

    /***
     * 乌
     */
    public static Ore crow;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE = event.create(new RegistryBuilder<Ore>().setName(new ResourceLocation(Dusk.MOD_ID, "ore")));
        mana = new Ore("mana", ColorPrefab.MANA_IO, ManaLevel.t1).removeTag(HAS_MINERAL_BLOCK, HAS_BLOCK, IS_METAL);
        testOre = new Ore("test_ore", new Color(255, 200, 140), ManaLevel.t1);
        spiritSilver = new Ore("spirit_silver", new Color(106, 235, 255), ManaLevel.t1);

        crimson = new Ore("crimson", new Color(0xE03131), ManaLevel.t2);
        ultramarine = new Ore("ultramarine", new Color(0x3d99), ManaLevel.t2);
        indigo = new Ore("indigo", new Color(0x4B0082), ManaLevel.t2);
        violet = new Ore("violet", new Color(0xEE82EE), ManaLevel.t2);
        thistle = new Ore("thistle", new Color(0xD8BFD8), ManaLevel.t2);
        peru = new Ore("peru", new Color(0xCD853F), ManaLevel.t2);
        goldenrod = new Ore("goldenrod", new Color(0xDAA520), ManaLevel.t2);
        greenTeal = new Ore("green_teal", new Color(0x008080), ManaLevel.t2);
        mediumspringgreen = new Ore("mediumspringgreen", new Color(0x00FA9A), ManaLevel.t2);
        cornflowerblue = new Ore("cornflowerblue", new Color(0x6495ED), ManaLevel.t2);
        aliceblue = new Ore("aliceblue", new Color(0xF0F8FF), ManaLevel.t2);
        darkGreen = new Ore("dark_green", new Color(0x3d3b4f), ManaLevel.t2);

        sunlight = new Ore("sunlight", ColorPrefab.SUNLIGHT_COLOR, ManaLevel.t2).setCrysta();
        moonlight = new Ore("moonlight", ColorPrefab.MOONLIGHT_COLOR, ManaLevel.t2).setCrysta();
        rain = new Ore("rain", ColorPrefab.RAIN_COLOR, ManaLevel.t2).setCrysta();
        willowYellow = new Ore("willow_yellow", new Color(0xc9dd22), ManaLevel.t2).setCrysta();
        cinnabar = new Ore("cinnabar", new Color(0xff461f), ManaLevel.t2).setCrysta();
        pink = new Ore("pink", new Color(0xf47983), ManaLevel.t2).setCrysta();
        tibetanBlue = new Ore("tibetan_blue", new Color(0x3b2e7e), ManaLevel.t2).setCrysta();
        pineCypress = new Ore("pine_cypress", new Color(0x057748), ManaLevel.t2).setCrysta();
        cotinusCoggygria = new Ore("cotinus_coggygria", new Color(0xe29c45), ManaLevel.t2).setCrysta();
        clove = new Ore("clove", new Color(0xcca4e3), ManaLevel.t2).setCrysta();
        lotusRoot = new Ore("lotus_root", new Color(0xe4c6d0), ManaLevel.t2).setCrysta();
        crow = new Ore("crow", new Color(0x725e82), ManaLevel.t2).setCrysta();
    }

    /***
     * 矿物的颜色
     */
    public final Color color;

    /***
     * 矿物处理的等级
     */
    public final ManaLevel manaLevel;

    /***
     * 强度系数，对应方块的采集时间和防爆，和加工时间倍数
     */
    public double strength = 1f;

    /***
     * 加工消耗灵气倍数
     */
    public double consume = 1f;


    public Ore(String name, Color color, ManaLevel manaLevel) {
        this(new ResourceLocation(Dusk.MOD_ID, name), color, manaLevel);
        addTag(HAS_MINERAL_BLOCK, HAS_BLOCK, HAS_FLUID, IS_METAL);
    }


    public Ore(ResourceLocation name, Color color, ManaLevel manaLevel) {
        super(name, ORE);
        this.color = color;
        this.manaLevel = manaLevel;
    }

    public Ore setStrength(double strength) {
        this.strength = strength;
        return this;
    }

    public Ore setConsume(double consume) {
        this.consume = consume;
        return this;
    }

    /***
     * 设置为晶体
     */
    public Ore setCrysta() {
        removeTag(IS_METAL);
        addTag(IS_CRYSTA);
        return this;
    }

    @Override
    public Supplier<IForgeRegistry<OreItem>> itemRegistry() {
        return OreItem.ORE_ITEM;
    }

    @Override
    public Supplier<IForgeRegistry<OreBlock>> blockRegistry() {
        return OreBlock.ORE_BLOCK;
    }

    @Override
    public Supplier<IForgeRegistry<OreFluid>> fluidRegistry() {
        return OreFluid.ORE_FLUID;
    }

    public static List<Ore> screen(StaticTag... staticTags) {
        List<Ore> oreList = new ArrayList<>();
        for (Ore ore : ORE.get()) {
            if (ore.hasTag(staticTags)) {
                oreList.add(ore);
            }
        }
        return oreList;
    }

    /***
     * 洗矿副产物
     */
    public static final GenericMap.IKey<Supplier<Ore[]>> WASH_BYPRODUCT = new GenericMap.IKey.Key<>();

    /***
     * 离心副产物
     */
    public static final GenericMap.IKey<Supplier<Ore[]>> CENTRIFUGE_BYPRODUCT = new GenericMap.IKey.Key<>();

    /***
     * 筛选副产物
     */
    public static final GenericMap.IKey<Supplier<Ore[]>> SCREEN_BYPRODUCT = new GenericMap.IKey.Key<>();

    /***
     * 混合配方
     */
    public static final GenericMap.IKey<Supplier<Ore[]>> BLEND_BYPRODUCT = new GenericMap.IKey.Key<>();

    /***
     * 有矿物方块
     */
    public static final StaticTag HAS_MINERAL_BLOCK = new StaticTag("HAS_MINERAL_BLOCK", List.of());

    /***
     * 拥有方块
     */
    public static final StaticTag HAS_BLOCK = new StaticTag("HAS_BLOCK", List.of());

    /***
     * 有流体
     */
    public static final StaticTag HAS_FLUID = new StaticTag("HAS_FLUID", List.of());

    /***
     * 是金属
     */
    public static final StaticTag IS_METAL = new StaticTag("IS_METAL", List.of());

    /***
     * 是晶体
     */
    public static final StaticTag IS_CRYSTA = new StaticTag("IS_CRYSTA", List.of());


}
