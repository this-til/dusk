package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.block.ExtractManaMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.DuskColor;

import java.util.List;

public class FoodManaMechanic extends ExtractManaMechanic {
    public FoodManaMechanic() {
        super("food_mana", () -> List.of(ShapedType.foodMana), new DuskColor(255, 184, 66));
        setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                .addInItem(ItemTag.BREAD, 64));
    }

}
