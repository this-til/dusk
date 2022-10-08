package com.til.dusk.common.register.ore.ore;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigKey;
import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.data.tag.FluidTag;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.UnitRegister;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.OreBlockMineral;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.ores.*;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.skill.Skill;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Util;
import com.til.dusk.util.nbt.cell.AllNBTCell;
import com.til.dusk.util.pack.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        if (hasConfig(IS_LEVEL_ACCEPTABLE)) {
            ItemTag.addTag(getConfig(IS_LEVEL_ACCEPTABLE).acceptableTagPack.getTagPack(oreItem).itemTagKey(), itemPack.item());
        }
    }

    @Override
    protected void registerBlock(OreBlock oreBlock, BlockPack blockPack) {
        super.registerBlock(oreBlock, blockPack);
        if (hasConfig(IS_LEVEL_ACCEPTABLE)) {
            ItemTag.addTag(getConfig(IS_LEVEL_ACCEPTABLE).acceptableTagPack.getTagPack(oreBlock).itemTagKey(), blockPack.blockItem());
            BlockTag.addTag(getConfig(IS_LEVEL_ACCEPTABLE).acceptableTagPack.getTagPack(oreBlock).blockTagKey(), blockPack.block());
        }
        if (oreBlock.hasConfig(OreBlock.IS_MINERAL)) {
            ItemTag.addTag(tagPackSupplier.getTagPack(OreBlockMineral.MINERAL_NAME).itemTagKey(), blockPack.blockItem());
            BlockTag.addTag(tagPackSupplier.getTagPack(OreBlockMineral.MINERAL_NAME).blockTagKey(), blockPack.block());
        }
    }

    @Override
    protected void registerFluid(OreFluid oreFluid, FluidPack fluidPack) {
        super.registerFluid(oreFluid, fluidPack);
        if (hasConfig(IS_LEVEL_ACCEPTABLE)) {
            FluidTag.addTag(getConfig(IS_LEVEL_ACCEPTABLE).acceptableTagPack.getTagPack(oreFluid).fluidTagKey(), fluidPack.source());
            FluidTag.addTag(getConfig(IS_LEVEL_ACCEPTABLE).acceptableTagPack.getTagPack(oreFluid).fluidTagKey(), fluidPack.flowing());
            if (fluidPack.bucketItem() != null) {
                ItemTag.addTag(getConfig(IS_LEVEL_ACCEPTABLE).acceptableTagPack.getTagPack(oreFluid).itemTagKey(), fluidPack.bucketItem());
            }
            if (fluidPack.liquidBlock() != null) {
                BlockTag.addTag(getConfig(IS_LEVEL_ACCEPTABLE).acceptableTagPack.getTagPack(oreFluid).blockTagKey(), fluidPack.liquidBlock());
            }
        }
    }

    @Override
    public void registerShaped(Consumer<Shaped> shapedConsumer) {
        if (hasConfig(RELEVANT_SHAPED)) {
            for (Shaped shaped : getConfig(RELEVANT_SHAPED)) {
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

    /***
     * 颜色
     */
    public static final ConfigKey<DuskColor> COLOR = new ConfigKey<>("ore.color", AllNBTCell.COLOR, DuskColor::new);
    /***
     * 矿物处理的等级
     */
    public static final ConfigKey<ManaLevel> MANA_LEVEL = new ConfigKey<>("ore.mana_level", AllNBTCell.MANA_LEVEL, null);
    /***
     * 强度系数，对应方块的采集时间和防爆，和加工时间倍数
     */
    public static final ConfigKey<Double> STRENGTH = new ConfigKey<>("strength", AllNBTCell.DOUBLE, () -> 1D);
    /***
     * 加工消耗灵气倍数
     */
    public static final ConfigKey<Double> CONSUME = new ConfigKey<>("consume", AllNBTCell.DOUBLE, () -> 1D);
    /***
     * 有粉末
     */
    public static final ConfigKey.VoidConfigKey HAS_DUST = new ConfigKey.VoidConfigKey("ore.has_dust");
    /***
     * 是金属
     */
    public static final ConfigKey.VoidConfigKey IS_METAL = new ConfigKey.VoidConfigKey("ore.is_metal");
    /***
     * 是晶体
     */
    public static final ConfigKey.VoidConfigKey IS_CRYSTA = new ConfigKey.VoidConfigKey("ore.is_crysta");
    /***
     * 表明该灵压等级可接受物品作为通用输入
     */
    public static final ConfigKey<ManaLevel> IS_LEVEL_ACCEPTABLE = new ConfigKey<>("ore.is_level_acceptable", AllNBTCell.MANA_LEVEL, () -> ManaLevel.t1);
    /***
     * 相关配方生成
     */
    public static final ConfigKey<List<Shaped>> RELEVANT_SHAPED = new ConfigKey<>("ore.relevant_shaped", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP.getListNBTCell()), List::of);


    public static class ArmorConfig {
        public static final ConfigKey.ConfigMapKey ARMOR_CONFIG = new ConfigKey.ConfigMapKey("ore.armor_config");
        public static final List<Integer> DEFAULT_DURABILITY = new ArrayList<>(4);
        public static final List<Integer> DEFAULT_DEFENSE = new ArrayList<>(4);


        static {
            DEFAULT_DURABILITY.add(300);
            DEFAULT_DURABILITY.add(400);
            DEFAULT_DURABILITY.add(400);
            DEFAULT_DURABILITY.add(300);
            DEFAULT_DEFENSE.add(2);
            DEFAULT_DEFENSE.add(6);
            DEFAULT_DEFENSE.add(5);
            DEFAULT_DEFENSE.add(2);
        }


        /***
         * 耐久
         */
        public static final ConfigKey<List<Integer>> DURABILITY = new ConfigKey<>("ore.armor.durability", AllNBTCell.INT.getListNBTCell(), List::of);

        /***
         * 附件
         */
        public static final ConfigKey<List<Integer>> DEFENSE = new ConfigKey<>("ore.armor.defense", AllNBTCell.INT.getListNBTCell(), List::of);

        /***
         * 韧性
         */
        public static final ConfigKey<Integer> TOUGHNESS = new ConfigKey<>("ore.armor.toughness", AllNBTCell.INT, () -> 3);

        /***
         * 击退抗性
         */
        public static final ConfigKey<Float> KNOCK_BACK_RESISTANCE = new ConfigKey<>("ore.armor.knock_back_resistance", AllNBTCell.FLOAT, () -> 0.25F);

        /***
         * 附魔值
         */
        public static final ConfigKey<Integer> ENCHANTMENT_VALUE = new ConfigKey<>("ore.armor.enchantment_value", AllNBTCell.INT, () -> 23);

        /***
         * 基础灵气
         * 如果为0物品将没有灵气处理的能力
         */
        public static final ConfigKey<Long> MANA_BASICS = new ConfigKey<>("ore.arms.mana_basics", AllNBTCell.LONG, () -> 0L);

        /***
         * 流速基础
         */
        public static final ConfigKey<Long> RATE_BASICS = new ConfigKey<>("ore.arms.rate_basics", AllNBTCell.LONG, () -> 0L);

        /***
         * 默认技能
         */
        public static final ConfigKey<Map<Skill, Integer>> DEFAULT_SKILL = new ConfigKey<>("ore.armor.default_skill", AllNBTCell.SKILL_INT_MAP, Map::of);

        public static List<Integer> ofDurability(int durability) {
            List<Integer> list = new ArrayList<>(DEFAULT_DURABILITY.size());
            for (Integer integer : DEFAULT_DURABILITY) {
                list.add(integer * durability);
            }
            return list;
        }

        public static List<Integer> ofDefense(int defense) {
            List<Integer> list = new ArrayList<>(DEFAULT_DEFENSE.size());
            for (Integer integer : DEFAULT_DEFENSE) {
                list.add(integer * defense);
            }
            return list;
        }

    }

    public static class ArmsConfig {
        public static final ConfigKey.ConfigMapKey ARMS_CONFIG = new ConfigKey.ConfigMapKey("ore.arms_config");

        /***
         * 层级[弃用?]
         */
        public static final ConfigKey<Integer> LEVEL = new ConfigKey<>("ore.arms.level", AllNBTCell.INT, () -> 5);

        /***
         * 耐久
         */
        public static final ConfigKey<Integer> USES = new ConfigKey<>("ore.arms.uses", AllNBTCell.INT, () -> 2400);

        /***
         * 挖掘速度
         */
        public static final ConfigKey<Float> SPEED = new ConfigKey<>("ore.arms.speed", AllNBTCell.FLOAT, () -> -3F);

        /***
         * 攻击基础伤害
         */
        public static final ConfigKey<Integer> ATTACK_DAMAGE_BONUS = new ConfigKey<>("ore.arms.attack_damage_bonus", AllNBTCell.INT, () -> 10);

        /***
         * 附魔等级
         */
        public static final ConfigKey<Integer> ENCHANTMENT_VALUE = new ConfigKey<>("ore.arms.enchantment_value", AllNBTCell.INT, () -> 23);

        /***
         * 一个方块的tag表示当前层级可以挖掘的方块草
         */
        public static final ConfigKey<TagKey<Block>> TAG = new ConfigKey<>("ore.arms.tag", AllNBTCell.BLOCK_TAG, null);

        /***
         * 修复工具
         */
        public static final ConfigKey<List<TagKey<Item>>> REPAIR_ITEM = new ConfigKey<>("ore.item.repair_item", AllNBTCell.ITEM_TAG.getListNBTCell(), null);

        /***
         * 基础灵气
         * 如果为0物品将没有灵气处理的能力
         */
        public static final ConfigKey<Long> MANA_BASICS = new ConfigKey<>("ore.arms.mana_basics", AllNBTCell.LONG, () -> 0L);

        /***
         * 流速基础
         */
        public static final ConfigKey<Long> RATE_BASICS = new ConfigKey<>("ore.arms.rate_basics", AllNBTCell.LONG, () -> 0L);

        /***
         * 默认技能
         */
        public static final ConfigKey<Map<Skill, Integer>> DEFAULT_SKILL = new ConfigKey<>("ore.arms.default_skill", AllNBTCell.SKILL_INT_MAP, Map::of);
    }

    /***
     * 装饰方块配置
     */
    public static class DecorateBlockConfig {

        public static final ConfigKey.ConfigMapKey DECORATE_BLOCK_CONFIG = new ConfigKey.ConfigMapKey("ore.decorate.block.config");
    }

    public static class FluidConfig {

        public static final ConfigKey.ConfigMapKey FLUID_CONFIG = new ConfigKey.ConfigMapKey("ore.fluid_config");

        /***
         * 流体可复制配置
         */
        public static final ConfigKey.VoidConfigKey CAN_COPY = new ConfigKey.VoidConfigKey("ore.fluid.copy");

        public static class SplittingConfig {
            /***
             * 流体裂变配置
             */
            public static final ConfigKey.ConfigMapKey SPLITTING = new ConfigKey.ConfigMapKey("ore.fluid.splitting");
            public static final ConfigKey<List<IShapedOreConfig<Void>>> SUNLIGHT = new ConfigKey<>("ore.fluid.splitting.sunlight", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP.getListNBTCell()), List::of);
            public static final ConfigKey<List<IShapedOreConfig<Void>>> MOONLIGHT = new ConfigKey<>("ore.fluid.splitting.moonlight", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP.getListNBTCell()), List::of);
            public static final ConfigKey<List<IShapedOreConfig<Void>>> RAIN = new ConfigKey<>("ore.fluid.splitting.rain", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP.getListNBTCell()), List::of);
        }

    }

    /***
     * 矿石配置
     */
    public static class MineralBlockConfig {
        /***
         * 洗矿副产物
         */
        public static final ConfigKey<List<IShapedOreConfig<Void>>> WASH_BYPRODUCT = new ConfigKey<>("ore.mineral_block.wash_byproduct", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP.getListNBTCell()), List::of);

        /***
         * 离心副产物
         */
        public static final ConfigKey<List<IShapedOreConfig<Void>>> CENTRIFUGE_BYPRODUCT = new ConfigKey<>("ore.mineral_block.centrifuge_byproduct", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP.getListNBTCell()), List::of);

        /***
         * 筛选副产物
         */
        public static final ConfigKey<List<IShapedOreConfig<Void>>> SCREEN_BYPRODUCT = new ConfigKey<>("ore.mineral_block.screen_byproduct", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP.getListNBTCell()), List::of);

        /***
         * 矿物生成
         */
        public static final ConfigKey<List<IOrePlacedFeatureConfig>> PLACED_FEATURE = new ConfigKey<>("ore.mineral_block.placed_feature", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP.getListNBTCell()), List::of);
        public static final ConfigKey.ConfigMapKey MINERAL_BLOCK_CONFIG = new ConfigKey.ConfigMapKey("ore.mineral_block");
    }

    public static class ToolDataConfig {

        public static final ConfigKey.ConfigMapKey TOOL_DATA_CONFIG = new ConfigKey.ConfigMapKey("ore.tool_data_config");

        /***
         * 耐久
         */
        public static final ConfigKey<Integer> USES = new ConfigKey<>("ore.tool_data.uses", AllNBTCell.INT, () -> 64);

        /***
         * 储罐最大液体
         */
        public static final ConfigKey<Integer> TANK_MAX = new ConfigKey<>("ore.tool_data.tank_max", AllNBTCell.INT, () -> 4000);
    }
}
