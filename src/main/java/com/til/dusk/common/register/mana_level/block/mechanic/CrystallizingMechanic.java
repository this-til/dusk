package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;

/**
 * @author til
 */
public class CrystallizingMechanic extends HandleMechanic {
    public CrystallizingMechanic() {
        super("crystallizing", () -> List.of(ShapedType.crystallizing));
        setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.blockMap.get(pack).blockItemTag(), 1))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.forming.getTag(m), 1))
                .addInItem(ItemTag.CAULDRON.d1(), 1)
                .addRun((s, m) -> s.addInItem(OreItem.crystalSeed.getTagPack().itemTagKey(), 12)));
    }
}
