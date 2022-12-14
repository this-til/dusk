package com.til.dusk.common.register.mana_level.block;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.register.mana_level.block.mechanic.*;
import com.til.dusk.common.register.mana_level.block.mechanic.core.AccelerateMultiBlockMechanic;
import com.til.dusk.common.register.mana_level.block.mechanic.core.MK1MultiBlockMechanic;
import com.til.dusk.common.register.mana_level.block.mechanic.core.MK2MultiBlockMechanic;
import com.til.dusk.common.register.mana_level.block.mechanic.core.MK3MultiBlockMechanic;
import com.til.dusk.common.register.mana_level.block.mechanic.extract_mana.*;
import com.til.dusk.common.register.mana_level.block.mechanic.extract_mana.passive.MoonlightSimilarSolarEnergyMechanic;
import com.til.dusk.common.register.mana_level.block.mechanic.extract_mana.passive.RainSimilarSolarEnergyMechanic;
import com.til.dusk.common.register.mana_level.block.mechanic.extract_mana.passive.SunlightSimilarSolarEnergyMechanic;
import com.til.dusk.common.register.mana_level.block.mechanic.handle_mechanic.*;
import com.til.dusk.util.gson.AcceptTypeJson;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.BlockUnitRegister;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.mana_level.mana_level.MakeLevel;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class ManaLevelBlock extends BlockUnitRegister<ManaLevelBlock, ManaLevel> {


    public static Supplier<IForgeRegistry<ManaLevelBlock>> LEVEL_BLOCK;

    //??????
    /***
     * ?????????
     * ???????????????????????????
     */
    public static RepeaterMechanic repeater;

    /***
     * ????????????
     */
    public static FrameBasicMechanic frameBasic;

    //??????
    /***
     * ????????????
     * ???????????????
     */
    public static SunlightSimilarSolarEnergyMechanic sunlight;

    /***
     * ????????????
     */
    public static MoonlightSimilarSolarEnergyMechanic moonlight;

    /***
     * ????????????
     */
    public static RainSimilarSolarEnergyMechanic rain;

    /***
     * ??????????????????
     */
    public static ExtractManaHandleMechanic extractMana;

    /***
     * ??????????????????
     */
    public static DischantmentManaMechanic dischantmentMana;

    /***
     *  ??????????????????
     */
    public static EnderManaMechanic enderMana;

    /***
     * ??????????????????
     */
    public static PotionManaMechanic potionMana;

    /***
     * ??????????????????
     */
    public static ExplosiveManaMechanic explosiveMana;

    /***
     * ??????????????????
     */
    public static FrostyManaMechanic frostyMana;

    /***
     * ??????????????????
     */
    public static SlimeyManaMechanic slimeyMana;

    /***
     * ??????????????????
     */
    public static HalitosisManaMechanic halitosisMana;

    /***
     * ??????????????????
     */
    public static FlameManaMechanic flameMana;

    /***
     * ??????????????????
     */
    public static BotanyManaMechanic botanyMana;

    /***
     * ??????????????????
     */
    public static FoodManaMechanic foodMana;

    /***
     * ????????????
     */
    public static ElementManaMechanic elementMana;

    //??????

    /***
     * ??????
     */
    public static GrindMechanic grind;

    /***
     * ??????
     */
    public static WashMechanic wash;

    /***
     * ??????
     */
    public static CentrifugalMechanic centrifugal;

    /***
     * ??????
     */
    public static PackMechanic pack;

    /***
     * ??????
     */
    public static UnpackMechanic unpack;

    /***
     * ??????
     */
    public static BlastFurnaceMechanic blastFurnace;

    /***
     * ??????
     */
    public static CrystallizingMechanic crystallizing;

    /***
     * ????????????
     */
    public static CrystalSeedMakeMechanic crystalSeedMake;

    /***
     * ?????????
     */
    public static AssembleMechanic assemble;

    /***
     * ??????
     */
    public static EncapsulationMechanic encapsulation;

    /***
     * ???????????????
     */
    public static CrystalAssembleMechanic crystalAssemble;

    /***
     * ??????
     */
    public static DistillationMechanic distillation;

    /***
     * ??????
     */
    public static DissolutionMechanic dissolution;

    /***
     * ??????
     */
    public static FreezingMechanic freezing;

    /***
     * ????????????
     */
    public static HighPressureFuseMechanic highPressureFuse;

    /***
     * ??????
     */
    public static CarvingMechanic carving;

    /***
     * ??????
     */
    public static ScreenMechanic screen;

    /***
     * ??????
     */
    public static FurnaceMechanic furnace;

    /***
     * ?????????
     */
    public static MakerStoneMechanic makerStone;

    /***
     * ?????????
     */
    public static StampingMachineMechanic stampingMachine;

    /***
     * ??????
     */
    public static LatheMechanic lathe;

    /***
     * ?????????
     */
    public static TieWireMechanic tieWire;

    /***
     * ??????
     */
    public static CuttingMechanic cutting;

    /***
     * ??????
     */
    public static PressureStickMechanic pressureStick;

    /***
     * ??????
     */
    public static BlendMechanic blend;

    /***
     * ??????
     */
    public static DecomposeMechanic decompose;

    /***
     * ??????
     */
    public static RecoveryMechanic recovery;

    /***
     * ??????
     */
    public static ShapingMechanic shaping;

    /***
     * ??????????????????
     */
    public static ManaCoagulationMechanic manaCoagulation;

    /***
     * ??????????????????
     */
    public static HandleMechanic stemCellExtract;

    /***
     * ??????????????????
     */
    public static HandleMechanic cellCulture;

    /***
     * uu??????
     */
    public static HandleMechanic uuGenerate;

    /***
     * ????????????
     */
    public static HandleMechanic qualityGenerate;

    /***
     * ????????????
     */
    public static HandleMechanic dialysis;

    /***
     * ????????????
     */
    public static HandleMechanic splitting;

    //??????

    /***
     * ?????????
     * 4096 * l
     */
    public static VoidCaseMechanic voidCase;

    /***
     * ?????????
     * 1280000mb * l
     */
    public static VoidTankMechanic voidTank;

    /***
     * ??????
     */
    public static GatherManaMechanic gatherMana;

    /***
     * ????????????
     */
    public static IOMechanic.ManaIO manaIO;

    /***
     * ????????????
     */
    public static IOMechanic.ItemIO itemIO;

    /***
     * ????????????
     */
    public static IOMechanic.FluidIO fluidIO;

    /***
     * ????????????
     */
    public static CollectMechanic collect;

    /***
     * ????????????
     */
    public static WhirlBoostMechanic whirlBoost;

    /***
     * ????????????
     */
    public static ChargeMechanic charge;

    /***
     * ?????????
     */
    public static PumpMechanic pumpMechanic;

    /***
     * ????????????
     */
    public static MiningMechanic mining;

    /***
     * ????????????
     */
    public static MassacreMechanic massacre;

    /***
     * FE????????????
     */
    public static TransformationFEMechanic transformationFEMechanic;

    /***
     * ??????????????????
     */
    public static AccelerateMultiBlockMechanic accelerateMultiBlock;

    public static MK1MultiBlockMechanic mk1;
    public static MK2MultiBlockMechanic mk2;
    public static MK3MultiBlockMechanic mk3;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        LEVEL_BLOCK = RegisterManage.create(ManaLevelBlock.class, new ResourceLocation(Dusk.MOD_ID, "mana_level_block"), event);
        repeater = new RepeaterMechanic();
        frameBasic = new FrameBasicMechanic();
        sunlight = new SunlightSimilarSolarEnergyMechanic();
        moonlight = new MoonlightSimilarSolarEnergyMechanic();
        rain = new RainSimilarSolarEnergyMechanic();
        extractMana = new ExtractManaHandleMechanic();
        dischantmentMana = new DischantmentManaMechanic();
        enderMana = new EnderManaMechanic();
        potionMana = new PotionManaMechanic();
        explosiveMana = new ExplosiveManaMechanic();
        frostyMana = new FrostyManaMechanic();
        slimeyMana = new SlimeyManaMechanic();
        halitosisMana = new HalitosisManaMechanic();
        flameMana = new FlameManaMechanic();
        botanyMana = new BotanyManaMechanic();
        foodMana = new FoodManaMechanic();
        elementMana = new ElementManaMechanic();
        grind = new GrindMechanic();
        wash = new WashMechanic();
        centrifugal = new CentrifugalMechanic();
        pack = new PackMechanic();
        unpack = new UnpackMechanic();
        blastFurnace = new BlastFurnaceMechanic();
        crystallizing = new CrystallizingMechanic();
        crystalSeedMake = new CrystalSeedMakeMechanic();
        assemble = new AssembleMechanic();
        encapsulation = new EncapsulationMechanic();
        crystalAssemble = new CrystalAssembleMechanic();
        distillation = new DistillationMechanic();
        dissolution = new DissolutionMechanic();
        freezing = new FreezingMechanic();
        highPressureFuse = new HighPressureFuseMechanic();
        carving = new CarvingMechanic();
        screen = new ScreenMechanic();
        furnace = new FurnaceMechanic();
        makerStone = new MakerStoneMechanic();
        stampingMachine = new StampingMachineMechanic();
        lathe = new LatheMechanic();
        tieWire = new TieWireMechanic();
        cutting = new CuttingMechanic();
        pressureStick = new PressureStickMechanic();
        blend = new BlendMechanic();
        decompose = new DecomposeMechanic();
        recovery = new RecoveryMechanic();
        shaping = new ShapingMechanic();
        manaCoagulation = new ManaCoagulationMechanic();
        stemCellExtract = new StemCellExtractMechanic();
        cellCulture = new CellCultureMechanic();
        uuGenerate = new UUGenerateMechanic();
        qualityGenerate = new QualityGenerateMechanic();
        dialysis = new DialysisMechanic();
        splitting = new SplittingMechanic();
        voidCase = new VoidCaseMechanic();
        voidTank = new VoidTankMechanic();
        gatherMana = new GatherManaMechanic();
        manaIO = new IOMechanic.ManaIO();
        itemIO = new IOMechanic.ItemIO();
        fluidIO = new IOMechanic.FluidIO();
        collect = new CollectMechanic();
        whirlBoost = new WhirlBoostMechanic();
        charge = new ChargeMechanic();
        pumpMechanic = new PumpMechanic();
        mining = new MiningMechanic();
        massacre = new MassacreMechanic();
        transformationFEMechanic = new TransformationFEMechanic();
        accelerateMultiBlock = new AccelerateMultiBlockMechanic();
        mk1 = new MK1MultiBlockMechanic();
        mk2 = new MK2MultiBlockMechanic();
        mk3 = new MK3MultiBlockMechanic();
    }

    public ManaLevelBlock(ResourceLocation name) {
        super(name, LEVEL_BLOCK);
    }

    public ManaLevelBlock(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
        itemColorPack.addColor(0, itemStack -> manaLevel.color);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.BlockColorPack blockColorPack) {
        blockColorPack.addColor(0, (blockState, blockAndTintGetter, blockPos) -> manaLevel.color);
    }

    @ConfigField
    public ManaLevelMakeData manaLevelMakeData;

    @AcceptTypeJson
    public static class ManaLevelMakeData {

        public MakeLevel makeLevel = MakeLevel.CURRENT;
        public boolean isMustRegister;
        @Nullable
        public List<IShapedOreConfig<ManaLevel>> config;

        public ManaLevelMakeData setManaLevel(MakeLevel makeLevel) {
            this.makeLevel = makeLevel;
            return this;
        }

        public ManaLevelMakeData setMustRegister(boolean mustRegister) {
            isMustRegister = mustRegister;
            return this;
        }

        public ManaLevelMakeData addOreConfig(IShapedOreConfig<ManaLevel> oreConfig) {
            if (this.config == null) {
                this.config = new ArrayList<>();
            }
            this.config.add(oreConfig);
            return this;
        }

        public final ManaLevelMakeData addOreConfig(List<IShapedOreConfig<ManaLevel>> oreConfig) {
            if (this.config == null) {
                this.config = new ArrayList<>();
            }
            this.config.addAll(oreConfig);
            return this;
        }

    }


}
