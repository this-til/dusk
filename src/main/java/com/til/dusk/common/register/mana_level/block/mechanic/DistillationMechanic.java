package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;

/**
 * @author til
 */
public class DistillationMechanic extends HandleMechanic {

    public DistillationMechanic(){
        super("distillation", () -> List.of(ShapedType.distillation));
        setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.blockMap.get(blastFurnace).blockItemTag(), 1))
                .addRun((s,m) -> s.addInItem(ManaLevelItem.destruction.getTag(m), 3))
                .addInItem(ItemTag.CAULDRON.d1(), 1));
    }

}
