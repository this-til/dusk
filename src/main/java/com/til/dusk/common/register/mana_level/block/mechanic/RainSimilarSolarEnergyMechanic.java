package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.register.mana_level.block.SimilarSolarEnergyMechanic;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.world.level.Level;

/**
 * @author til
 */
public class RainSimilarSolarEnergyMechanic extends SimilarSolarEnergyMechanic {

    public RainSimilarSolarEnergyMechanic() {
        super("rain",4, Level::isRaining, ColorPrefab.RAIN_COLOR);
        setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .setMakeLevel(ManaLevelMakeData.MakeLevel.CURRENT)
                .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                .addInItem(Ore.rain.itemMap.get(OreItem.perfectCrystal).itemTag(), 1));
    }

}
