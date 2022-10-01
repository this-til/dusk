package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.Dusk;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.*;
import com.til.dusk.util.pack.DataPack;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class ManaLevelBlock extends RegisterBasics.BlockUnitRegister<ManaLevelBlock, ManaLevel> {


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
    public static DefaultCapacityMechanic frameBasic;

    //产物
    /***
     * 日光晶体
     * 太阳能神教
     */
    public static SimilarSolarEnergyMechanic sunlight;

    /***
     * 月光晶体
     */
    public static SimilarSolarEnergyMechanic moonlight;

    /***
     * 雨灵晶体
     */
    public static SimilarSolarEnergyMechanic rain;

    /***
     * 灵气提提取器
     */
    public static HandleMechanic extractMana;

    /***
     * 解咒转灵晶体
     */
    public static ExtractManaMechanic dischantmentMana;

    /***
     *  末影转灵晶体
     */
    public static ExtractManaMechanic enderMana;

    /***
     * 药水转灵晶体
     */
    public static ExtractManaMechanic potionMana;

    /***
     * 爆破转灵晶体
     */
    public static ExtractManaMechanic explosiveMana;

    /***
     * 寒霜转灵晶体
     */
    public static ExtractManaMechanic frostyMana;

    /***
     * 粘液转灵晶体
     */
    public static ExtractManaMechanic slimeyMana;

    /***
     * 口臭转灵晶体
     */
    public static ExtractManaMechanic halitosisMana;

    /***
     * 烈焰转灵晶体
     */
    public static ExtractManaMechanic flameMana;

    /***
     * 植物转灵晶体
     */
    public static ExtractManaMechanic botanyMana;

    /***
     * 食物转灵晶体
     */
    public static ExtractManaMechanic foodMana;

    //处理

    /***
     * 研磨
     */
    public static HandleMechanic grind;

    /***
     * 洗涤
     */
    public static HandleMechanic wash;

    /***
     * 离心
     */
    public static HandleMechanic centrifugal;

    /***
     * 打包
     */
    public static HandleMechanic pack;

    /***
     * 解包
     */
    public static HandleMechanic unpack;

    /***
     * 高炉
     */
    public static HandleMechanic blastFurnace;

    /***
     * 结晶
     */
    public static HandleMechanic crystallizing;

    public static HandleMechanic crystalSeedMake;

    /***
     * 组装机
     */
    public static HandleMechanic assemble;

    /***
     * 封装
     */
    public static HandleMechanic encapsulation;

    /***
     * 晶体组装机
     */
    public static HandleMechanic crystalAssemble;

    /***
     * 蒸馏
     */
    public static HandleMechanic distillation;

    /***
     * 溶解
     */
    public static HandleMechanic dissolution;

    /***
     * 凝固
     */
    public static HandleMechanic freezing;

    /***
     * 高压融合
     */
    public static HandleMechanic highPressureFuse;

    /***
     * 雕刻
     */
    public static HandleMechanic carving;

    /***
     * 筛选
     */
    public static Mechanic screen;

    /***
     * 熔炉
     */
    public static Mechanic furnace;

    /***
     * 造石机
     */
    public static Mechanic makerStone;

    /***
     * 冲压机
     */
    public static Mechanic stampingMachine;

    /***
     * 车床
     */
    public static Mechanic lathe;

    /***
     * 扎线机
     */
    public static Mechanic tieWire;

    /***
     * 切割
     */
    public static Mechanic cutting;

    /***
     * 压杆
     */
    public static Mechanic pressureStick;

    /***
     * 混合
     */
    public static Mechanic blend;

    /***
     * 分解
     */
    public static Mechanic decompose;

    /***
     * 回收
     */
    public static Mechanic recovery;

    /***
     * 成型
     */
    public static Mechanic shaping;

    /***
     * 灵气凝结晶体
     */
    public static Mechanic manaCoagulation;

    /***
     * 干细胞提晶体
     */
    public static Mechanic stemCellExtract;

    /***
     * 细胞培养晶体
     */
    public static Mechanic cellCulture;

    /***
     * uu生成
     */
    public static Mechanic uuGenerate;

    /***
     * 质量生成
     */
    public static Mechanic qualityGenerate;

    /***
     * 透析晶体
     */
    public static Mechanic dialysis;

    /***
     * 裂解晶体
     */
    public static Mechanic splitting;

    //功能

    /***
     * 虚空箱
     * 1280 * l
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
        repeater = new RepeaterMechanic("repeater");
        frameBasic = new DefaultCapacityMechanic("frame_basic");
        sunlight = (SimilarSolarEnergyMechanic) new SimilarSolarEnergyMechanic("sunlight", 1, level -> level.isDay() && !level.isRaining(), ColorPrefab.SUNLIGHT_COLOR)
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .setMakeLevel(ManaLevelMakeData.MakeLevel.CURRENT)
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                        .addInItem(Ore.sunlight.itemMap.get(OreItem.perfectCrystal).itemTag(), 1));
        moonlight = (SimilarSolarEnergyMechanic) new SimilarSolarEnergyMechanic("moonlight", 1, level -> level.isNight() && !level.isRaining(), ColorPrefab.MOONLIGHT_COLOR)
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .setMakeLevel(ManaLevelMakeData.MakeLevel.CURRENT)
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                        .addInItem(Ore.moonlight.itemMap.get(OreItem.perfectCrystal).itemTag(), 1));
        rain = (SimilarSolarEnergyMechanic) new SimilarSolarEnergyMechanic("rain", 4, Level::isRaining, ColorPrefab.RAIN_COLOR)
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .setMakeLevel(ManaLevelMakeData.MakeLevel.CURRENT)
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                        .addInItem(Ore.rain.itemMap.get(OreItem.perfectCrystal).itemTag(), 1));
        extractMana = (HandleMechanic) new HandleMechanic("extract_mana", () -> List.of(ShapedType.extractMana))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addInItem(Ore.spiritSilver.itemMap.get(OreItem.plate).itemTag(), 3)
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(blastFurnace).blockItemTag(), 1)));
        dischantmentMana = (ExtractManaMechanic) new ExtractManaMechanic("dischantment_mana", () -> List.of(ShapedType.dischantmentMana), new DuskColor(135, 60, 168, 255))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .setMakeLevel(ManaLevelMakeData.MakeLevel.CURRENT)
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                        .addInItem(ItemTag.ENCHANTING_TABLE.d1(), 1)
                        .addInItem(ItemTag.ENCHANTING_BOOK, 3));
        enderMana = (ExtractManaMechanic) new ExtractManaMechanic("ender_mana", () -> List.of(ShapedType.enderMana), new DuskColor(96, 22, 96))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .setMakeLevel(ManaLevelMakeData.MakeLevel.CURRENT)
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                        .addInItem(Tags.Items.ENDER_PEARLS, 8)
                        .addInItem(ItemTag.ENDER_EYE, 8));
        potionMana = (ExtractManaMechanic) new ExtractManaMechanic("potion_mana", () -> List.of(ShapedType.potionMana), new DuskColor(243, 138, 255))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .setMakeLevel(ManaLevelMakeData.MakeLevel.CURRENT)
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                        .addInItem(ItemTag.BREWING_STAND.d1(), 1));
        explosiveMana = (ExtractManaMechanic) new ExtractManaMechanic("explosive_mana", () -> List.of(ShapedType.explosiveMana), new DuskColor(178, 25, 25))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .setMakeLevel(ManaLevelMakeData.MakeLevel.CURRENT)
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                        .addInItem(ItemTag.TNT.d1(), 4)
                        .addInItem(Tags.Items.GUNPOWDER, 16));
        frostyMana = (ExtractManaMechanic) new ExtractManaMechanic("frosty_mana", () -> List.of(ShapedType.frostyMana), new DuskColor(29, 237, 255))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .setMakeLevel(ManaLevelMakeData.MakeLevel.CURRENT)
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                        .addInItem(ItemTag.ICES.d1(), 32)
                        .addInItem(ItemTag.SNOW_BLOCK.d1(), 32));
        slimeyMana = (ExtractManaMechanic) new ExtractManaMechanic("slimey_mana", () -> List.of(ShapedType.slimeyMana), new DuskColor(43, 255, 33))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .setMakeLevel(ManaLevelMakeData.MakeLevel.CURRENT)
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                        .addInItem(ItemTag.SLIME_BALL, 32)
                        .addInItem(ItemTag.SLIME_BLOCK.d1(), 12));
        halitosisMana = (ExtractManaMechanic) new ExtractManaMechanic("halitosis_mana", () -> List.of(ShapedType.halitosisMana), new DuskColor(229, 45, 136))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .setMakeLevel(ManaLevelMakeData.MakeLevel.CURRENT)
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                        .addInItem(ItemTag.DRAGON_BREATH, 16));
        flameMana = (ExtractManaMechanic) new ExtractManaMechanic("flame_mana", () -> List.of(ShapedType.flameMana), new DuskColor(255, 0, 0))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .setMakeLevel(ManaLevelMakeData.MakeLevel.CURRENT)
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                        .addInFluid(FluidTags.LAVA, 32000));
        botanyMana = (ExtractManaMechanic) new ExtractManaMechanic("botany_mana", () -> List.of(ShapedType.botanyMana), new DuskColor(7, 140, 0))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .setMakeLevel(ManaLevelMakeData.MakeLevel.CURRENT)
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                        .addInItem(ItemTags.FLOWERS, 256));
        foodMana = (ExtractManaMechanic) new ExtractManaMechanic("food_mana", () -> List.of(ShapedType.foodMana), new DuskColor(255, 184, 66))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .setMakeLevel(ManaLevelMakeData.MakeLevel.CURRENT)
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                        .addInItem(ItemTag.BREAD, 64));
        grind = (HandleMechanic) new HandleMechanic("grind", () -> List.of(ShapedType.grind))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addInItem(Tags.Items.GEMS_DIAMOND, 2)
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(frameBasic).blockItemTag(), 1))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.destruction.getTag(m), 1))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.power.getTag(m), 1)));
        wash = (HandleMechanic) new HandleMechanic("wash", () -> List.of(ShapedType.wash))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(frameBasic).blockItemTag(), 1))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.forming.getTag(m), 1))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.destruction.getTag(m), 1))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.power.getTag(m), 1))
                        .addInItem(Tags.Items.BARRELS, 4));
        centrifugal = (HandleMechanic) new HandleMechanic("centrifugal", () -> List.of(ShapedType.centrifugal))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(frameBasic).blockItemTag(), 1))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.forming.getTag(m), 1))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.destruction.getTag(m), 1))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.power.getTag(m), 1))
                        .addRun((s, m) -> s.addInItem(m.getRelationTagPack(OreItem.rotor).itemTagKey(), 2)));
        pack = (HandleMechanic) new HandleMechanic("pack", () -> List.of(ShapedType.pack))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(frameBasic).blockItemTag(), 1))
                        .addInItem(ItemTag.CRAFTING_TABLE.d1(), 9)
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.forming.getTag(m), 1)));
        unpack = (HandleMechanic) new HandleMechanic("unpack", () -> List.of(ShapedType.unpack))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(frameBasic).blockItemTag(), 1))
                        .addInItem(ItemTag.CRAFTING_TABLE.d1(), 9)
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.destruction.getTag(m), 1)));
        blastFurnace = (HandleMechanic) new HandleMechanic("blast_furnace", () -> List.of(ShapedType.blastFurnace))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(frameBasic).blockItemTag(), 1))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.forming.getTag(m), 1))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.power.getTag(m), 2))
                        .addRun((s, m) -> s.addInItem(m.getRelationTagPack(OreBlock.coil).itemTagKey(), 2)));
        crystallizing = (HandleMechanic) new HandleMechanic("crystallizing", () -> List.of(ShapedType.crystallizing))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(frameBasic).blockItemTag(), 1))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.forming.getTag(m), 4))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.power.getTag(m), 1))
                        .addRun((s, m) -> s.addInItem(OreItem.crystalSeed.getTagPack().itemTagKey(), 12)));
        crystalSeedMake = (HandleMechanic) new HandleMechanic("crystal_seed_make", () -> List.of(ShapedType.crystalSeedMake))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.destruction.getTag(m), 1))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.power.getTag(m), 1))
                        .addRun((s, m) -> s.addInItem(OreItem.crystalSeed.getTagPack().itemTagKey(), 12)));
        assemble = (HandleMechanic) new HandleMechanic("assemble", () -> List.of(ShapedType.assemble))
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(frameBasic).blockItemTag(), 1))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.forming.getTag(m), 4))
                        .addRun((s, m) -> s.addInItem(ManaLevelItem.instructions.getTag(m), 2)));
        encapsulation = new HandleMechanic("encapsulation", () -> List.of(ShapedType.encapsulation));
        crystalAssemble = new HandleMechanic("crystal_assemble", () -> List.of(ShapedType.crystalAssemble));
        distillation = new HandleMechanic("distillation", () -> List.of(ShapedType.distillation));
        dissolution = new HandleMechanic("dissolution", () -> List.of(ShapedType.dissolution));
        freezing = new HandleMechanic("freezing", () -> List.of(ShapedType.freezing));
        highPressureFuse = new HandleMechanic("high_pressure_fuse", () -> List.of(ShapedType.highPressureFuse));
        carving = new HandleMechanic("carving", () -> List.of(ShapedType.carving));
        screen = new HandleMechanic("screen", () -> List.of(ShapedType.screen));
        furnace = new HandleMechanic("furnace", () -> List.of(ShapedType.furnace));
        makerStone = new HandleMechanic("maker_stone", () -> List.of(ShapedType.makerStone));
        stampingMachine = new HandleMechanic("stamping_machine", () -> List.of(ShapedType.stampingMachine));
        lathe = new HandleMechanic("lathe", () -> List.of(ShapedType.lathe));
        tieWire = new HandleMechanic("tie_wire", () -> List.of(ShapedType.tieWire));
        cutting = new HandleMechanic("cutting", () -> List.of(ShapedType.cutting));
        pressureStick = new HandleMechanic("pressure_stick", () -> List.of(ShapedType.pressureStick));
        blend = new HandleMechanic("blend", () -> List.of(ShapedType.blend));
        decompose = new HandleMechanic("decompose", () -> List.of(ShapedType.decompose));
        recovery = new HandleMechanic("recovery", () -> List.of(ShapedType.recovery));
        shaping = new HandleMechanic("shaping", () -> List.of(ShapedType.forming));
        manaCoagulation = new HandleMechanic("mana_coagulation", () -> List.of(ShapedType.manaCoagulation));
        stemCellExtract = new HandleMechanic("stem_cell_extract", () -> List.of(ShapedType.stemCellExtract));
        cellCulture = new HandleMechanic("cell_culture", () -> List.of(ShapedType.cellCulture));
        uuGenerate = new HandleMechanic("uu_generate", () -> List.of(ShapedType.uuGenerate));
        qualityGenerate = new HandleMechanic("quality_generate", () -> List.of(ShapedType.qualityGenerate));
        dialysis = new HandleMechanic("dialysis", () -> List.of(ShapedType.dialysis));
        splitting = new HandleMechanic("splitting", () -> List.of(ShapedType.splitting));
        voidCase = (VoidCaseMechanic) new VoidCaseMechanic()
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(frameBasic).blockItemTag(), 1))
                        .addRun((s, m) -> s.addInItem(Ore._void.itemMap.get(OreItem.plate).itemTag(), 3 * m.level))
                        .addRun((s, m) -> s.addInItem(Ore._void.itemMap.get(OreItem.casing).itemTag(), 12 * m.level))
                        .addRun((s, m) -> s.addInItem(Ore._void.itemMap.get(OreItem.foil).itemTag(), 12 * m.level))
                        .addRun((s, m) -> s.addInItem(Tags.Items.CHESTS, 12 * m.level)));
        voidTank = (VoidTankMechanic) new VoidTankMechanic()
                .setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                        .addRun((s, m) -> s.addInItem(m.blockMap.get(frameBasic).blockItemTag(), 1))
                        .addRun((s, m) -> s.addInItem(Ore._void.itemMap.get(OreItem.plate).itemTag(), 3 * m.level))
                        .addRun((s, m) -> s.addInItem(Ore._void.itemMap.get(OreItem.casing).itemTag(), 12 * m.level))
                        .addRun((s, m) -> s.addInItem(Ore._void.itemMap.get(OreItem.foil).itemTag(), 12 * m.level))
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
        public MakeLevel makeLevel = MakeLevel.UP;

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
