package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class ManaCoagulationMechanic extends HandleMechanic {
    public ManaCoagulationMechanic() {
        super("mana_coagulation", () -> Set.of(ShapedType.manaCoagulation));
        setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.blockMap.get(frameBasic).blockItemTag(), 1))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.forming.getTag(m), 1))
                .addRun((s, m) -> s.addInItem(Ore.mithril.blockMap.get(OreBlock.coil).blockItemTag(), 4)));
    }
}
