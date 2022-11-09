package com.til.dusk.common.register.ore.ore.metal;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.config.util.Delayed;
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
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

/**
 * @author til
 */
public class MomentFlowerOre extends Ore {

    public MomentFlowerOre() {
        super("moment_flower");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "刹那之花");
        lang.add(LangType.EN_CH, "Moment Flower");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(193, 196, 221);
        manaLevel = ManaLevel.t3;
        isMetal = true;
        hasDust = true;
        isLevelAcceptable = List.of(ManaLevel.t3);
        decorateBlockData = new DecorateBlockData();
        fluidData = new FluidData();
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(name, ShapedType.highPressureFuse, ShapedDrive.get(0), manaLevel)
                        .addInFluid(_void.get(OreFluid.solution).fluidTag(), 144)
                        .addInFluid(idea.get(OreFluid.solution).fluidTag(), 1440)
                        .addOutFluid(new FluidStack(get(OreFluid.solution).source(), 1440 - 144), 1D)
        ));
    }
}
