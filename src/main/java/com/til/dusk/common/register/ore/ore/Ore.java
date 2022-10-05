package com.til.dusk.common.register.ore.ore;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigKey;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.data.tag.FluidTag;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.UnitRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.block.DecorateBlockData;
import com.til.dusk.common.register.ore.block.MineralBlockData;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.item.ArmorData;
import com.til.dusk.common.register.ore.item.ArmsData;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.item.ToolData;
import com.til.dusk.common.register.ore.ore.ores.*;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.common.register.skill.Skill;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.GenericMap;
import com.til.dusk.util.pack.*;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Ore extends UnitRegister<Ore, OreItem, OreBlock, OreFluid> {

    public static Supplier<IForgeRegistry<Ore>> ORE;

    /***
     * 灵银
     */
    public static SpiritSilverOre spiritSilver;

    /***
     * 绯红
     */
    public static CrimsonOre crimson;

    /***
     * 群青
     */
    public static UltramarineOre ultramarine;

    /***
     * 靛青
     */
    public static IndigoOre indigo;

    /***
     * 紫罗兰
     */
    public static VioletOre violet;

    /***
     * 蓟
     */
    public static ThistleOre thistle;

    /***
     * 秘鲁
     */
    public static PeruOre peru;

    /***
     * 金麒麟
     */
    public static GoldenrodOre goldenrod;

    /***
     * 水鸭
     */
    public static GreenTealOre greenTeal;

    /***
     * 间春绿
     */
    public static MediumspringgreenOre mediumspringgreen;

    /***
     * 菊兰
     */
    public static CornflowerblueOre cornflowerblue;

    /***
     * 艾利斯兰
     */
    public static AliceblueOre aliceblue;

    /***
     * 玄青
     */
    public static DarkGreenOre darkGreen;

    /***
     * 虚空
     */
    public static VoidOre _void;

    /***
     * 秘银
     */
    public static MithrilOre mithril;

    /***
     * 星云银
     */
    public static StarSilver starSilver;

    /***
     * 星云铁
     */
    public static StarIronOre starIron;

    /***
     * 星云金
     */
    public static StarGoldOre starGold;

    /***
     * 星河合金
     */
    public static StarRiverOre starRiver;

    /***
     * 日耀
     */
    public static SunlightOre sunlight;

    /***
     * 月耀
     */
    public static MoonlightOre moonlight;

    /***
     * 雨灵
     */
    public static RainOre rain;

    /***
     * 柳黄
     */
    public static WillowYellowOre willowYellow;

    /***
     * 朱砂
     */
    public static CinnabarOre cinnabar;

    /***
     * 桃红
     */
    public static PinkOre pink;

    /***
     * 藏蓝
     */
    public static TibetanBlueOre tibetanBlue;

    /***
     * 松柏绿
     */
    public static PineCypressOre pineCypress;

    /***
     * 黄栌
     */
    public static CotinusCoggygriaOre cotinusCoggygria;

    /***
     * 丁香
     */
    public static CloveOre clove;

    /***
     * 藕荷
     */
    public static Ore lotusRoot;

    /***
     * 乌
     */
    public static CrowOre crow;

    /***
     * 晶体矿
     */
    public static CrystalOre crystal;

    /***
     * 富集晶体矿
     */
    public static Ore enrichmentCrystal;

    /***
     * 猫
     */
    public static Ore cat;

    /***
     * 狗
     */
    public static Ore dog;

    /***
     * 虚空辉光
     */
    public static Ore voidGlow;

    //other

    //fluid

    /***
     * 实体灵气
     */
    public static ManaOre mana;

    /***
     * 营养液
     */
    public static NutrientOre nutrient;

    /***
     * 质空
     */
    public static NatureAirOre natureAir;

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
        spiritSilver = new SpiritSilverOre();
        crimson = new CrimsonOre();
        ultramarine = new UltramarineOre();
        indigo = new IndigoOre();
        violet = new VioletOre();
        thistle = new ThistleOre();
        peru = new PeruOre();
        goldenrod = new GoldenrodOre();
        greenTeal = new GreenTealOre();
        mediumspringgreen = new MediumspringgreenOre();
        cornflowerblue = new CornflowerblueOre();
        aliceblue = new AliceblueOre();
        darkGreen = new DarkGreenOre();
        _void = new VoidOre();

        mithril = new MithrilOre();

        starSilver = new StarSilver();
        starIron = new StarIronOre();
        starGold = new StarGoldOre();

        starRiver = new StarRiverOre();

        sunlight = new SunlightOre();
        moonlight = new MoonlightOre();
        rain = new RainOre();
        willowYellow = new WillowYellowOre();
        cinnabar = new CinnabarOre();
        pink = new PinkOre();
        tibetanBlue = new TibetanBlueOre();
        pineCypress = new PineCypressOre();
        cotinusCoggygria = new CotinusCoggygriaOre();
        clove = new CloveOre();
        lotusRoot = new LotusRootOre();
        crow = new CrowOre();
        crystal = new CrystalOre();
        enrichmentCrystal = new Ore("enrichment_crystal", new DuskColor(235, 225, 125), ManaLevel.t1)
                .setConfig(IS_CRYSTA)
                .setConfig(HAS_DUST)
                .setConfig(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t2)
                .setConfig(DECORATE_BLOCK_DATA, () -> new DecorateBlockData(crystal))
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(crystal))
                .addShaped(() -> new ShapedOre(ShapedType.blend, ShapedDrive.get(0), this.getConfig(OreConfig.MANA_LEVEL))
                        .addInItem(crystal.get(OreItem.dust).itemTag(), 1)
                        .addInItem(goldenrod.get(OreItem.dust).itemTag(), 1)
                        .addInItem(cotinusCoggygria.get(OreItem.dust).itemTag(), 1)
                        .addOutItem(new ItemStack(this.get(OreItem.dust).item(), 3), 1d)
                        .addMultipleSurplusTime((long) (751L * this.getConfig(OreConfig.STRENGTH)))
                        .addMultipleConsumeMana((long) (15L * this.getConfig(OreConfig.CONSUME)));
        cat = new Ore("cat", new DuskColor(239, 218, 217), ManaLevel.t2)
                .setConfig(IS_CRYSTA)
                .setConfig(HAS_DUST)
                .setConfig(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t2)
                .setConfig(DECORATE_BLOCK_DATA, () -> new DecorateBlockData(cat))
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(cat))
                .addShaped(() -> new ShapedOre(ShapedType.highPressureFuse, ShapedDrive.get(1), this.getConfig(OreConfig.MANA_LEVEL))
                        .addInFluid(crystal.get(OreFluid.solution).fluidTag(), 72)
                        .addInFluid(cotinusCoggygria.get(OreFluid.solution).fluidTag(), 72)
                        .addInFluid(sourceAir.get(OreFluid.solution).fluidTag(), 440)
                        .addOutFluid(new FluidStack(this.get(OreFluid.solution).source(), 144), 1D)
                        .addMultipleSurplusTime((long) (855L * this.getConfig(OreConfig.STRENGTH)))
                        .addMultipleConsumeMana((long) (17L * this.getConfig(OreConfig.CONSUME)));
        dog = new Ore("dog", new DuskColor(235, 225, 125), ManaLevel.t2)
                .setConfig(IS_CRYSTA)
                .setConfig(HAS_DUST)
                .setConfig(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t2)
                .setConfig(DECORATE_BLOCK_DATA, () -> new DecorateBlockData(dog))
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(dog))
                .addShaped(() -> new ShapedOre(ShapedType.highPressureFuse, ShapedDrive.get(1), this.getConfig(OreConfig.MANA_LEVEL))
                        .addInFluid(crystal.fluidMap.get(OreFluid.solution).fluidTag(), 72)
                        .addInFluid(tibetanBlue.fluidMap.get(OreFluid.solution).fluidTag(), 72)
                        .addInFluid(sourceAir.fluidMap.get(OreFluid.solution).fluidTag(), 440)
                        .addOutFluid(new FluidStack(dog.fluidMap.get(OreFluid.solution).source(), 144), 1D)
                        .addMultipleSurplusTime((long) (855L * this.getConfig(OreConfig.STRENGTH)))
                        .addMultipleConsumeMana((long) (17L * this.getConfig(OreConfig.CONSUME)));
        voidGlow = new Ore("void_glow", new DuskColor(240, 239, 238), ManaLevel.t2)
                .setConfig(IS_CRYSTA)
                .setConfig(HAS_DUST)
                .setConfig(IS_LEVEL_ACCEPTABLE, () -> ManaLevel.t3)
                .setConfig(DECORATE_BLOCK_DATA, () -> new DecorateBlockData(voidGlow))
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(voidGlow))
                .addShaped(() -> new ShapedOre(ShapedType.highPressureFuse, ShapedDrive.get(1), this.getConfig(OreConfig.MANA_LEVEL))
                        .addInFluid(cat.get(OreFluid.solution).fluidTag(), 72)
                        .addInFluid(dog.get(OreFluid.solution).fluidTag(), 72)
                        .addInFluid(natureAir.get(OreFluid.solution).fluidTag(), 1200)
                        .addOutFluid(new FluidStack(this.fluidMap.get(OreFluid.solution).source(), 144), 1D)
                        .addMultipleSurplusTime((long) (1455L * this.getConfig(OreConfig.STRENGTH)))
                        .addMultipleConsumeMana((long) (17L * this.getConfig(OreConfig.CONSUME)));

        mana = new ManaOre();
        nutrient = new NutrientOre();
        natureAir = new NatureAirOre();
        sourceAir = new Ore("source_air", new DuskColor(240, 66, 243), ManaLevel.t2)
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(sourceAir));
        elementAir = new Ore("element_air", new DuskColor(114, 211, 175), ManaLevel.t2)
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(elementAir));
        nature = new Ore("nature", new DuskColor(237, 133, 129), ManaLevel.t2)
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(nature)
                        .setSplitting(new OreFluid.FluidData.SplittingData()
                                .setMoonlightSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(natureAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))
                                .setSunlightSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(natureAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))
                                .setRainSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(natureAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))));
        source = new Ore("source", new DuskColor(220, 46, 223), ManaLevel.t2)
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(source)
                        .setSplitting(new OreFluid.FluidData.SplittingData()
                                .setMoonlightSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(sourceAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))
                                .setSunlightSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(sourceAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))
                                .setRainSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(sourceAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))));
        element = new Ore("element", new DuskColor(94, 191, 155), ManaLevel.t2)
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(element)
                        .setSplitting(new OreFluid.FluidData.SplittingData()
                                .setMoonlightSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(elementAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))
                                .setSunlightSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(elementAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))
                                .setRainSplitting(new OreFluid.FluidData.SplittingDataPack()
                                        .addOutFluid(new FluidStack(elementAir.fluidMap.get(OreFluid.solution).source(), 72), 1d))));

        highEnergyRedStone = new Ore("high_energy_red_stone", new DuskColor(245, 35, 35), ManaLevel.t1)
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(highEnergyRedStone))
                .addShaped(() -> new ShapedOre(ShapedType.highPressureFuse, ShapedDrive.get(0), this.getConfig(OreConfig.MANA_LEVEL))
                        .addInItem(Tags.Items.DUSTS_REDSTONE, 1)
                        .addInItem(Tags.Items.DUSTS_GLOWSTONE, 1)
                        .addInFluid(crimson.fluidMap.get(OreFluid.solution).fluidTag(), 144)
                        .addOutFluid(new FluidStack(highEnergyRedStone.fluidMap.get(OreFluid.solution).source(), 144), 1d)
                        .addMultipleSurplusTime((long) (1024L * this.getConfig(OreConfig.STRENGTH)))
                        .addMultipleConsumeMana((long) (22L * this.getConfig(OreConfig.CONSUME))));
        coolant = new Ore("coolant", new DuskColor(69, 173, 206), ManaLevel.t2)
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(coolant))
                .addShaped(() -> new ShapedOre(ShapedType.highPressureFuse, ShapedDrive.get(0), this.getConfig(OreConfig.MANA_LEVEL))
                        .addInItem(spiritSilver.itemMap.get(OreItem.dust).itemTag(), 1)
                        .addInFluid(ultramarine.fluidMap.get(OreFluid.solution).fluidTag(), 144)
                        .addOutFluid(new FluidStack(coolant.fluidMap.get(OreFluid.solution).source(), 144), 1d)
                        .addMultipleSurplusTime((long) (2046L * coolant.strength))
                        .addMultipleConsumeMana((long) (18L * coolant.consume)));
        dissolutionMana = new Ore("dissolution_mana", new DuskColor(242, 225, 149), ManaLevel.t3)
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(dissolutionMana))
                .addShaped(() -> new ShapedOre(ShapedType.highPressureFuse, ShapedDrive.get(0), this.getConfig(OreConfig.MANA_LEVEL))
                        .addInItem(spiritSilver.itemMap.get(OreItem.dust).itemTag(), 1)
                        .addInItem(indigo.itemMap.get(OreItem.dust).itemTag(), 1)
                        .addInItem(willowYellow.itemMap.get(OreItem.dust).itemTag(), 1)
                        .addInFluid(mana.fluidMap.get(OreFluid.solution).fluidTag(), 32)
                        .addOutFluid(new FluidStack(dissolutionMana.fluidMap.get(OreFluid.solution).source(), 32), 1d)
                        .addMultipleSurplusTime((long) (4096L * this.getConfig(OreConfig.STRENGTH)))
                        .addMultipleConsumeMana((long) (18L * this.getConfig(OreConfig.CONSUME)));
        culture = new Ore("culture", new DuskColor(199, 107, 87), ManaLevel.t4)
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(culture))
                .addShaped(() -> new ShapedOre(ShapedType.highPressureFuse, ShapedDrive.get(0), this.getConfig(OreConfig.MANA_LEVEL))
                        .addInFluid(nutrient.fluidMap.get(OreFluid.solution).fluidTag(), 1024)
                        .addInItem(ItemTag.SUGAR, 5)
                        .addInItem(clove.itemMap.get(OreItem.dust).itemTag(), 5)
                        .addInItem(lotusRoot.itemMap.get(OreItem.dust).itemTag(), 5)
                        .addOutFluid(new FluidStack(culture.fluidMap.get(OreFluid.solution).source(), 128), 1D)
                        .addMultipleSurplusTime((long) (8192L * this.getConfig(OreConfig.STRENGTH)))
                        .addMultipleConsumeMana((long) (18L * this.getConfig(OreConfig.CONSUME))));
        stemCell = new Ore("stem_cell", new DuskColor(209, 149, 182), ManaLevel.t4)
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(stemCell))
                .addShaped(() -> new ShapedOre(ShapedType.stemCellExtract, ShapedDrive.get(0), this.getConfig(OreConfig.MANA_LEVEL))
                        .addInItem(ItemTag.CAN_EXTRACT_STEM_CELL, 1)
                        .addOutFluid(new FluidStack(stemCell.fluidMap.get(OreFluid.solution).source(), 1), 0.1)
                        .addMultipleSurplusTime((long) (512L * this.getConfig(OreConfig.STRENGTH)))
                        .addMultipleConsumeMana((long) (128L * this.getConfig(OreConfig.CONSUME))))
                .addShaped(() -> new ShapedOre(ShapedType.cellCulture, ShapedDrive.get(0), ManaLevel.t4)
                        .addInFluid(stemCell.fluidMap.get(OreFluid.solution).fluidTag(), 1)
                        .addInFluid(culture.fluidMap.get(OreFluid.solution).fluidTag(), 128)
                        .addOutFluid(new FluidStack(stemCell.fluidMap.get(OreFluid.solution).source(), 1), 1d));
        neuronCell = new Ore("neuron_cell", new DuskColor(176, 221, 227), ManaLevel.t4)
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(neuronCell));
        supportCell = new Ore("support_cell", new DuskColor(136, 209, 142), ManaLevel.t4)
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(supportCell));
        perceptionCell = new Ore("perception_cell", new DuskColor(159, 105, 156), ManaLevel.t4)
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(perceptionCell));
        sarconectin = new Ore("sarconectin", new DuskColor(148, 73, 56), ManaLevel.t4)
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(sarconectin));
        uu = new Ore("uu", new DuskColor(160, 32, 240), ManaLevel.t4)
                .setConfig(FLUID_DATA, () -> new OreFluid.FluidData(uu))
                .addShaped(() -> new ShapedOre(ShapedType.uuGenerate, ShapedDrive.get(0), this.getConfig(OreConfig.MANA_LEVEL))
                        .addOutFluid(new FluidStack(uu.fluidMap.get(OreFluid.solution).source(), 1), 1D)
                        .addMultipleSurplusTime((long) (32768L * this.getConfig(OreConfig.STRENGTH)))
                        .addMultipleConsumeMana((long) (12L * this.getConfig(OreConfig.CONSUME))));
    }

    public Ore(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    public Ore(ResourceLocation name) {
        super(name, ORE);
    }


    @Override
    protected void registerItem(OreItem oreItem, ItemPack itemPack) {
        super.registerItem(oreItem, itemPack);
        if (hasSet(IS_LEVEL_ACCEPTABLE)) {
            ItemTag.addTag(getSet(IS_LEVEL_ACCEPTABLE).getAcceptableTagPack(oreItem).itemTagKey(), itemPack.item());
        }
    }

    @Override
    protected void registerBlock(OreBlock oreBlock, BlockPack blockPack) {
        super.registerBlock(oreBlock, blockPack);
        if (hasSet(IS_LEVEL_ACCEPTABLE)) {
            ItemTag.addTag(getSet(IS_LEVEL_ACCEPTABLE).getAcceptableTagPack(oreBlock).itemTagKey(), blockPack.blockItem());
            BlockTag.addTag(getSet(IS_LEVEL_ACCEPTABLE).getAcceptableTagPack(oreBlock).blockTagKey(), blockPack.block());
        }
        if (oreBlock.hasSet(OreBlock.IS_MINERAL)) {
            ItemTag.addTag(getMineralBlockTag().itemTagKey(), blockPack.blockItem());
            BlockTag.addTag(getMineralBlockTag().blockTagKey(), blockPack.block());
        }
    }

    @Override
    protected void registerFluid(OreFluid oreFluid, FluidPack fluidPack) {
        super.registerFluid(oreFluid, fluidPack);
        if (hasSet(IS_LEVEL_ACCEPTABLE)) {
            FluidTag.addTag(getSet(IS_LEVEL_ACCEPTABLE).getAcceptableTagPack(oreFluid).fluidTagKey(), fluidPack.source());
            FluidTag.addTag(getSet(IS_LEVEL_ACCEPTABLE).getAcceptableTagPack(oreFluid).fluidTagKey(), fluidPack.flowing());
            if (fluidPack.bucketItem() != null) {
                ItemTag.addTag(getSet(IS_LEVEL_ACCEPTABLE).getAcceptableTagPack(oreFluid).itemTagKey(), fluidPack.bucketItem());
            }
            if (fluidPack.liquidBlock() != null) {
                BlockTag.addTag(getSet(IS_LEVEL_ACCEPTABLE).getAcceptableTagPack(oreFluid).blockTagKey(), fluidPack.liquidBlock());
            }
        }
    }

    public TagPack getMineralBlockTag() {
        if (mineralBlockTag == null) {
            mineralBlockTag = new TagPack(
                    Dusk.instance.ITEM_TAG.createTagKey(fuseName("/", new String[]{name.getPath(), "mineral"})),
                    Dusk.instance.BLOCK_TAG.createTagKey(fuseName("/", new String[]{name.getPath(), "mineral"})),
                    Dusk.instance.FLUID_TAG.createTagKey(fuseName("/", new String[]{name.getPath(), "mineral"}))
            );
        }
        return mineralBlockTag;
    }

    @Override
    public void registerShaped(Consumer<Shaped> shapedConsumer) {
        if (hasConfig(OreConfig.RELEVANT_SHAPED)) {
            for (Shaped shaped : getConfig(OreConfig.RELEVANT_SHAPED)) {
                shapedConsumer.accept(shaped);
            }
        }
    }

    public static List<Ore> screen(ConfigKey<?>... isMetal) {
        List<Ore> oreList = new ArrayList<>();
        for (Ore ore : Ore.ORE.get()) {
            boolean has = true;
            for (ConfigKey<?> iKey : isMetal) {
                if (!ore.hasConfig(iKey)) {
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

}
