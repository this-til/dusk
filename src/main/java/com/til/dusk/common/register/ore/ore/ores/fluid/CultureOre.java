package com.til.dusk.common.register.ore.ore.ores.fluid;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.data.tag.ItemTag;
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
public class CultureOre extends Ore {
    public CultureOre() {
        super("culture");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "培养液");
        lang.add(LangType.EN_CH, "Culture");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(199, 107, 87);
        manaLevel = ManaLevel.t4;
        fluidData = new FluidData();
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(this, OreFluid.solution), ShapedType.blend, ShapedDrive.get(0),manaLevel)
                        .addInFluid(nutrient.get(OreFluid.solution).fluidTag(), 1024)
                        .addInItem(ItemTag.SUGAR, 5)
                        .addInItem(clove.get(OreItem.dust).itemTag(), 5)
                        .addInItem(lotusRoot.get(OreItem.dust).itemTag(), 5)
                        .addOutFluid(new FluidStack(culture.get(OreFluid.solution).source(), 128), 1D)
                        .addMultipleSurplusTime((long) (8192L * strength))
                        .addMultipleConsumeMana((long) (18L * consume))));
    }
}

