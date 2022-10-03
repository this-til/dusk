package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class HighPressureFuseMechanic extends HandleMechanic {

    public HighPressureFuseMechanic() {
        super("high_pressure_fuse", () -> Set.of(ShapedType.highPressureFuse));
        setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.blockMap.get(blastFurnace).blockItemTag(), 1))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.power.getTag(m), 1))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.forming.getTag(m), 1))
                .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreBlock.coil).itemTagKey(), 3))
                .addInItem(ItemTag.CAULDRON.d1(), 1));
    }

}
