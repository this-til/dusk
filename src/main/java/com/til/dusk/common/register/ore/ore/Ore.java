package com.til.dusk.common.register.ore.ore;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.data.tag.FluidTag;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.UnitRegister;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.DecorateBlockData;
import com.til.dusk.common.register.ore.block.MineralBlockData;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.block.OreBlockMineral;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.item.ArmorData;
import com.til.dusk.common.register.ore.item.ArmsData;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.item.ToolData;
import com.til.dusk.common.register.ore.ore.ores.crysta.*;
import com.til.dusk.common.register.ore.ore.ores.fluid.*;
import com.til.dusk.common.register.ore.ore.ores.fluid.ba_gua.*;
import com.til.dusk.common.register.ore.ore.ores.fluid.basics.*;
import com.til.dusk.common.register.ore.ore.ores.crysta.CinnabarOre;
import com.til.dusk.common.register.ore.ore.ores.fluid.cell.*;
import com.til.dusk.common.register.ore.ore.ores.fluid.liang_yi.YangOre;
import com.til.dusk.common.register.ore.ore.ores.fluid.liang_yi.YinOre;
import com.til.dusk.common.register.ore.ore.ores.fluid.si_xiang.SaoYang;
import com.til.dusk.common.register.ore.ore.ores.fluid.si_xiang.SaoYin;
import com.til.dusk.common.register.ore.ore.ores.fluid.si_xiang.TaiYang;
import com.til.dusk.common.register.ore.ore.ores.fluid.si_xiang.TaiYin;
import com.til.dusk.common.register.ore.ore.ores.metal.*;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.pack.BlockPack;
import com.til.dusk.util.pack.FluidPack;
import com.til.dusk.util.pack.ItemPack;
import com.til.dusk.util.pack.RegistryPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;

