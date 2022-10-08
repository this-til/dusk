package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
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
public class StemCellOre extends Ore {
    public StemCellOre() {
        super("stem_cell");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "干细胞");
        lang.add(LangType.EN_CH, "Stem Cell");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(209, 149, 182);
        manaLevel = ManaLevel.t4;
        fluidData = new FluidData();
        relevantShaped = new Delayed<>(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(this, OreFluid.solution), ShapedType.stemCellExtract, ShapedDrive.get(0), manaLevel)
                        .addInFluid(nutrient.get(OreFluid.solution).fluidTag(), 1024)
                        .addInItem(ItemTag.SUGAR, 5)
                        .addInItem(clove.get(OreItem.dust).itemTag(), 5)
                        .addInItem(lotusRoot.get(OreItem.dust).itemTag(), 5)
                        .addOutFluid(new FluidStack(culture.get(OreFluid.solution).source(), 128), 1D)
                        .addMultipleSurplusTime((long) (512L * strength))
                        .addMultipleConsumeMana((long) (64L * consume)),
                new ShapedOre(ResourceLocationUtil.fuseName(this, OreFluid.solution), ShapedType.cellCulture, ShapedDrive.get(0), manaLevel)
                        .addInFluid(stemCell.get(OreFluid.solution).fluidTag(), 1)
                        .addInFluid(this.get(OreFluid.solution).fluidTag(), 128)
                        .addOutFluid(new FluidStack(this.get(OreFluid.solution).source(), 1), 1d)
                        .addMultipleSurplusTime((long) (1024L * strength))
                        .addMultipleConsumeMana((long) (64L * consume))));
    }

}

