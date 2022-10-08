package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.SimilarSolarEnergyMechanic;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * @author til
 */
public class RainSimilarSolarEnergyMechanic extends SimilarSolarEnergyMechanic {

    public RainSimilarSolarEnergyMechanic() {
        super("rain", Level::isRaining);
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(PRODUCTION_MULTIPLE, 4)
                .setConfigOfV(SOLAR_ENERGY_COLOR, ColorPrefab.RAIN_COLOR)
                .setConfig(MECHANIC_MAKE_DATA, () -> new ConfigMap()
                        .setConfigOfV(ManaLevelMakeDataConfig.ORE_CONFIG, List.of(
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(extractMana.name, 1),
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(Ore.rain.get(OreItem.perfectCrystal).itemTag(), 4))));
    }

}
