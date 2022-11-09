package com.til.dusk.common.register.ore.ore.fluid.si_xiang;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.fluid.OreFluid;
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
public class SaoYin extends SiXiangBasicsOre {

    public SaoYin() {
        super("sao_yin");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        for (LangType value : LangType.values()) {
            lang.add(value, "少阴");
        }
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(125, 125, 125);
        manaLevel = ManaLevel.t1;
        fluidData = new FluidData();
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(name, ShapedType.mk2, ShapedDrive.get(1), manaLevel)
                        .addInFluid(Ore.li.get(OreFluid.solution).fluidTag(), 1)
                        .addInFluid(Ore.zhen.get(OreFluid.solution).fluidTag(), 1)
                        .addOutFluid(new FluidStack(get(OreFluid.solution).source(), 2), 0.5D)
                        .addMultipleSurplusTime(8194)
                        .addMultipleConsumeMana(512)
                        .addMultipleOutMana(7264747)
        ));
    }
}
