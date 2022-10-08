package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.DecorateBlockData;
import com.til.dusk.common.register.ore.block.MineralBlockData;
import com.til.dusk.common.register.ore.fluid.FluidData;
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
    public void defaultConfig() {
        color = new DuskColor(240, 239, 238);
        manaLevel = ManaLevel.t2;
        isCrysta = true;
        hasDust = true;
        isLevelAcceptable = List.of(ManaLevel.t3);
        mineralBlockData = new MineralBlockData()
                .addOrePlacedFeatureConfig(new IOrePlacedFeatureConfig.GenerateData().useDefaultConfig(this, 12, 4));
        decorateBlockData = new DecorateBlockData();
        fluidData = new FluidData();
        relevantShaped = new Delayed<>(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(this, OreFluid.solution), ShapedType.highPressureFuse, ShapedDrive.get(1), manaLevel)
                        .addInFluid(cat.get(OreFluid.solution).fluidTag(), 72)
                        .addInFluid(dog.get(OreFluid.solution).fluidTag(), 72)
                        .addInFluid(natureAir.get(OreFluid.solution).fluidTag(), 450)
                        .addOutFluid(new FluidStack(this.fluidMap.get(OreFluid.solution).source(), 144), 1D)
                        .addMultipleSurplusTime((long) (1455L * strength))
                        .addMultipleConsumeMana((long) (17L * consume))));
    }

}

