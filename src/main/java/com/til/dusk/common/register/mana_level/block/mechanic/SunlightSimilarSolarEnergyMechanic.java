package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.SimilarSolarEnergyMechanic;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.prefab.ColorPrefab;

import java.util.List;

/**
 * @author til
 */
public class SunlightSimilarSolarEnergyMechanic extends SimilarSolarEnergyMechanic {

    public SunlightSimilarSolarEnergyMechanic() {
        super("sunlight", level -> level.isDay() && !level.isRaining());
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(PRODUCTION_MULTIPLE, 1)
                .setConfigOfV(SOLAR_ENERGY_COLOR, ColorPrefab.SUNLIGHT_COLOR)
                .setConfig(MECHANIC_MAKE_DATA, () -> new ConfigMap()
                        .setConfigOfV(ManaLevelMakeDataConfig.ORE_CONFIG, List.of(
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(extractMana.name, 1),
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(Ore.moonlight.get(OreItem.perfectCrystal).itemTag(), 4))));
    }
}
