package com.til.dusk.common.register.ore;

import com.til.dusk.Dusk;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.data.tag.FluidTag;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.common.register.skill.Skill;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.GenericMap;
import com.til.dusk.util.pack.*;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class
Ore extends RegisterBasics.UnitRegister<Ore, OreItem, OreBlock, OreFluid> {

    public static Supplier<IForgeRegistry<Ore>> ORE;

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
     * 虚空
     */
    public static Ore _void;

    /***
     * 星河合金
     */
    public static Ore starRiver;

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

    //fluid

    /***
     * 实体灵气
     */
    public static Ore mana;

    /***
     * 营养液
     */
    public static Ore nutrient;

    /***
     * 质空
     */
    public static Ore natureAir;

    /***
     * 源空
     */
    public static Ore sourceAir;

    /***
     * 素空
     */
    public static Ore elementAir;
    /***
     * 质
     */
    public static Ore nature;

    /***
     * 源
     */
    public static Ore source;

    /***
     * 素
     */
    public static Ore element;


    /***
     * 高能红石
     */
    public static Ore highEnergyRedStone;

    /***
     * 冷却液
     */
    public static Ore coolant;

    /***
     * 灵气融液
     */
    public static Ore dissolutionMana;

    /***
     * 培养液
     */
    public static Ore culture;

    /***
     * 干细胞
     */
    public static Ore stemCell;

    /***
     * 神经元细胞
     */
    public static Ore neuronCell;

    /***
     * 供养细胞
     */
    public static Ore supportCell;

    /***
     * 感知细胞
     */
    public static Ore perceptionCell;

    /***
     * 肌联蛋白
     */
    public static Ore sarconectin;

    /***
     * uu
     */
    public static Ore uu;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE = event.create(new RegistryBuilder<Ore>().setName(new ResourceLocation(Dusk.MOD_ID, "ore")));
        spiritSilver = new Ore("spirit_silver", new DuskColor(106, 235, 255), ManaLevel.t1)
                .setSet(IS_METAL)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(spiritSilver)
                        .addCurrencyGenerateData(12, 12))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(spiritSilver))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(spiritSilver)
                        .setCanCopy(true));
        crimson = new Ore("crimson", new DuskColor(224, 49, 49), ManaLevel.t1)
                .setSet(IS_METAL)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(crimson)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(crimson))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(crimson)
                        .setCanCopy(true));
        ultramarine = new Ore("ultramarine", new DuskColor(0, 61, 153), ManaLevel.t1)
                .setSet(IS_METAL)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(ultramarine)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(ultramarine))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(ultramarine)
                        .setCanCopy(true));
        indigo = new Ore("indigo", new DuskColor(75, 0, 130), ManaLevel.t1)
                .setSet(IS_METAL)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(indigo)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(indigo))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(indigo)
                        .setCanCopy(true));
        violet = new Ore("violet", new DuskColor(238, 130, 238), ManaLevel.t1)
                .setSet(IS_METAL)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(violet)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(violet))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(violet)
                        .setCanCopy(true));
        thistle = new Ore("thistle", new DuskColor(216, 191, 216), ManaLevel.t1)
                .setSet(IS_METAL)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(thistle)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(thistle))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(thistle)
                        .setCanCopy(true));
        peru = new Ore("peru", new DuskColor(205, 133, 63), ManaLevel.t1)
                .setSet(IS_METAL)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(peru)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(peru))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(peru)
                        .setCanCopy(true));
        goldenrod = new Ore("goldenrod", new DuskColor(218, 165, 32), ManaLevel.t1)
                .setSet(IS_METAL)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(goldenrod)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(goldenrod))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(goldenrod)
                        .setCanCopy(true));
        greenTeal = new Ore("green_teal", new DuskColor(0, 128, 128), ManaLevel.t1)
                .setSet(IS_METAL)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(greenTeal)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(greenTeal))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(greenTeal)
                        .setCanCopy(true));
        mediumspringgreen = new Ore("mediumspringgreen", new DuskColor(0, 250, 154), ManaLevel.t1)
                .setSet(IS_METAL)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(mediumspringgreen)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(mediumspringgreen))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(mediumspringgreen)
                        .setCanCopy(true));
        cornflowerblue = new Ore("cornflowerblue", new DuskColor(100, 149, 237), ManaLevel.t1)
                .setSet(IS_METAL)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(cornflowerblue)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(cornflowerblue))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(cornflowerblue)
                        .setCanCopy(true));
        aliceblue = new Ore("aliceblue", new DuskColor(240, 248, 255), ManaLevel.t1)
                .setSet(IS_METAL)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(aliceblue)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(aliceblue))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(aliceblue)
                        .setCanCopy(true));
        darkGreen = new Ore("dark_green", new DuskColor(61, 59, 79), ManaLevel.t1)
                .setSet(IS_METAL)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(darkGreen)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(darkGreen))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(darkGreen)
                        .setCanCopy(true));
        _void = new Ore("void", new DuskColor(35, 35, 35), ManaLevel.t1)
                .setSet(IS_METAL)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(_void)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(_void))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(_void));

        starRiver = new Ore("star_river", new DuskColor(100, 135, 255), ManaLevel.t2)
                .setSet(IS_METAL)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(starRiver))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(starRiver))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(starRiver)
                        .setCanCopy(true))
                .setSet(ARMOR_DATA, () -> new OreItem.ArmorData(() -> starRiver)
                        .setDefense(3)
                        .setDurability(10)
                        .setMane(128000, 1024)
                        .setDefaultSkill(() -> List.of(Skill.life)))
                .setSet(ARMS_DATA, () -> new OreItem.ArmsData(() -> starRiver)
                        .setMane(128000, 1024));

        sunlight = new Ore("sunlight", ColorPrefab.SUNLIGHT_COLOR, ManaLevel.t1)
                .setSet(IS_CRYSTA)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(sunlight)
                        .addCurrencyGenerateData(12, 12))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(sunlight))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(sunlight)
                        .setCanCopy(true));
        moonlight = new Ore("moonlight", ColorPrefab.MOONLIGHT_COLOR, ManaLevel.t1)
                .setSet(IS_CRYSTA)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(moonlight)
                        .addCurrencyGenerateData(12, 12))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(moonlight))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(moonlight)
                        .setCanCopy(true));
        rain = new Ore("rain", ColorPrefab.RAIN_COLOR, ManaLevel.t1)
                .setSet(IS_CRYSTA)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(rain)
                        .addCurrencyGenerateData(12, 12))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(rain))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(rain)
                        .setCanCopy(true));
        willowYellow = new Ore("willow_yellow", new DuskColor(201, 221, 34), ManaLevel.t1)
                .setSet(IS_CRYSTA)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(willowYellow)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(willowYellow))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(willowYellow)
                        .setCanCopy(true));
        cinnabar = new Ore("cinnabar", new DuskColor(255, 70, 31), ManaLevel.t1)
                .setSet(IS_CRYSTA)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(cinnabar)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(cinnabar))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(cinnabar)
                        .setCanCopy(true));
        pink = new Ore("pink", new DuskColor(244, 121, 131), ManaLevel.t1)
                .setSet(IS_CRYSTA)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(pink)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(pink))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(pink)
                        .setCanCopy(true));
        tibetanBlue = new Ore("tibetan_blue", new DuskColor(59, 46, 126), ManaLevel.t1)
                .setSet(IS_CRYSTA)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(tibetanBlue)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(tibetanBlue))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(tibetanBlue)
                        .setCanCopy(true));
        pineCypress = new Ore("pine_cypress", new DuskColor(5, 119, 72), ManaLevel.t1)
                .setSet(IS_CRYSTA)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(pineCypress)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(pineCypress))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(pineCypress)
                        .setCanCopy(true));
        cotinusCoggygria = new Ore("cotinus_coggygria", new DuskColor(226, 156, 69), ManaLevel.t1)
                .setSet(IS_CRYSTA)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(cotinusCoggygria)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(cotinusCoggygria))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(cotinusCoggygria)
                        .setCanCopy(true));
        clove = new Ore("clove", new DuskColor(204, 164, 227), ManaLevel.t1)
                .setSet(IS_CRYSTA)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(clove)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(clove))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(clove)
                        .setCanCopy(true));
        lotusRoot = new Ore("lotus_root", new DuskColor(228, 198, 208), ManaLevel.t1)
                .setSet(IS_CRYSTA)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(lotusRoot)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(lotusRoot))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(lotusRoot)
                        .setCanCopy(true));
        crow = new Ore("crow", new DuskColor(114, 94, 130), ManaLevel.t1)
                .setSet(IS_CRYSTA)
                .setSet(HAS_DUST)
                .setSet(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t1)
                .setSet(MINERAL_BLOCK_DATA, () -> new OreBlock.MineralBlockData(crow)
                        .addCurrencyGenerateData(12, 4))
                .setSet(DECORATE_BLOCK_DATA, () -> new OreBlock.DecorateBlockData(crow))
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(crow));

        mana = new Ore("mana", ColorPrefab.MANA_IO, ManaLevel.t1)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(mana));
        nutrient = new Ore("nutrient", new DuskColor(245, 190, 110), ManaLevel.t1)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(nutrient));
        natureAir = new Ore("nature_air", new DuskColor(255, 153, 149), ManaLevel.t2)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(natureAir));
        sourceAir = new Ore("source_air", new DuskColor(240, 66, 243), ManaLevel.t2)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(sourceAir));
        elementAir = new Ore("element_air", new DuskColor(114, 211, 175), ManaLevel.t2)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(elementAir));
        nature = new Ore("nature", new DuskColor(237, 133, 129), ManaLevel.t2)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(nature)
                        .setSplitting(new OreFluid.FluidData.SplittingData()
                                .setMoonlightSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(natureAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))
                                .setSunlightSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(natureAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))
                                .setRainSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(natureAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))));
        source = new Ore("source", new DuskColor(220, 46, 223), ManaLevel.t2)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(source)
                        .setSplitting(new OreFluid.FluidData.SplittingData()
                                .setMoonlightSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(sourceAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))
                                .setSunlightSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(sourceAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))
                                .setRainSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(sourceAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))));
        element = new Ore("element", new DuskColor(94, 191, 155), ManaLevel.t2)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(element)
                        .setSplitting(new OreFluid.FluidData.SplittingData()
                                .setMoonlightSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(elementAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))
                                .setSunlightSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(elementAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))
                                .setRainSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(elementAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))));

        highEnergyRedStone = new Ore("high_energy_red_stone", new DuskColor(245, 35, 35), ManaLevel.t1)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(highEnergyRedStone))
                .addShaped(() -> new ShapedOre(ShapedType.highPressureFuse, ShapedDrive.get(0), highEnergyRedStone.manaLevel)
                        .addInItem(Tags.Items.DUSTS_REDSTONE, 1)
                        .addInItem(Tags.Items.DUSTS_GLOWSTONE, 1)
                        .addInFluid(crimson.fluidMap.get(OreFluid.solution).fluidTag(), 144)
                        .addOutFluid(new FluidStack(highEnergyRedStone.fluidMap.get(OreFluid.solution).source(), 144), 1d)
                        .addMultipleSurplusTime((long) (1024L * highEnergyRedStone.strength))
                        .addMultipleConsumeMana((long) (22L * highEnergyRedStone.consume)));
        coolant = new Ore("coolant", new DuskColor(69, 173, 206), ManaLevel.t2)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(coolant))
                .addShaped(() -> new ShapedOre(ShapedType.highPressureFuse, ShapedDrive.get(0), coolant.manaLevel)
                        .addInItem(spiritSilver.itemMap.get(OreItem.dust).itemTag(), 1)
                        .addInFluid(ultramarine.fluidMap.get(OreFluid.solution).fluidTag(), 144)
                        .addOutFluid(new FluidStack(coolant.fluidMap.get(OreFluid.solution).source(), 144), 1d)
                        .addMultipleSurplusTime((long) (2046L * coolant.strength))
                        .addMultipleConsumeMana((long) (18L * coolant.consume)));
        dissolutionMana = new Ore("dissolution_mana", new DuskColor(242, 225, 149), ManaLevel.t3)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(dissolutionMana))
                .addShaped(() -> new ShapedOre(ShapedType.highPressureFuse, ShapedDrive.get(0), dissolutionMana.manaLevel)
                        .addInItem(spiritSilver.itemMap.get(OreItem.dust).itemTag(), 1)
                        .addInItem(indigo.itemMap.get(OreItem.dust).itemTag(), 1)
                        .addInItem(willowYellow.itemMap.get(OreItem.dust).itemTag(), 1)
                        .addInFluid(mana.fluidMap.get(OreFluid.solution).fluidTag(), 32)
                        .addOutFluid(new FluidStack(dissolutionMana.fluidMap.get(OreFluid.solution).source(), 32), 1d)
                        .addMultipleSurplusTime((long) (4096L * dissolutionMana.strength))
                        .addMultipleConsumeMana((long) (18L * dissolutionMana.consume)));
        culture = new Ore("culture", new DuskColor(199, 107, 87), ManaLevel.t4)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(culture))
                .addShaped(() -> new ShapedOre(ShapedType.highPressureFuse, ShapedDrive.get(0), culture.manaLevel)
                        .addInFluid(nutrient.fluidMap.get(OreFluid.solution).fluidTag(), 1024)
                        .addInItem(ItemTag.SUGAR, 5)
                        .addInItem(clove.itemMap.get(OreItem.dust).itemTag(), 5)
                        .addInItem(lotusRoot.itemMap.get(OreItem.dust).itemTag(), 5)
                        .addOutFluid(new FluidStack(culture.fluidMap.get(OreFluid.solution).source(), 128), 1D)
                        .addMultipleSurplusTime((long) (8192L * culture.strength))
                        .addMultipleConsumeMana((long) (18L * culture.consume)));
        stemCell = new Ore("stem_cell", new DuskColor(209, 149, 182), ManaLevel.t4)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(stemCell))
                .addShaped(() -> new ShapedOre(ShapedType.stemCellExtract, ShapedDrive.get(0), stemCell.manaLevel)
                        .addInItem(ItemTag.CAN_EXTRACT_STEM_CELL, 1)
                        .addOutFluid(new FluidStack(stemCell.fluidMap.get(OreFluid.solution).source(), 1), 0.1)
                        .addMultipleSurplusTime((long) (512L * stemCell.strength))
                        .addMultipleConsumeMana((long) (128L * stemCell.consume)))
                .addShaped(() -> new ShapedOre(ShapedType.cellCulture, ShapedDrive.get(0), ManaLevel.t4)
                        .addInFluid(stemCell.fluidMap.get(OreFluid.solution).fluidTag(), 1)
                        .addInFluid(culture.fluidMap.get(OreFluid.solution).fluidTag(), 128)
                        .addOutFluid(new FluidStack(stemCell.fluidMap.get(OreFluid.solution).source(), 1), 1d));
        neuronCell = new Ore("neuron_cell", new DuskColor(176, 221, 227), ManaLevel.t4)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(neuronCell));
        supportCell = new Ore("support_cell", new DuskColor(136, 209, 142), ManaLevel.t4)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(supportCell));
        perceptionCell = new Ore("perception_cell", new DuskColor(159, 105, 156), ManaLevel.t4)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(perceptionCell));
        sarconectin = new Ore("sarconectin", new DuskColor(148, 73, 56), ManaLevel.t4)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(sarconectin));
        uu = new Ore("uu", new DuskColor(160, 32, 240), ManaLevel.t4)
                .setSet(FLUID_DATA, () -> new OreFluid.FluidData(uu))
                .addShaped(() -> new ShapedOre(ShapedType.uuGenerate, ShapedDrive.get(0), ManaLevel.t4)
                        .addOutFluid(new FluidStack(uu.fluidMap.get(OreFluid.solution).source(), 1), 1D)
                        .addMultipleSurplusTime((long) (32768L * uu.strength))
                        .addMultipleConsumeMana((long) (12L * uu.strength)));
    }

    /***
     * 矿物的颜色
     */
    public final DuskColor color;

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

    public Ore(String name, DuskColor color, ManaLevel manaLevel) {
        this(new ResourceLocation(Dusk.MOD_ID, name), color, manaLevel);
    }

    public Ore(ResourceLocation name, DuskColor color, ManaLevel manaLevel) {
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

    @Override
    public void registerItem(OreItem oreItem, ItemPack itemPack) {
        super.registerItem(oreItem, itemPack);
        if (hasSet(IS_LEVEL_ACCEPTABLE)) {
            ItemTag.addTag(getSet(IS_LEVEL_ACCEPTABLE).getRelationTagPack(oreItem).itemTagKey(), itemPack.item());
        }
    }

    @Override
    public void registerBlock(OreBlock oreBlock, BlockPack blockPack) {
        super.registerBlock(oreBlock, blockPack);
        if (hasSet(IS_LEVEL_ACCEPTABLE)) {
            ItemTag.addTag(getSet(IS_LEVEL_ACCEPTABLE).getRelationTagPack(oreBlock).itemTagKey(), blockPack.blockItem());
            BlockTag.addTag(getSet(IS_LEVEL_ACCEPTABLE).getRelationTagPack(oreBlock).blockTagKey(), blockPack.block());
        }
    }

    @Override
    public void registerFluid(OreFluid oreFluid, FluidPack fluidPack) {
        super.registerFluid(oreFluid, fluidPack);
        if (hasSet(IS_LEVEL_ACCEPTABLE)) {
            FluidTag.addTag(getSet(IS_LEVEL_ACCEPTABLE).getRelationTagPack(oreFluid).fluidTagKey(), fluidPack.source());
            FluidTag.addTag(getSet(IS_LEVEL_ACCEPTABLE).getRelationTagPack(oreFluid).fluidTagKey(), fluidPack.flowing());
            if (fluidPack.bucketItem() != null) {
                ItemTag.addTag(getSet(IS_LEVEL_ACCEPTABLE).getRelationTagPack(oreFluid).itemTagKey(), fluidPack.bucketItem());
            }
            if (fluidPack.liquidBlock() != null) {
                BlockTag.addTag(getSet(IS_LEVEL_ACCEPTABLE).getRelationTagPack(oreFluid).blockTagKey(), fluidPack.liquidBlock());
            }
        }
    }

    public static List<Ore> screen(GenericMap.IKey<?>... isMetal) {
        List<Ore> oreList = new ArrayList<>();
        for (Ore ore : Ore.ORE.get()) {
            boolean has = true;
            for (GenericMap.IKey<?> iKey : isMetal) {
                if (!ore.hasSet(iKey)) {
                    has = false;
                    break;
                }
            }
            if (has) {
                oreList.add(ore);
            }
        }
        return oreList;
    }

    @Override
    public RegistryPack<Ore, OreItem, OreBlock, OreFluid> getCellRegistry() {
        if (cellRegistry == null) {
            cellRegistry = new RegistryPack<>(OreItem.ORE_ITEM, OreBlock.ORE_BLOCK, OreFluid.ORE_FLUID);
        }
        return cellRegistry;
    }

    public static RegistryPack<Ore, OreItem, OreBlock, OreFluid> cellRegistry;


    /***
     * 洗矿副产物
     */
    public static final GenericMap.IKey<DataPack.OreDataPack> WASH_BYPRODUCT = new GenericMap.IKey.Key<>();

    /***
     * 离心副产物
     */
    public static final GenericMap.IKey<DataPack.OreDataPack> CENTRIFUGE_BYPRODUCT = new GenericMap.IKey.Key<>();

    /***
     * 筛选副产物
     */
    public static final GenericMap.IKey<DataPack.OreDataPack> SCREEN_BYPRODUCT = new GenericMap.IKey.Key<>();

    /***
     * 盔甲数据
     */
    public static final GenericMap.IKey<OreItem.ArmorData> ARMOR_DATA = new GenericMap.IKey.Key<>();

    /***
     * 武器数据
     */
    public static final GenericMap.IKey<OreItem.ArmsData> ARMS_DATA = new GenericMap.IKey.Key<>();

    /***
     * 有矿物方块
     */
    public static final GenericMap.IKey<OreBlock.MineralBlockData> MINERAL_BLOCK_DATA = new GenericMap.IKey.Key<>();

    /***
     * 拥有装饰方块
     */
    public static final GenericMap.IKey<OreBlock.DecorateBlockData> DECORATE_BLOCK_DATA = new GenericMap.IKey.Key<>();

    /***
     * 有流体
     */
    public static final GenericMap.IKey<OreFluid.FluidData> FLUID_DATA = new GenericMap.IKey.Key<>();

    /***
     * 有粉末
     */
    public static final GenericMap.IKey<Void> HAS_DUST = new GenericMap.IKey.Key<>();

    /***
     * 是金属
     */
    public static final GenericMap.IKey<Void> IS_METAL = new GenericMap.IKey.Key<>();

    /***
     * 是晶体
     */
    public static final GenericMap.IKey<Void> IS_CRYSTA = new GenericMap.IKey.Key<>();

    /***
     * 表明该灵压等级可接受物品作为通用输入
     */
    public static final GenericMap.IKey<ManaLevel> IS_LEVEL_ACCEPTABLE = new GenericMap.IKey.Key<>();

}