import javax.annotation.Nullable;
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
    public static VoidGlowOre voidGlow;

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
     * 念空
     */
    public static IdeaAirOre ideaAirOre;

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
     * 念
     */
    public static Ore idea;

    /***
     *乾
     */
    public static Ore qian;
    /***
     *兑
     */
    public static Ore dui;
    /***
     *离
     */
    public static Ore li;
    /***
     *震
     */
    public static Ore zhen;
    /***
     *巽
     */
    public static Ore xun;
    /***
     *坎
     */
    public static Ore kan;
    /***
     *艮
     */
    public static Ore gen;
    /***
     *坤
     */
    public static Ore kun;

    /***
     * 太阳
     */
    public static Ore taiYang;

    /***
     * 少阴
     */
    public static Ore saoYin;

    /***
     * 少阳
     */
    public static Ore saoYang;

    /***
     * 太阴
     */
    public static Ore taiYin;

    /***
     * 阳仪
     */
    public static Ore yang;

    /***
     * 阴仪
     */
    public static Ore yin;


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
    public static DissolutionManaOre dissolutionMana;

    /***
     * 培养液
     */
    public static CultureOre culture;

    /***
     * 干细胞
     */
    public static StemCellOre stemCell;

    /***
     * 神经元细胞
     */
    public static NeuronCellOre neuronCell;

    /***
     * 供养细胞
     */
    public static SupportCellOre supportCell;

    /***
     * 感知细胞
     */
    public static PerceptionCellOre perceptionCell;

    /***
     * 肌联蛋白
     */
    public static SarconectinOre sarconectin;

    /***
     * uu
     */
    public static UUOre uu;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE = RegisterManage.create(Ore.class, new ResourceLocation(Dusk.MOD_ID, "ore"), event);
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
        ideaAirOre = new IdeaAirOre();
        nature = new NatureOre();
        source = new SourceOre();
        element = new ElementOre();
        idea = new IdeaOre();

        qian = new QianOre();
        dui = new DuiOre();
        li = new LiOre();
        zhen = new ZhenOre();
        xun = new XunOre();
        kan = new KanOre();
        gen = new GenOre();
        kun = new KunOre();

        taiYang = new TaiYang();
        saoYin = new SaoYin();
        saoYang = new SaoYang();
        taiYin = new TaiYin();

        yang = new YangOre();
        yin = new YinOre();

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
        if (isLevelAcceptable != null) {
            for (ManaLevel level : isLevelAcceptable) {
                ItemTag.addTag(level.acceptableTagPack.getTagPack(oreItem).itemTagKey(), itemPack.item());
            }
        }
    }

    @Override
    protected void registerBlock(OreBlock oreBlock, BlockPack blockPack) {
        super.registerBlock(oreBlock, blockPack);
        if (isLevelAcceptable != null) {
            for (ManaLevel level : isLevelAcceptable) {
                ItemTag.addTag(level.acceptableTagPack.getTagPack(oreBlock).itemTagKey(), blockPack.blockItem());
                BlockTag.addTag(level.acceptableTagPack.getTagPack(oreBlock).blockTagKey(), blockPack.block());
            }
        }
        if (oreBlock instanceof OreBlockMineral) {
            ItemTag.addTag(tagPackSupplier.getTagPack(OreBlockMineral.MINERAL_NAME).itemTagKey(), blockPack.blockItem());
            BlockTag.addTag(tagPackSupplier.getTagPack(OreBlockMineral.MINERAL_NAME).blockTagKey(), blockPack.block());
        }
    }

    @Override
    protected void registerFluid(OreFluid oreFluid, FluidPack fluidPack) {
        super.registerFluid(oreFluid, fluidPack);
        if (isLevelAcceptable != null) {
            for (ManaLevel level : isLevelAcceptable) {
                FluidTag.addTag(level.acceptableTagPack.getTagPack(oreFluid).fluidTagKey(), fluidPack.source());
                if (fluidPack.bucketItem() != null) {
                    ItemTag.addTag(level.acceptableTagPack.getTagPack(oreFluid).itemTagKey(), fluidPack.bucketItem());
                }
                if (fluidPack.liquidBlock() != null) {
                    BlockTag.addTag(level.acceptableTagPack.getTagPack(oreFluid).blockTagKey(), fluidPack.liquidBlock());
                }
            }
        }

    }

    @Override
    public void registerShaped(Consumer<Shaped> shapedConsumer) {
        if (relevantShaped != null) {
            for (Shaped shaped : relevantShaped.get()) {
                shapedConsumer.accept(shaped);
            }
        }
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
     * 颜色
     */
    @ConfigField
    public DuskColor color;

    /***
     * 矿物处理的等级
     */
    @ConfigField
    public ManaLevel manaLevel;

    /***
     * 强度系数，对应方块的采集时间和防爆，和加工时间倍数
     */
    @ConfigField
    public double strength = 1;

    /***
     * 加工消耗灵气倍数
     */
    @ConfigField
    public double consume = 1;

    /***
     * 有粉末
     */
    @ConfigField
    public boolean hasDust;

    /***
     * 是金属
     */
    @ConfigField
    public boolean isMetal;

    /***
     * 是晶体
     */
    @ConfigField
    public boolean isCrysta;

    /***
     * 默认一单位所含灵气数量
     * 这个值作用在前期灵气提取晶体产生灵气
     */
    @ConfigField
    public long defaultMana;

    /***
     * 表明该灵压等级可接受物品作为通用输入
     */
    @ConfigField
    public List<ManaLevel> isLevelAcceptable;

    @Nullable
    @ConfigField
    public Delayed<List<Shaped>> relevantShaped;

    @Nullable
    @ConfigField
    public ArmorData armorData;

    @Nullable
    @ConfigField
    public ArmsData armsData;

    @Nullable
    @ConfigField
    public ToolData toolData;

    @Nullable
    @ConfigField
    public FluidData fluidData;

    @Nullable
    @ConfigField
    public DecorateBlockData decorateBlockData;

    @Nullable
    @ConfigField
    public MineralBlockData mineralBlockData;
}
