package com.til.dusk.common.register.ore.ore;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Util;
import com.til.dusk.util.nbt.cell.AllNBTCell;

import java.util.List;

/**
 * @author til
 */
public class OreConfig {
    /***
     * 颜色
     */
    @ConfigField
    public DuskColor color;

    /***
     * 矿物处理的等级
     */
    public static final ConfigKey<ManaLevel> MANA_LEVEL = new ConfigKey<>("ore.mana_level", AllNBTCell.MANA_LEVEL, null);
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
     * 表明该灵压等级可接受物品作为通用输入
     */
    public static final ConfigKey<ManaLevel> IS_LEVEL_ACCEPTABLE = new ConfigKey<>("ore.is_level_acceptable", AllNBTCell.MANA_LEVEL, () -> ManaLevel.t1);
    /***
     * 相关配方生成
     */
    public static final ConfigKey<List<Shaped>> RELEVANT_SHAPED = new ConfigKey<>("ore.relevant_shaped", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP.getListNBTCell()), List::of);
}
