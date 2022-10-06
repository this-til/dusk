package com.til.dusk.common.register.ore.ore;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigKey;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.data.tag.FluidTag;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.UnitRegister;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.ores.*;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.util.pack.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
public abstract class Ore extends UnitRegister<Ore, OreItem, OreBlock, OreFluid> {

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
    public static EnrichmentCrystalOre enrichmentCrystal;

    /***
     * 猫
     */
    public static CatOre cat;

    /***
     * 狗
     */
    public static DogOre dog;

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
    public static SourceAirOre sourceAir;

    /***
     * 素空
     */
    public static ElementAirOre elementAir;

    /***
     * 质
     */
    public static NatureOre nature;

    /***
     * 源
     */
    public static SourceOre source;

    /***
     * 素
     */
    public static ElementOre element;


    /***
     * 高能红石
     */
    public static HighEnergyRedStoneOre highEnergyRedStone;

    /***
     * 冷却液
     */
    public static CoolantOre coolant;

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
        enrichmentCrystal = new EnrichmentCrystalOre();
        cat = new CatOre();
        dog = new DogOre();
        voidGlow = new VoidGlowOre();

        mana = new ManaOre();
        nutrient = new NutrientOre();
        natureAir = new NatureAirOre();
        sourceAir = new SourceAirOre();
        elementAir = new ElementAirOre();
        nature = new NatureOre();
        source = new SourceOre();
        element = new ElementOre();

        highEnergyRedStone = new HighEnergyRedStoneOre();
        coolant = new CoolantOre();
        dissolutionMana = new DissolutionManaOre();
        culture = new CultureOre();
        stemCell = new StemCellOre();
        neuronCell = new NeuronCellOre();
        supportCell = new SupportCellOre();
        perceptionCell = new PerceptionCellOre();
        sarconectin = new SarconectinOre();
        uu = new UUOre();
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
        if (hasConfig(OreConfig.IS_LEVEL_ACCEPTABLE)) {
            ItemTag.addTag(getConfig(OreConfig.IS_LEVEL_ACCEPTABLE).getAcceptableTagPack(oreItem).itemTagKey(), itemPack.item());
        }
    }

    @Override
    protected void registerBlock(OreBlock oreBlock, BlockPack blockPack) {
        super.registerBlock(oreBlock, blockPack);
        if (hasConfig(OreConfig.IS_LEVEL_ACCEPTABLE)) {
            ItemTag.addTag(getConfig(OreConfig.IS_LEVEL_ACCEPTABLE).getAcceptableTagPack(oreBlock).itemTagKey(), blockPack.blockItem());
            BlockTag.addTag(getConfig(OreConfig.IS_LEVEL_ACCEPTABLE).getAcceptableTagPack(oreBlock).blockTagKey(), blockPack.block());
        }
        if (oreBlock.hasConfig(OreBlock.IS_MINERAL)) {
            ItemTag.addTag(getMineralBlockTag().itemTagKey(), blockPack.blockItem());
            BlockTag.addTag(getMineralBlockTag().blockTagKey(), blockPack.block());
        }
    }

    @Override
    protected void registerFluid(OreFluid oreFluid, FluidPack fluidPack) {
        super.registerFluid(oreFluid, fluidPack);
        if (hasConfig(OreConfig.IS_LEVEL_ACCEPTABLE)) {
            FluidTag.addTag(getConfig(OreConfig.IS_LEVEL_ACCEPTABLE).getAcceptableTagPack(oreFluid).fluidTagKey(), fluidPack.source());
            FluidTag.addTag(getConfig(OreConfig.IS_LEVEL_ACCEPTABLE).getAcceptableTagPack(oreFluid).fluidTagKey(), fluidPack.flowing());
            if (fluidPack.bucketItem() != null) {
                ItemTag.addTag(getConfig(OreConfig.IS_LEVEL_ACCEPTABLE).getAcceptableTagPack(oreFluid).itemTagKey(), fluidPack.bucketItem());
            }
            if (fluidPack.liquidBlock() != null) {
                BlockTag.addTag(getConfig(OreConfig.IS_LEVEL_ACCEPTABLE).getAcceptableTagPack(oreFluid).blockTagKey(), fluidPack.liquidBlock());
            }
        }
    }

    protected TagPack mineralBlockTag;

    public TagPack getMineralBlockTag() {
        if (mineralBlockTag == null) {
            mineralBlockTag = new TagPack(
                    Dusk.instance.ITEM_TAG.createTagKey(fuseName(, "/", new String[]{name.getPath(), "mineral"})),
                    Dusk.instance.BLOCK_TAG.createTagKey(fuseName(, "/", new String[]{name.getPath(), "mineral"})),
                    Dusk.instance.FLUID_TAG.createTagKey(fuseName(, "/", new String[]{name.getPath(), "mineral"}))
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

    @Override
    public RegistryPack<Ore, OreItem, OreBlock, OreFluid> getCellRegistry() {
        if (cellRegistry == null) {
            cellRegistry = new RegistryPack<>(OreItem.ORE_ITEM, OreBlock.ORE_BLOCK, OreFluid.ORE_FLUID);
        }
        return cellRegistry;
    }

    public static RegistryPack<Ore, OreItem, OreBlock, OreFluid> cellRegistry;

}
