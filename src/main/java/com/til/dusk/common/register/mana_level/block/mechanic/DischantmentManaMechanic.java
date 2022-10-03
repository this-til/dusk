package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.block.ExtractManaMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.DuskColor;

import java.util.List;

/**
 * @author til
 */
public class DischantmentManaMechanic extends ExtractManaMechanic {
    public DischantmentManaMechanic() {
        super("dischantment_mana", () -> List.of(ShapedType.dischantmentMana), new DuskColor(135, 60, 168, 255));
        setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                .addInItem(ItemTag.ENCHANTING_TABLE.d1(), 1)
                .addInItem(ItemTag.ENCHANTING_BOOK, 3));
    }
}
