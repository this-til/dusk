package com.til.dusk.common.config;

import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.shapeds.IShapedOreConfig;
import com.til.dusk.common.register.skill.Skill;
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
@Deprecated
public class OreConfig {
    /***
     * 有粉末
     */
    public static final IConfigKey.VoidConfigKey HAS_DUST = new IConfigKey.VoidConfigKey("ore.has_dust");

    /***
     * 是金属
     */
    public static final IConfigKey.VoidConfigKey IS_METAL = new IConfigKey.VoidConfigKey("ore.is_metal");

    /***
     * 是晶体
     */
    public static final IConfigKey.VoidConfigKey IS_CRYSTA = new IConfigKey.VoidConfigKey("ore.is_crysta");

    /***
     * 表明该灵压等级可接受物品作为通用输入
     */
    public static final IConfigKey<ManaLevel> IS_LEVEL_ACCEPTABLE = new IConfigKey<>("ore.is_level_acceptable", AllNBTCell.MANA_LEVEL, () -> ManaLevel.t1);


    public static class FluidConfig {

        /***
         * 流体可复制配置
         */
        public static final IConfigKey.VoidConfigKey CAN_COPY = new IConfigKey.VoidConfigKey("ore.fluid.copy");

        /***
         * 流体裂变配置
         */
        public static final IConfigKey<ConfigMap> SPLITTING = new IConfigKey<>("ore.fluid.splitting", AllNBTCell.CONFIG_MAP, ConfigMap::new);

        public static class SplittingConfig {
            public static final IConfigKey<List<IShapedOreConfig>> SUNLIGHT = new IConfigKey<>("ore.fluid.splitting.sunlight", Util.forcedConversion(AllNBTCell.I_SERIALIZE.getListNBTCell()), List::of);
            public static final IConfigKey<List<IShapedOreConfig>> MOONLIGHT = new IConfigKey<>("ore.fluid.splitting.moonlight", Util.forcedConversion(AllNBTCell.I_SERIALIZE.getListNBTCell()), List::of);
            public static final IConfigKey<List<IShapedOreConfig>> RAIN = new IConfigKey<>("ore.fluid.splitting.rain", Util.forcedConversion(AllNBTCell.I_SERIALIZE.getListNBTCell()), List::of);
        }

    }

    public static class DecorateBlockConfig {

    }

    public static class MineralBlockConfig {
        /***
         * 洗矿副产物
         */
        public static final IConfigKey<List<IShapedOreConfig>> WASH_BYPRODUCT = new IConfigKey<>("ore.mineral_block.wash_byproduct", Util.forcedConversion(AllNBTCell.I_SERIALIZE.getListNBTCell()), List::of);

        /***
         * 离心副产物
         */
        public static final IConfigKey<List<IShapedOreConfig>> CENTRIFUGE_BYPRODUCT = new IConfigKey<>("ore.mineral_block.centrifuge_byproduct", Util.forcedConversion(AllNBTCell.I_SERIALIZE.getListNBTCell()), List::of);

        /***
         * 筛选副产物
         */
        public static final IConfigKey<List<IShapedOreConfig>> SCREEN_BYPRODUCT = new IConfigKey<>("ore.mineral_block.screen_byproduct", Util.forcedConversion(AllNBTCell.I_SERIALIZE.getListNBTCell()), List::of);
    }

    public static class ArmorConfig {
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
        public static final IConfigKey<List<Integer>> DURABILITY = new IConfigKey<>("ore.armor.durability", AllNBTCell.INT.getListNBTCell(), List::of);

        /***
         * 附件
         */
        public static final IConfigKey<List<Integer>> DEFENSE = new IConfigKey<>("ore.armor.defense", AllNBTCell.INT.getListNBTCell(), List::of);

        /***
         * 韧性
         */
        public static final IConfigKey<Integer> TOUGHNESS = new IConfigKey<>("ore.armor.toughness", AllNBTCell.INT, () -> 3);

        /***
         * 击退抗性
         */
        public static final IConfigKey<Float> KNOCK_BACK_RESISTANCE = new IConfigKey<>("ore.armor.knock_back_resistance", AllNBTCell.FLOAT, () -> 0.25F);

        /***
         * 附魔值
         */
        public static final IConfigKey<Integer> ENCHANTMENT_VALUE = new IConfigKey<>("ore.armor.enchantment_value", AllNBTCell.INT, () -> 23);

        /***
         * 基础灵气
         * 如果为0物品将没有灵气处理的能力
         */
        public static final IConfigKey<Long> MANA_BASICS = new IConfigKey<>("ore.arms.mana_basics", AllNBTCell.LONG, () -> 0L);

        /***
         * 流速基础
         */
        public static final IConfigKey<Long> RATE_BASICS = new IConfigKey<>("ore.arms.rate_basics", AllNBTCell.LONG, () -> 0L);

        /***
         * 默认技能
         */
        public static final IConfigKey<Map<Skill, Integer>> DEFAULT_SKILL = new IConfigKey<>("ore.armor.default_skill", AllNBTCell.SKILL_INT_MAP, Map::of);

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

        /***
         * 层级[弃用?]
         */
        public static final IConfigKey<Integer> LEVEL = new IConfigKey<>("ore.arms.level", AllNBTCell.INT, () -> 5);

        /***
         * 耐久
         */
        public static final IConfigKey<Integer> USES = new IConfigKey<>("ore.arms.uses", AllNBTCell.INT, () -> 2400);

        /***
         * 挖掘速度
         */
        public static final IConfigKey<Float> SPEED = new IConfigKey<>("ore.arms.speed", AllNBTCell.FLOAT, () -> -3F);

        /***
         * 攻击基础伤害
         */
        public static final IConfigKey<Integer> ATTACK_DAMAGE_BONUS = new IConfigKey<>("ore.arms.attack_damage_bonus", AllNBTCell.INT, () -> 10);

        /***
         * 附魔等级
         */
        public static final IConfigKey<Integer> ENCHANTMENT_VALUE = new IConfigKey<>("ore.arms.enchantment_value", AllNBTCell.INT, () -> 23);

        /***
         * 一个方块的tag表示当前层级可以挖掘的方块草
         */
        public static final IConfigKey<TagKey<Block>> TAG = new IConfigKey<>("ore.arms.tag", AllNBTCell.BLOCK_TAG, null);

        /***
         * 修复工具
         */
        public static final IConfigKey<List<TagKey<Item>>> REPAIR_ITEM = new IConfigKey<>("ore.item.repair_item", AllNBTCell.ITEM_TAG.getListNBTCell(), null);

        /***
         * 基础灵气
         * 如果为0物品将没有灵气处理的能力
         */
        public static final IConfigKey<Long> MANA_BASICS = new IConfigKey<>("ore.arms.mana_basics", AllNBTCell.LONG, () -> 0L);

        /***
         * 流速基础
         */
        public static final IConfigKey<Long> RATE_BASICS = new IConfigKey<>("ore.arms.rate_basics", AllNBTCell.LONG, () -> 0L);

        /***
         * 默认技能
         */
        public static final IConfigKey<Map<Skill, Integer>> DEFAULT_SKILL = new IConfigKey<>("ore.arms.default_skill", AllNBTCell.SKILL_INT_MAP, Map::of);
    }
}
