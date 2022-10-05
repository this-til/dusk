package com.til.dusk.common.register.ore.ore;

import com.til.dusk.common.config.ConfigKey;
import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.skill.Skill;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Util;
import com.til.dusk.util.nbt.cell.AllNBTCell;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class OreConfig {

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
            public static final ConfigKey<List<IShapedOreConfig>> SUNLIGHT = new ConfigKey<>("ore.fluid.splitting.sunlight", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP.getListNBTCell()), List::of);
            public static final ConfigKey<List<IShapedOreConfig>> MOONLIGHT = new ConfigKey<>("ore.fluid.splitting.moonlight", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP.getListNBTCell()), List::of);
            public static final ConfigKey<List<IShapedOreConfig>> RAIN = new ConfigKey<>("ore.fluid.splitting.rain", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP.getListNBTCell()), List::of);
        }

    }

    /***
     * 装饰方块配置
     */
    public static class DecorateBlockConfig {

        public static final ConfigKey.ConfigMapKey DECORATE_BLOCK_CONFIG = new ConfigKey.ConfigMapKey("ore.decorate.block.config");
    }


    /***
     * 矿石配置
     */
    public static class MineralBlockConfig {
        /***
         * 洗矿副产物
         */
        public static final ConfigKey<List<IShapedOreConfig>> WASH_BYPRODUCT = new ConfigKey<>("ore.mineral_block.wash_byproduct", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP.getListNBTCell()), List::of);

        /***
         * 离心副产物
         */
        public static final ConfigKey<List<IShapedOreConfig>> CENTRIFUGE_BYPRODUCT = new ConfigKey<>("ore.mineral_block.centrifuge_byproduct", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP.getListNBTCell()), List::of);

        /***
         * 筛选副产物
         */
        public static final ConfigKey<List<IShapedOreConfig>> SCREEN_BYPRODUCT = new ConfigKey<>("ore.mineral_block.screen_byproduct", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP.getListNBTCell()), List::of);

        /***
         * 矿物生成
         */
        public static final ConfigKey<List<IOrePlacedFeatureConfig>> PLACED_FEATURE = new ConfigKey<>("ore.mineral_block.placed_feature", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP.getListNBTCell()), List::of);
        public static final ConfigKey.ConfigMapKey MINERAL_BLOCK_CONFIG = new ConfigKey.ConfigMapKey("ore.mineral_block");
    }

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

    public static class ToolDataConfig {

        /***
         * 耐久
         */
        public static final ConfigKey<Integer> USES = new ConfigKey<>("ore.tool_data.uses", AllNBTCell.INT, () -> 64);

        /***
         * 储罐最大液体
         */
        public static final ConfigKey<Integer> TANK_MAX = new ConfigKey<>("ore.tool_data.tank_max", AllNBTCell.INT, () -> 4000);

        public static final ConfigKey.ConfigMapKey TOOL_DATA_CONFIG = new ConfigKey.ConfigMapKey("ore.tool_data_config");
    }
}
