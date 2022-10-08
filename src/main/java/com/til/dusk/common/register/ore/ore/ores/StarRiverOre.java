package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.DecorateBlockData;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

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
    public void defaultConfig() {
        color = new DuskColor(255, 245, 46);
        manaLevel = ManaLevel.t2;
        isMetal = true;
        hasDust = true;
        isLevelAcceptable = List.of(ManaLevel.t3);
        decorateBlockData = new DecorateBlockData();
        fluidData = new FluidData();
        relevantShaped = new Delayed<>(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(this, OreItem.dust), ShapedType.highPressureFuse, ShapedDrive.get(0), manaLevel)
                        .addInFluid(starSilver.get(OreFluid.solution).fluidTag(), 32)
                        .addInFluid(starIron.get(OreFluid.solution).fluidTag(), 32)
                        .addInFluid(starGold.get(OreFluid.solution).fluidTag(), 32)
                        .addInFluid(elementAir.get(OreFluid.solution).fluidTag(), 256)
                        .addOutFluid(new FluidStack(starRiver.get(OreFluid.solution).source(), 32 * 3), 1D)
                        .addMultipleSurplusTime((long) consume * 2048L)
                        .addMultipleConsumeMana((long) strength * 42L)));
    }

}

