package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

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
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(OreConfig.COLOR, new DuskColor(199, 107, 87 ))
                .setConfigOfV(OreConfig.MANA_LEVEL, ManaLevel.t4)
                .setConfig(OreConfig.FluidConfig.FLUID_CONFIG, ConfigMap::new)
                .setConfig(OreConfig.RELEVANT_SHAPED, () -> List.of(
                        new ShapedOre(ResourceLocationUtil.fuseName(this, OreFluid.solution), ShapedType.highPressureFuse, ShapedDrive.get(0), this.getConfig(OreConfig.MANA_LEVEL))
                                .addInFluid(nutrient.get(OreFluid.solution).fluidTag(), 1024)
                                .addInItem(ItemTag.SUGAR, 5)
                                .addInItem(clove.get(OreItem.dust).itemTag(), 5)
                                .addInItem(lotusRoot.get(OreItem.dust).itemTag(), 5)
                                .addOutFluid(new FluidStack(culture.get(OreFluid.solution).source(), 128), 1D)
                                .addMultipleSurplusTime((long) (8192L * this.getConfig(OreConfig.STRENGTH)))
                                .addMultipleConsumeMana((long) (18L * this.getConfig(OreConfig.CONSUME)))));
    }

}

