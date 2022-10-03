package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class PackMechanic extends HandleMechanic {

    public PackMechanic(){
        super("pack", () -> Set.of(ShapedType.pack));
        setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.blockMap.get(frameBasic).blockItemTag(), 1))
                .addInItem(ItemTag.CRAFTING_TABLE.d1(), 9)
                .addRun((s, m) -> s.addInItem(ManaLevelItem.forming.getTag(m), 1)));
    }

}
