package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class PressureStickMechanic extends HandleMechanic {

    public PressureStickMechanic(){
        super("pressure_stick", () -> Set.of(ShapedType.pressureStick));
        setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.blockMap.get(frameBasic).blockItemTag(), 1))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.forming.getTag(m), 1))
                .addRun((s, m) -> s.addInItem(ItemTag.ANVIL.d1(), 1))
                .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreItem.gear).itemTagKey(), 4)));
    }

}
