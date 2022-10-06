package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class CatOre extends Ore {
    public CatOre() {
        super("cat");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "(>^ω^<)喵");
        lang.add(LangType.EN_CH, "CatOre");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(OreConfig.COLOR, new DuskColor(239, 218, 217))
                .setConfigOfV(OreConfig.MANA_LEVEL, ManaLevel.t2)
                .setConfig(OreConfig.IS_CRYSTA)
                .setConfig(OreConfig.HAS_DUST)
                .setConfigOfV(OreConfig.IS_LEVEL_ACCEPTABLE, ManaLevel.t2)
                .setConfig(OreConfig.DecorateBlockConfig.DECORATE_BLOCK_CONFIG, ConfigMap::new)
                .setConfig(OreConfig.FluidConfig.FLUID_CONFIG, ConfigMap::new)
                .setConfig(OreConfig.RELEVANT_SHAPED, () -> List.of(
                        new ShapedOre(ResourceLocationUtil.fuseName(this, OreFluid.solution), ShapedType.highPressureFuse, ShapedDrive.get(1), this.getConfig(OreConfig.MANA_LEVEL))
                                .addInFluid(crystal.get(OreFluid.solution).fluidTag(), 72)
                                .addInFluid(cotinusCoggygria.get(OreFluid.solution).fluidTag(), 72)
                                .addInFluid(sourceAir.get(OreFluid.solution).fluidTag(), 440)
                                .addOutFluid(new FluidStack(this.get(OreFluid.solution).source(), 144), 1D)
                                .addMultipleSurplusTime((long) (855L * this.getConfig(OreConfig.STRENGTH)))
                                .addMultipleConsumeMana((long) (17L * this.getConfig(OreConfig.CONSUME)))));
    }

}

