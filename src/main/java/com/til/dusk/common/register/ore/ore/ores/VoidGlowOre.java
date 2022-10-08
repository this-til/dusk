package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class VoidGlowOre extends Ore {
    public VoidGlowOre() {
        super("void_glow");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "虚空辉光");
        lang.add(LangType.EN_CH, "Void Glow");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(Ore.COLOR, new DuskColor(240, 239, 238))
                .setConfigOfV(Ore.MANA_LEVEL, ManaLevel.t2)
                .setConfig(Ore.IS_CRYSTA)
                .setConfig(Ore.HAS_DUST)
                .setConfigOfV(Ore.IS_LEVEL_ACCEPTABLE, ManaLevel.t3)
                .setConfig(DecorateBlockConfig.DECORATE_BLOCK_CONFIG, ConfigMap::new)
                .setConfig(FluidConfig.FLUID_CONFIG, ConfigMap::new)
                .setConfig(Ore.RELEVANT_SHAPED, () -> List.of(
                        new ShapedOre(ResourceLocationUtil.fuseName(this, OreFluid.solution), ShapedType.highPressureFuse, ShapedDrive.get(1), this.getConfig(Ore.MANA_LEVEL))
                                .addInFluid(cat.get(OreFluid.solution).fluidTag(), 72)
                                .addInFluid(dog.get(OreFluid.solution).fluidTag(), 72)
                                .addInFluid(natureAir.get(OreFluid.solution).fluidTag(), 450)
                                .addOutFluid(new FluidStack(this.fluidMap.get(OreFluid.solution).source(), 144), 1D)
                                .addMultipleSurplusTime((long) (1455L * this.getConfig(Ore.STRENGTH)))
                                .addMultipleConsumeMana((long) (17L * this.getConfig(Ore.CONSUME)))));
    }

}

