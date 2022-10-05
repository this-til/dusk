package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.common.register.skill.Skill;
import com.til.dusk.util.DuskColor;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

/**
 * @author til
 */
public class MithrilOre extends Ore {
    public MithrilOre() {
        super("mithril");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "秘银");
        lang.add(LangType.EN_CH, "Mithril");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(OreConfig.COLOR, new DuskColor(205, 209, 229))
                .setConfigOfV(OreConfig.MANA_LEVEL, ManaLevel.t1)
                .setConfig(OreConfig.IS_METAL)
                .setConfig(OreConfig.HAS_DUST)
                .setConfigOfV(OreConfig.IS_LEVEL_ACCEPTABLE, ManaLevel.t2)
                .setConfig(OreConfig.DecorateBlockConfig.DECORATE_BLOCK_CONFIG, ConfigMap::new)
                .setConfig(OreConfig.FluidConfig.FLUID_CONFIG, ConfigMap::new)
                .setConfig(OreConfig.ArmorConfig.ARMOR_CONFIG, () -> new ConfigMap()
                        .setConfigOfV(OreConfig.ArmorConfig.DEFENSE, OreConfig.ArmorConfig.ofDefense(3))
                        .setConfigOfV(OreConfig.ArmorConfig.DURABILITY, OreConfig.ArmorConfig.ofDurability(10))
                        .setConfigOfV(OreConfig.ArmorConfig.MANA_BASICS, 3200000L)
                        .setConfigOfV(OreConfig.ArmorConfig.RATE_BASICS, 12800L)
                        .setConfigOfV(OreConfig.ArmorConfig.DEFAULT_SKILL, Map.of(Skill.life, 1)))
                .setConfig(OreConfig.ArmsConfig.ARMS_CONFIG, () -> new ConfigMap()
                        .setConfigOfV(OreConfig.ArmsConfig.MANA_BASICS, 3200000L)
                        .setConfigOfV(OreConfig.ArmsConfig.RATE_BASICS, 12800L))
                .setConfig(OreConfig.ToolDataConfig.TOOL_DATA_CONFIG, () -> new ConfigMap()
                        .setConfigOfV(OreConfig.ToolDataConfig.USES, 64 * 16)
                        .setConfigOfV(OreConfig.ToolDataConfig.TANK_MAX, 4000 * 16));
    }

    @Override
    public void registerShaped() {
        new ShapedOre(ShapedType.blend, ShapedDrive.get(0), this.getConfig(OreConfig.MANA_LEVEL))
                .addInItem(spiritSilver.get(OreItem.dust).itemTag(), 1)
                .addInItem(greenTeal.get(OreItem.dust).itemTag(), 1)
                .addInItem(mediumspringgreen.get(OreItem.dust).itemTag(), 1)
                .addOutItem(new ItemStack(this.get(OreItem.dust).item(), 3), 1D)
                .addMultipleSurplusTime((long) (this.getConfig(OreConfig.STRENGTH) * 1024L))
                .addMultipleConsumeMana((long) (this.getConfig(OreConfig.CONSUME) * 27L));
    }
}

