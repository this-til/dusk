package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.common.register.skill.Skill;
import com.til.dusk.util.DuskColor;
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
                .setConfigOfV(OreConfig.COLOR, new DuskColor(100, 135, 255))
                .setConfigOfV(OreConfig.MANA_LEVEL, ManaLevel.t2)
                .setConfig(OreConfig.IS_METAL)
                .setConfig(OreConfig.HAS_DUST)
                .setConfigOfV(OreConfig.IS_LEVEL_ACCEPTABLE, ManaLevel.t3)
                .setConfig(OreConfig.DecorateBlockConfig.DECORATE_BLOCK_CONFIG, ConfigMap::new)
                .setConfig(OreConfig.FluidConfig.FLUID_CONFIG, ConfigMap::new)
                .setConfig(OreConfig.ArmorConfig.ARMOR_CONFIG, () -> new ConfigMap()
                        .setConfigOfV(OreConfig.ArmorConfig.DEFENSE, OreConfig.ArmorConfig.ofDefense(4))
                        .setConfigOfV(OreConfig.ArmorConfig.DURABILITY, OreConfig.ArmorConfig.ofDurability(4))
                        .setConfigOfV(OreConfig.ArmorConfig.MANA_BASICS, 12800000L)
                        .setConfigOfV(OreConfig.ArmorConfig.RATE_BASICS, 102400L)
                        .setConfigOfV(OreConfig.ArmorConfig.DEFAULT_SKILL, Map.of(Skill.life, 2)))
                .setConfig(OreConfig.ArmsConfig.ARMS_CONFIG, () -> new ConfigMap()
                        .setConfigOfV(OreConfig.ArmsConfig.MANA_BASICS, 12800000L)
                        .setConfigOfV(OreConfig.ArmsConfig.RATE_BASICS, 102400L))
                .setConfig(OreConfig.ToolDataConfig.TOOL_DATA_CONFIG, () -> new ConfigMap()
                        .setConfigOfV(OreConfig.ToolDataConfig.USES, 64 * 22)
                        .setConfigOfV(OreConfig.ToolDataConfig.TANK_MAX, 4000 * 22))
                .setConfig(OreConfig.RELEVANT_SHAPED, () -> List.of(
                        new ShapedOre(fuseName(this, OreFluid.solution), ShapedType.highPressureFuse, ShapedDrive.get(0), this.getConfig(OreConfig.MANA_LEVEL))
                                .addInFluid(starSilver.get(OreFluid.solution).fluidTag(), 32)
                                .addInFluid(starIron.get(OreFluid.solution).fluidTag(), 32)
                                .addInFluid(starGold.get(OreFluid.solution).fluidTag(), 32)
                                .addInFluid(elementAir.get(OreFluid.solution).fluidTag(), 256)
                                .addOutFluid(new FluidStack(starRiver.get(OreFluid.solution).source(), 32 * 3), 1D)
                                .addMultipleSurplusTime((long) (this.getConfig(OreConfig.STRENGTH) * 2048L))
                                .addMultipleConsumeMana((long) (this.getConfig(OreConfig.CONSUME) * 42L))
                ));
    }

}

