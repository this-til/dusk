package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.register.mana_level.block.SimilarSolarEnergyMechanic;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.util.prefab.ColorPrefab;

/**
 * @author til
 */
public class SunlightSimilarSolarEnergyMechanic extends SimilarSolarEnergyMechanic {

    public SunlightSimilarSolarEnergyMechanic() {
        super("sunlight", 1, level -> level.isDay() && !level.isRaining(), ColorPrefab.SUNLIGHT_COLOR);
        setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .setMakeLevel(ManaLevelMakeData.MakeLevel.CURRENT)
                .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                .addInItem(Ore.sunlight.itemMap.get(OreItem.perfectCrystal).itemTag(), 1));
    }
}