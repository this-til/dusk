package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.block.ExtractManaMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.DuskColor;

import java.util.Set;

/**
 * @author til
 */
public class FrostyManaMechanic extends ExtractManaMechanic {
    public FrostyManaMechanic() {
        super("frosty_mana", () -> Set.of(ShapedType.frostyMana), new DuskColor(29, 237, 255));
        setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.get(extractMana).blockItemTag(), 1))
                .addInItem(ItemTag.ICES.d1(), 32)
                .addInItem(ItemTag.SNOW_BLOCK.d1(), 32));
    }

}
