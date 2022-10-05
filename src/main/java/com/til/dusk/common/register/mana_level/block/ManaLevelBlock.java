package com.til.dusk.common.register.mana_level.block;

import com.til.dusk.Dusk;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.BlockUnitRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.mechanic.*;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.*;
import com.til.dusk.util.pack.DataPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.Set;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class ManaLevelBlock extends BlockUnitRegister<ManaLevelBlock, ManaLevel> {


    public static Supplier<IForgeRegistry<ManaLevelBlock>> LEVEL_BLOCK;

    //基础
    /***
     * 中继器
     * 在提取相应方块能力
     */
    public static RepeaterMechanic repeater;

    /***
     * 基础框架
     */
    public static FrameBasicMechanic frameBasic;

    //产物
    /***
     * 日光晶体
     * 太阳能神教
     */
    public static SunlightSimilarSolarEnergyMechanic sunlight;

    /***
     * 月光晶体
     */
    public static MoonlightSimilarSolarEnergyMechanic moonlight;

    /***
     * 雨灵晶体
     */
    public static RainSimilarSolarEnergyMechanic rain;

    /***
     * 灵气提提取器
     */
    public static ExtractManaHandleMechanic extractMana;

    /***
     * 解咒转灵晶体
     */
    public static DischantmentManaMechanic dischantmentMana;

    /***
     *  末影转灵晶体
     */
    public static EnderManaMechanic enderMana;

    /***
     * 药水转灵晶体
     */
    public static PotionManaMechanic potionMana;

    /***
     * 爆破转灵晶体
     */
    public static ExplosiveManaMechanic explosiveMana;

    /***
     * 寒霜转灵晶体
     */
    public static ExtractManaMechanic frostyMana;

    /***
     * 粘液转灵晶体
     */
    public static SlimeyManaMechanic slimeyMana;

    /***
     * 口臭转灵晶体
     */
    public static HalitosisManaMechanic halitosisMana;

    /***
     * 烈焰转灵晶体
     */
    public static FlameManaMechanic flameMana;

    /***
     * 植物转灵晶体
     */
    public static ExtractManaMechanic botanyMana;

    /***
     * 食物转灵晶体
     */
    public static FoodManaMechanic foodMana;

    //处理

    /***
     * 研磨
     */
    public static GrindMechanic grind;

    /***
     * 洗涤
     */
    public static WashMechanic wash;

    /***
     * 离心
     */
    public static CentrifugalMechanic centrifugal;

    /***
     * 打包
     */
    public static PackMechanic pack;

    /***
     * 解包
     */
    public static UnpackMechanic unpack;

    /***
     * 高炉
     */
    public static BlastFurnaceMechanic blastFurnace;

    /***
     * 结晶
     */
    public static CrystallizingMechanic crystallizing;

    /***
     * 种子制造
     */
    public static CrystalSeedMakeMechanic crystalSeedMake;

    /***
     * 组装机
     */
    public static AssembleMechanic assemble;

    /***
     * 封装
     */
    public static EncapsulationMechanic encapsulation;

    /***
     * 晶体组装机
     */
    public static CrystalAssembleMechanic crystalAssemble;

    /***
     * 蒸馏
     */
    public static DistillationMechanic distillation;

    /***
     * 溶解
     */
    public static DissolutionMechanic dissolution;

    /***
     * 凝固
     */
    public static FreezingMechanic freezing;

    /***
     * 高压融合
     */
    public static HighPressureFuseMechanic highPressureFuse;

    /***
     * 雕刻
     */
    public static HandleMechanic carving;

    /***
     * 筛选
     */
    public static ScreenMechanic screen;

    /***
     * 熔炉
     */
    public static FurnaceMechanic furnace;

    /***
     * 造石机
     */
    public static MakerStoneMechanic makerStone;

    /***
     * 冲压机
     */
    public static StampingMachineMechanic stampingMachine;

    /***
     * 车床
     */
    public static LatheMechanic lathe;

    /***
     * 扎线机
     */
    public static TieWireMechanic tieWire;

    /***
     * 切割
     */
    public static CuttingMechanic cutting;

    /***
     * 压杆
     */
    public static PressureStickMechanic pressureStick;

    /***
     * 混合
     */
    public static BlendMechanic blend;

    /***
     * 分解
     */
    public static DecomposeMechanic decompose;

    /***
     * 回收
     */
    public static RecoveryMechanic recovery;

    /***
     * 成型
     */
    public static ShapingMechanic shaping;

    /***
     * 灵气凝结晶体
     */
    public static HandleMechanic manaCoagulation;

    /***
     * 干细胞提晶体
     */
    public static HandleMechanic stemCellExtract;

    /***
     * 细胞培养晶体
     */
    public static HandleMechanic cellCulture;

    /***
     * uu生成
     */
    public static HandleMechanic uuGenerate;

    /***
     * 质量生成
     */
    public static HandleMechanic qualityGenerate;

    /***
     * 透析晶体
     */
    public static HandleMechanic dialysis;

    /***
     * 裂解晶体
     */
    public static HandleMechanic splitting;

    //功能

    /***
     * 虚空箱
     * 4096 * l
     */
    public static VoidCaseMechanic voidCase;

    /***
     * 虚空缸
     * 1280000mb * l
     */
    public static VoidTankMechanic voidTank;

    /***
     * 聚灵
     */
    public static GatherManaMechanic gatherMana;

    /***
     * 灵气传输
     */
    public static IOMechanic.ManaIO manaIO;

    /***
     * 物品传输
     */
    public static IOMechanic.ItemIO itemIO;

    /***
     * 流体传输
     */
    public static IOMechanic.FluidIO fluidIO;

    /***
     * 收集晶体
     */
    public static CollectMechanic collect;

    /***
     * 回旋升压
     */
    public static WhirlBoostMechanic whirlBoost;

    /***
     * 充能晶体
     */
    public static ChargeMechanic charge;

    /***
     * 泵晶体
     */
    public static PumpMechanic pumpMechanic;

    /***
     * 挖掘晶体
     */
    public static MiningMechanic mining;

    /***
     * 屠杀晶体
     */
    public static MassacreMechanic massacre;

    /***
     * FE转换晶体
     */
    public static TransformationFEMechanic transformationFEMechanic;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        LEVEL_BLOCK = event.create(new RegistryBuilder<ManaLevelBlock>().setName(new ResourceLocation(Dusk.MOD_ID, "mana_level_block")));
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
        blend =  new BlendMechanic();
        decompose = new DecomposeMechanic();
        recovery =  new RecoveryMechanic();
        shaping = new ShapingMechanic();
        manaCoagulation = new ManaCoagulationMechanic();
        stemCellExtract = (HandleMechanic) new HandleMechanic("stem_cell_extract", () -> Set.of(ShapedType.stemCellExtract));
        cellCulture = (HandleMechanic) new HandleMechanic("cell_culture", () -> Set.of(ShapedType.cellCulture));
        uuGenerate = (HandleMechanic) new HandleMechanic("uu_generate", () -> Set.of(ShapedType.uuGenerate))
                .setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addRun((s, m) -> s.addInItem(m.get(ManaLevelBlock.slimeyMana).blockItemTag(), 1))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.forming.getTag(m), 4))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.gather.getTag(m), 4))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.instructions.getTag(m), 4)));
        qualityGenerate = (HandleMechanic) new HandleMechanic("quality_generate", () -> Set.of(ShapedType.qualityGenerate))
                .setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addRun((s, m) -> s.addInItem(m.get(uuGenerate).blockItemTag(), 1))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.forming.getTag(m), 1))
                        .addRun((s, m) -> s.addInFluid(Ore.uu.get(OreFluid.solution).fluidTag(), 144 * m.level)));
        dialysis = (HandleMechanic) new HandleMechanic("dialysis", () -> Set.of(ShapedType.dialysis))
                .setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addRun((s, m) -> s.addInItem(m.get(uuGenerate).blockItemTag(), 1))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.destruction.getTag(m), 1))
                        .addRun((s, m) -> s.addInFluid(Ore.uu.get(OreFluid.solution).fluidTag(), 144 * m.level)));
        splitting = (HandleMechanic) new HandleMechanic("splitting", () -> Set.of(ShapedType.splitting))
                .setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addRun((s, m) -> s.addInItem(m.get(highPressureFuse).blockItemTag(), 1))
                        .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreBlock.coil).itemTagKey(), 2))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.forming.getTag(m), 4))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.destruction.getTag(m), 4)));
        voidCase = (VoidCaseMechanic) new VoidCaseMechanic()
                .setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addRun((s, m) -> s.addInItem(m.get(frameBasic).blockItemTag(), 1))
                        .addRun((s, m) -> s.addInItem(Ore._void.get(OreItem.plate).itemTag(), 3 * m.level))
                        .addRun((s, m) -> s.addInItem(Ore._void.get(OreItem.casing).itemTag(), 12 * m.level))
                        .addRun((s, m) -> s.addInItem(Ore._void.get(OreItem.foil).itemTag(), 12 * m.level))
                        .addRun((s, m) -> s.addInItem(Tags.Items.CHESTS, 12 * m.level)));
        voidTank = (VoidTankMechanic) new VoidTankMechanic()
                .setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addRun((s, m) -> s.addInItem(m.get(frameBasic).blockItemTag(), 1))
                        .addRun((s, m) -> s.addInItem(Ore._void.get(OreItem.plate).itemTag(), 3 * m.level))
                        .addRun((s, m) -> s.addInItem(Ore._void.get(OreItem.casing).itemTag(), 12 * m.level))
                        .addRun((s, m) -> s.addInItem(Ore._void.get(OreItem.foil).itemTag(), 12 * m.level))
                        .addRun((s, m) -> s.addInItem(ItemTag.BUCKET, 12 * m.level)));
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
    }

    public ManaLevelBlock(ResourceLocation name) {
        super(name, LEVEL_BLOCK);
    }

    public ManaLevelBlock(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    public static final GenericMap.IKey<ManaLevelMakeData> MECHANIC_MAKE_DATA = new GenericMap.IKey.Key<>();

    public static class ManaLevelMakeData extends DataPack<ManaLevelMakeData, ManaLevel> {

        /***
         * 所需组装机的等级
         */
        public MakeLevel makeLevel = MakeLevel.CURRENT;

        /***
         * 必定注册配方
         * 当设置等级UP或者NEXT如果没有对应等级将注册为t1或t8
         */
        public boolean isMustRegister = false;

        public ManaLevelMakeData setMakeLevel(MakeLevel makeLevel) {
            this.makeLevel = makeLevel;
            return this;
        }

        public enum MakeLevel {
            UP, CURRENT, NEXT
        }
    }

}
