package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.register.mana_level.block.SimilarSolarEnergyMechanic;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.util.prefab.ColorPrefab;

/**
 * @author til
 */
public class MoonlightSimilarSolarEnergyMechanic extends SimilarSolarEnergyMechanic {

    public MoonlightSimilarSolarEnergyMechanic() {
        super("moonlight", 1, level -> level.isNight() && !level.isRaining(), ColorPrefab.MOONLIGHT_COLOR);
        setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .setMakeLevel(ManaLevelMakeData.MakeLevel.CURRENT)
                .addRun((s, m) -> s.addInItem(m.get(extractMana).blockItemTag(), 1))
                .addInItem(Ore.moonlight.get(OreItem.perfectCrystal).itemTag(), 1));

    }

}
