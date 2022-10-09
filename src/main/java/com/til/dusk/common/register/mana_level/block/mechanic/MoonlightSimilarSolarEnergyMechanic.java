package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.SimilarSolarEnergyMechanic;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;

import java.util.List;

/**
 * @author til
 */
public class MoonlightSimilarSolarEnergyMechanic extends SimilarSolarEnergyMechanic {

    public MoonlightSimilarSolarEnergyMechanic() {
        super("moonlight", level -> level.isNight() && !level.isRaining());
    }

    @Override
    public void defaultConfig() {
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig( List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(extractMana.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ItemIn(() ->Ore.moonlight.get(OreItem.perfectCrystal).itemTag(), 4)));
    }
}
