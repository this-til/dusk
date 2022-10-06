package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class StarIronOre extends Ore {
    public StarIronOre() {
        super("star_iron");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "星云铁");
        lang.add(LangType.EN_CH, "Star Iron");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(OreConfig.COLOR, new DuskColor(177, 176, 192))
                .setConfigOfV(OreConfig.MANA_LEVEL, ManaLevel.t2)
                .setConfig(OreConfig.IS_METAL)
                .setConfig(OreConfig.HAS_DUST)
                .setConfigOfV(OreConfig.IS_LEVEL_ACCEPTABLE, ManaLevel.t2)
                .setConfig(OreConfig.DecorateBlockConfig.DECORATE_BLOCK_CONFIG, ConfigMap::new)
                .setConfig(OreConfig.FluidConfig.FLUID_CONFIG, ConfigMap::new)
                .setConfig(OreConfig.RELEVANT_SHAPED, () -> List.of(
                        new ShapedOre(ResourceLocationUtil.fuseName(this, OreItem.dust), ShapedType.blend, ShapedDrive.get(0), this.getConfig(OreConfig.MANA_LEVEL))
                                .addInItem(_void.get(OreItem.dust).itemTag(), 1)
                                .addInItem(darkGreen.get(OreItem.dust).itemTag(), 1)
                                .addOutItem(new ItemStack(this.get(OreItem.dust).item(), 2), 1D)
                                .addMultipleSurplusTime((long) (2048L * this.getConfig(OreConfig.STRENGTH)))
                                .addMultipleConsumeMana((long) (32L * this.getConfig(OreConfig.CONSUME)))));
    }

}

