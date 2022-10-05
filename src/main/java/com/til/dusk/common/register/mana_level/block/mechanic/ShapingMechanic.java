package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.Set;

/**
 * @author til
 */
public class ShapingMechanic extends HandleMechanic {
    public ShapingMechanic(){
        super("shaping", () -> Set.of(ShapedType.forming));
        setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.get(assemble).blockItemTag(), 1))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.instructions.getTag(m), 1))
                .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreItem.hammer).itemTagKey(), 1)));
    }
}
