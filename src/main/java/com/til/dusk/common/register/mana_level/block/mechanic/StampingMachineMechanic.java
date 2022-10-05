package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.Set;

/**
 * @author til
 */
public class StampingMachineMechanic extends HandleMechanic {
    public StampingMachineMechanic(){
        super("stamping_machine", () -> Set.of(ShapedType.stampingMachine));
        setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.get(frameBasic).blockItemTag(), 1))
                .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreItem.hammer).itemTagKey(), 1))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.instructions.getTag(m), 1))
                .addRun((s, m) -> s.addInItem(ItemTag.ANVIL.d1(), 1)));
    }
}
