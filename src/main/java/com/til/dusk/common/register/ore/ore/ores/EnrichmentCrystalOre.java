package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * @author til
 */
public class EnrichmentCrystalOre extends Ore {
    public EnrichmentCrystalOre() {
        super("enrichment_crystal");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "富集西伯利亚蓝色晶体");
        lang.add(LangType.EN_CH, "Enrichment Crystal");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(Ore.COLOR, new DuskColor(235, 225, 125))
                .setConfigOfV(Ore.MANA_LEVEL, ManaLevel.t1)
                .setConfig(Ore.IS_CRYSTA)
                .setConfig(Ore.HAS_DUST)
                .setConfigOfV(Ore.IS_LEVEL_ACCEPTABLE, ManaLevel.t2)
                .setConfig(DecorateBlockConfig.DECORATE_BLOCK_CONFIG, ConfigMap::new)
                .setConfig(FluidConfig.FLUID_CONFIG, ConfigMap::new)
                .setConfig(Ore.RELEVANT_SHAPED, () -> List.of(
                        new ShapedOre(ResourceLocationUtil.fuseName(this, OreItem.dust), ShapedType.blend, ShapedDrive.get(0), this.getConfig(Ore.MANA_LEVEL))
                                .addInItem(crystal.get(OreItem.dust).itemTag(), 1)
                                .addInItem(goldenrod.get(OreItem.dust).itemTag(), 1)
                                .addInItem(cotinusCoggygria.get(OreItem.dust).itemTag(), 1)
                                .addOutItem(new ItemStack(this.get(OreItem.dust).item(), 3), 1d)
                                .addMultipleSurplusTime((long) (751L * this.getConfig(Ore.STRENGTH)))
                                .addMultipleConsumeMana((long) (15L * this.getConfig(Ore.CONSUME)))));

    }

}

