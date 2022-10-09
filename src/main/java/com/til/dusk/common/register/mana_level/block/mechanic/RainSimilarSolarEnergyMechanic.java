package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.SimilarSolarEnergyMechanic;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
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
    public void defaultConfig() {
        solarEnergyColor = ColorPrefab.RAIN_COLOR;
        productionMultiple = 4;
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(extractMana.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ItemIn(() ->Ore.rain.get(OreItem.perfectCrystal).itemTag(), 4)));
    }
}
