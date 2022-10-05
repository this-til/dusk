package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.Set;

/**
 * @author til
 */
public class RecoveryMechanic extends HandleMechanic {
    public RecoveryMechanic() {
        super("recovery", () -> Set.of(ShapedType.recovery));
        setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.get(decompose).blockItemTag(), 1))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.destruction.getTag(m), 1)));
    }
}
