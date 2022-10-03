package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.block.ExtractManaMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.DuskColor;

import java.util.List;

/**
 * @author til
 */
public class HalitosisManaMechanic extends ExtractManaMechanic {

    public HalitosisManaMechanic(){
        super("halitosis_mana", () -> List.of(ShapedType.halitosisMana), new DuskColor(229, 45, 136));
        setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                .addInItem(ItemTag.DRAGON_BREATH, 16));
    }

}
