package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
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
public class CoolantOre extends Ore {
    public CoolantOre() {
        super("coolant");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "冷却液");
        lang.add(LangType.EN_CH, "CoolantOre");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(164	,200	,229);
        manaLevel = ManaLevel.t2;
        fluidData = new FluidData();
        relevantShaped = new Delayed<>(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(this, OreFluid.solution), ShapedType.highPressureFuse, ShapedDrive.get(0),manaLevel)
                        .addInItem(spiritSilver.get(OreItem.dust).itemTag(), 1)
                        .addInFluid(ultramarine.get(OreFluid.solution).fluidTag(), 144)
                        .addOutFluid(new FluidStack(this.get(OreFluid.solution).source(), 144), 1d)
                        .addMultipleSurplusTime((long) (1024L * strength))
                        .addMultipleConsumeMana((long) (18L * consume))));
    }

}

