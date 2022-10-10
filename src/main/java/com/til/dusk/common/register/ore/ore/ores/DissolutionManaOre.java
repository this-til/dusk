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
public class DissolutionManaOre extends Ore {
    public DissolutionManaOre() {
        super("dissolution_mana");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "灵气融液");
        lang.add(LangType.EN_CH, "Dissolution Mana");
    }


    @Override
    public void defaultConfig() {
        color = new DuskColor(242, 225, 149);
        manaLevel = ManaLevel.t3;
        fluidData = new FluidData();
        relevantShaped = new Delayed<>(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(this, OreFluid.solution), ShapedType.highPressureFuse, ShapedDrive.get(0),manaLevel)
                        .addInItem(spiritSilver.get(OreItem.dust).itemTag(), 1)
                        .addInItem(indigo.get(OreItem.dust).itemTag(), 1)
                        .addInItem(willowYellow.get(OreItem.dust).itemTag(), 1)
                        .addInFluid(mana.get(OreFluid.solution).fluidTag(), 32)
                        .addOutFluid(new FluidStack(dissolutionMana.get(OreFluid.solution).source(), 32), 1d)
                        .addMultipleSurplusTime((long) (4096L * strength))
                        .addMultipleConsumeMana((long) (18L * consume))));
    }

}

