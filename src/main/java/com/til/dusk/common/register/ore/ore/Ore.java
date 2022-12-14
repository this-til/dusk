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
import com.til.dusk.common.register.ore.ore.crysta.*;
import com.til.dusk.common.register.ore.ore.fluid.*;
import com.til.dusk.common.register.ore.ore.fluid.ba_gua.*;
import com.til.dusk.common.register.ore.ore.fluid.basics.*;
import com.til.dusk.common.register.ore.ore.fluid.cell.*;
import com.til.dusk.common.register.ore.ore.metal.*;
import com.til.dusk.common.register.ore.ore.fluid.liang_yi.YangOre;
import com.til.dusk.common.register.ore.ore.fluid.liang_yi.YinOre;
import com.til.dusk.common.register.ore.ore.fluid.si_xiang.SaoYang;
import com.til.dusk.common.register.ore.ore.fluid.si_xiang.SaoYin;
import com.til.dusk.common.register.ore.ore.fluid.si_xiang.TaiYang;
import com.til.dusk.common.register.ore.ore.fluid.si_xiang.TaiYin;
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
     * ??????
     */
    public static SpiritSilverOre spiritSilver;

    /***
     * ??????
     */
    public static CrimsonOre crimson;

    /***
     * ??????
     */
    public static UltramarineOre ultramarine;

    /***
     * ??????
     */
    public static IndigoOre indigo;

    /***
     * ?????????
     */
    public static VioletOre violet;

    /***
     * ???
     */
    public static ThistleOre thistle;

    /***
     * ??????
     */
    public static PeruOre peru;

    /***
     * ?????????
     */
    public static GoldenrodOre goldenrod;

    /***
     * ??????
     */
    public static GreenTealOre greenTeal;

    /***
     * ?????????
     */
    public static MediumspringgreenOre mediumspringgreen;

    /***
     * ??????
     */
    public static CornflowerblueOre cornflowerblue;

    /***
     * ????????????
     */
    public static AliceblueOre aliceblue;

    /***
     * ??????
     */
    public static DarkGreenOre darkGreen;

    /***
     * ??????
     */
    public static VoidOre _void;

    /***
     * ??????
     */
    public static MithrilOre mithril;

    /***
     * ?????????
     */
    public static StarSilver starSilver;

    /***
     * ?????????
     */
    public static StarIronOre starIron;

    /***
     * ?????????
     */
    public static StarGoldOre starGold;
    /***
     * ?????????
     */
    public static ChelseaOre chelsea;

    /***
     * ????????????
     */
    public static StarRiverOre starRiver;

    /***
     * ????????????
     */
    public static BlueDemonConcubineOre blueDemonConcubine;

    /***
     * ????????????
     */
    public static RedStormOre redStorm;

    /***
     * ????????????
     */
    public static MomentFlowerOre momentFlower;

    /***
     * ??????
     */
    public static SunlightOre sunlight;

    /***
     * ??????
     */
    public static MoonlightOre moonlight;

    /***
     * ??????
     */
    public static RainOre rain;

    /***
     * ??????
     */
    public static WillowYellowOre willowYellow;

    /***
     * ??????
     */
    public static CinnabarOre cinnabar;

    /***
     * ??????
     */
    public static PinkOre pink;

    /***
     * ??????
     */
    public static TibetanBlueOre tibetanBlue;

    /***
     * ?????????
     */
    public static PineCypressOre pineCypress;

    /***
     * ??????
     */
    public static CotinusCoggygriaOre cotinusCoggygria;

    /***
     * ??????
     */
    public static CloveOre clove;

    /***
     * ??????
     */
    public static LotusRootOre lotusRoot;

    /***
     * ???
     */
    public static CrowOre crow;

    /***
     * ?????????
     */
    public static CrystalOre crystal;

    /***
     * ???????????????
     */
    public static EnrichmentCrystalOre enrichmentCrystal;

    /***
     * ???
     */
    public static CatOre cat;

    /***
     * ???
     */
    public static DogOre dog;

    /***
     * ????????????
     */
    public static VoidGlowOre voidGlow;

    //other

    //fluid

    /***
     * ????????????
     */
    public static ManaOre mana;

    /***
     * ?????????
     */
    public static NutrientOre nutrient;

    /***
     * ??????
     */
    public static NatureAirOre natureAir;

    /***
     * ??????
     */
    public static SourceAirOre sourceAir;

    /***
     * ??????
     */
    public static ElementAirOre elementAir;

    /***
     * ??????
     */
    public static IdeaAirOre ideaAirOre;

    /***
     * ???
     */
    public static NatureOre nature;
    /***
     * ???
     */
    public static SourceOre source;
    /***
     * ???
     */
    public static ElementOre element;
    /***
     * ???
     */
    public static Ore idea;

    /***
     *???
     */
    public static Ore qian;
    /***
     *???
     */
    public static Ore dui;
    /***
     *???
     */
    public static Ore li;
    /***
     *???
     */
    public static Ore zhen;
    /***
     *???
     */
    public static Ore xun;
    /***
     *???
     */
    public static Ore kan;
    /***
     *???
     */
    public static Ore gen;
    /***
     *???
     */
    public static Ore kun;

    /***
     * ??????
     */
    public static Ore taiYang;

    /***
     * ??????
     */
    public static Ore saoYin;

    /***
     * ??????
     */
    public static Ore saoYang;

    /***
     * ??????
     */
    public static Ore taiYin;

    /***
     * ??????
     */
    public static Ore yang;

    /***
     * ??????
     */
    public static Ore yin;


    /***
     * ????????????
     */
    public static HighEnergyRedStoneOre highEnergyRedStone;

    /***
     * ??????
     */
    public static GasOre gas;

    /***
     * ????????????
     */
    public static HighEnergyGasOre highEnergyGas;

    /***
     * ?????????
     */
    public static CoolantOre coolant;

    /***
     * ??????
     */
    public static OverheatedCoolantOre overheatedCoolantOre;

    /***
     * ????????????
     */
    public static DissolutionManaOre dissolutionMana;

    /***
     * ?????????
     */
    public static CultureOre culture;

    /***
     * ?????????
     */
    public static StemCellOre stemCell;

    /***
     * ???????????????
     */
    public static NeuronCellOre neuronCell;

    /***
     * ????????????
     */
    public static SupportCellOre supportCell;

    /***
     * ????????????
     */
    public static PerceptionCellOre perceptionCell;

    /***
     * ????????????
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
        chelsea = new ChelseaOre();

        starRiver = new StarRiverOre();
        blueDemonConcubine = new BlueDemonConcubineOre();
        redStorm = new RedStormOre();
        momentFlower = new MomentFlowerOre();

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
        gas = new GasOre();
        highEnergyGas = new HighEnergyGasOre();
        coolant = new CoolantOre();
        overheatedCoolantOre = new OverheatedCoolantOre();
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
     * ??????
     */
    @ConfigField
    public DuskColor color;

    /***
     * ?????????????????????
     */
    @ConfigField
    public ManaLevel manaLevel;

    /***
     * ???????????????????????????????????????????????????????????????????????????
     */
    @ConfigField
    public double strength = 1;

    /***
     * ????????????????????????
     */
    @ConfigField
    public double consume = 1;

    /***
     * ?????????
     */
    @ConfigField
    public boolean hasDust;

    /***
     * ?????????
     */
    @ConfigField
    public boolean isMetal;

    /***
     * ?????????
     */
    @ConfigField
    public boolean isCrysta;

    /***
     * ?????????????????????????????????
     * ??????????????????????????????????????????????????????
     */
    @ConfigField
    public long defaultMana;

    /***
     * ??????????????????????????????????????????????????????
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
