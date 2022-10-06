package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
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
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(OreConfig.COLOR, new DuskColor(209, 149, 182))
                .setConfigOfV(OreConfig.MANA_LEVEL, ManaLevel.t1)
                .setConfig(OreConfig.FluidConfig.FLUID_CONFIG, ConfigMap::new)
                .setConfig(OreConfig.RELEVANT_SHAPED, () -> List.of(
                        new ShapedOre(ResourceLocationUtil.fuseName(this, OreFluid.solution), ShapedType.stemCellExtract, ShapedDrive.get(0), this.getConfig(OreConfig.MANA_LEVEL))
                                .addInItem(ItemTag.CAN_EXTRACT_STEM_CELL, 1)
                                .addOutFluid(new FluidStack(this.get(OreFluid.solution).source(), 1), 0.1)
                                .addMultipleSurplusTime((long) (512L * this.getConfig(OreConfig.STRENGTH)))
                                .addMultipleConsumeMana((long) (128L * this.getConfig(OreConfig.CONSUME))),
                        new ShapedOre(ResourceLocationUtil.fuseName(this, OreFluid.solution), ShapedType.cellCulture, ShapedDrive.get(0), ManaLevel.t4)
                                .addInFluid(stemCell.get(OreFluid.solution).fluidTag(), 1)
                                .addInFluid(this.get(OreFluid.solution).fluidTag(), 128)
                                .addOutFluid(new FluidStack(this.get(OreFluid.solution).source(), 1), 1d)
                                .addMultipleSurplusTime((long) (512L * this.getConfig(OreConfig.STRENGTH)))
                                .addMultipleConsumeMana((long) (128L * this.getConfig(OreConfig.CONSUME)))));
    }

}

