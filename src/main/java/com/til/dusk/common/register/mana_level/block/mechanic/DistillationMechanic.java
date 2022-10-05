package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.Set;

/**
 * @author til
 */
public class DistillationMechanic extends HandleMechanic {

    public DistillationMechanic() {
        super("distillation", () -> Set.of(ShapedType.distillation));
        setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.get(blastFurnace).blockItemTag(), 1))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.destruction.getTag(m), 3))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.spread.getTag(m), 1))
                .addInItem(ItemTag.CAULDRON.d1(), 1));
    }

}
