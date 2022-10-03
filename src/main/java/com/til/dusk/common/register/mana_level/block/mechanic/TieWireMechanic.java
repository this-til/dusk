package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class TieWireMechanic extends HandleMechanic {
    public TieWireMechanic(){
        super("tie_wire", () -> Set.of(ShapedType.tieWire));
        setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.blockMap.get(pressureStick).blockItemTag(), 1))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.forming.getTag(m), 1)));
    }
}
