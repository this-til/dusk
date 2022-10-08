package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.common.register.skill.Skill;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class StarRiverOre extends Ore {
    public StarRiverOre() {
        super("star_river");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "星河合金");
        lang.add(LangType.EN_CH, "Star River");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(Ore.COLOR, new DuskColor(100, 135, 255))
                .setConfigOfV(Ore.MANA_LEVEL, ManaLevel.t2)
                .setConfig(Ore.IS_METAL)
                .setConfig(Ore.HAS_DUST)
                .setConfigOfV(Ore.IS_LEVEL_ACCEPTABLE, ManaLevel.t3)
                .setConfig(DecorateBlockConfig.DECORATE_BLOCK_CONFIG, ConfigMap::new)
                .setConfig(FluidConfig.FLUID_CONFIG, ConfigMap::new)
                .setConfig(ArmorConfig.ARMOR_CONFIG, () -> new ConfigMap()
                        .setConfigOfV(ArmorConfig.DEFENSE, ArmorConfig.ofDefense(4))
                        .setConfigOfV(ArmorConfig.DURABILITY, ArmorConfig.ofDurability(4))
                        .setConfigOfV(ArmorConfig.MANA_BASICS, 12800000L)
                        .setConfigOfV(ArmorConfig.RATE_BASICS, 102400L)
                        .setConfigOfV(ArmorConfig.DEFAULT_SKILL, Map.of(Skill.life, 2)))
                .setConfig(ArmsConfig.ARMS_CONFIG, () -> new ConfigMap()
                        .setConfigOfV(ArmsConfig.MANA_BASICS, 12800000L)
                        .setConfigOfV(ArmsConfig.RATE_BASICS, 102400L)
                        .setConfigOfV(ArmsConfig.USES, 3200)
                        .setConfigOfV(ArmsConfig.SPEED, -3.4F)
                        .setConfigOfV(ArmsConfig.ATTACK_DAMAGE_BONUS, 16)
                        .setConfigOfV(ArmsConfig.ENCHANTMENT_VALUE, 32)
                        .setConfigOfV(ArmsConfig.REPAIR_ITEM, List.of(
                                this.get(OreItem.ingot).itemTag()))
                        .setConfigOfV(ArmsConfig.TAG, Dusk.instance.BLOCK_TAG.createTagKey(new ResourceLocation(name.getNamespace(), "tier." + name.getPath()))))
                .setConfig(ToolDataConfig.TOOL_DATA_CONFIG, () -> new ConfigMap()
                        .setConfigOfV(ToolDataConfig.USES, 64 * 22)
                        .setConfigOfV(ToolDataConfig.TANK_MAX, 4000 * 22))
                .setConfig(Ore.RELEVANT_SHAPED, () -> List.of(
                        new ShapedOre(ResourceLocationUtil.fuseName(this, OreFluid.solution), ShapedType.highPressureFuse, ShapedDrive.get(0), this.getConfig(Ore.MANA_LEVEL))
                                .addInFluid(starSilver.get(OreFluid.solution).fluidTag(), 32)
                                .addInFluid(starIron.get(OreFluid.solution).fluidTag(), 32)
                                .addInFluid(starGold.get(OreFluid.solution).fluidTag(), 32)
                                .addInFluid(elementAir.get(OreFluid.solution).fluidTag(), 256)
                                .addOutFluid(new FluidStack(starRiver.get(OreFluid.solution).source(), 32 * 3), 1D)
                                .addMultipleSurplusTime((long) (this.getConfig(Ore.STRENGTH) * 2048L))
                                .addMultipleConsumeMana((long) (this.getConfig(Ore.CONSUME) * 42L))
                ));
    }

}

