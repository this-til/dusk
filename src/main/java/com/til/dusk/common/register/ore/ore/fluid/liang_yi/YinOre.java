package com.til.dusk.common.register.ore.ore.fluid.liang_yi;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.DuskColor;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

/**
 * @author til
 */
public class YinOre extends LiangYiBasicsOre {

    public YinOre() {
        super("yin");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        for (LangType value : LangType.values()) {
            lang.add(value, "阴仪");
        }
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(35, 35, 35);
        manaLevel = ManaLevel.t1;
        fluidData = new FluidData();
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(name, ShapedType.mk3, ShapedDrive.get(1), manaLevel)
                        .addInFluid(Ore.taiYin.get(OreFluid.solution).fluidTag(), 1)
                        .addInFluid(Ore.saoYang.get(OreFluid.solution).fluidTag(), 1)
                        .addOutFluid(new FluidStack(get(OreFluid.solution).source(), 2), 0.5D)
                        .addMultipleSurplusTime(81920)
                        .addMultipleConsumeMana(1024)
                        .addMultipleOutMana(167772160)
        ));
    }
}
