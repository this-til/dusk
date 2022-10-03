package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;

/**
 * @author til
 */
public class EncapsulationMechanic extends HandleMechanic {

    public EncapsulationMechanic(){
        super("encapsulation", () -> List.of(ShapedType.encapsulation));
        setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.blockMap.get(assemble).blockItemTag(), 1))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.gather.getTag(m), 1)));
    }

}
