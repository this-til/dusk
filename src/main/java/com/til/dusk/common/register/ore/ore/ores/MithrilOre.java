package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.common.register.skill.Skill;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;
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
                .setConfigOfV(Ore.COLOR, new DuskColor(205, 209, 229))
                .setConfigOfV(Ore.MANA_LEVEL, ManaLevel.t1)
                .setConfig(Ore.IS_METAL)
                .setConfig(Ore.HAS_DUST)
                .setConfigOfV(Ore.IS_LEVEL_ACCEPTABLE, ManaLevel.t2)
                .setConfig(DecorateBlockConfig.DECORATE_BLOCK_CONFIG, ConfigMap::new)
                .setConfig(FluidConfig.FLUID_CONFIG, ConfigMap::new)
                .setConfig(ArmorConfig.ARMOR_CONFIG, () -> new ConfigMap()
                        .setConfigOfV(ArmorConfig.DEFENSE, ArmorConfig.ofDefense(3))
                        .setConfigOfV(ArmorConfig.DURABILITY, ArmorConfig.ofDurability(10))
                        .setConfigOfV(ArmorConfig.MANA_BASICS, 3200000L)
                        .setConfigOfV(ArmorConfig.RATE_BASICS, 12800L)
                        .setConfigOfV(ArmorConfig.DEFAULT_SKILL, Map.of(Skill.life, 1)))
                .setConfig(ArmsConfig.ARMS_CONFIG, () -> new ConfigMap()
                        .setConfigOfV(ArmsConfig.MANA_BASICS, 3200000L)
                        .setConfigOfV(ArmsConfig.RATE_BASICS, 12800L)
                        .setConfigOfV(ArmsConfig.REPAIR_ITEM, List.of(
                                this.get(OreItem.ingot).itemTag()))
                        .setConfigOfV(ArmsConfig.TAG, Dusk.instance.BLOCK_TAG.createTagKey(new ResourceLocation(name.getNamespace(), "tier." + name.getPath()))))
                .setConfig(ToolDataConfig.TOOL_DATA_CONFIG, () -> new ConfigMap()
                        .setConfigOfV(ToolDataConfig.USES, 64 * 16)
                        .setConfigOfV(ToolDataConfig.TANK_MAX, 4000 * 16))
                .setConfig(Ore.RELEVANT_SHAPED, () -> List.of(new ShapedOre(ResourceLocationUtil.fuseName(this, OreItem.dust), ShapedType.blend, ShapedDrive.get(0), this.getConfig(Ore.MANA_LEVEL))
                        .addInItem(spiritSilver.get(OreItem.dust).itemTag(), 1)
                        .addInItem(greenTeal.get(OreItem.dust).itemTag(), 1)
                        .addInItem(mediumspringgreen.get(OreItem.dust).itemTag(), 1)
                        .addOutItem(new ItemStack(this.get(OreItem.dust).item(), 3), 1D)
                        .addMultipleSurplusTime((long) (this.getConfig(Ore.STRENGTH) * 1024L))
                        .addMultipleConsumeMana((long) (this.getConfig(Ore.CONSUME) * 27L))));
    }
}

