package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.Set;

/**
 * @author til
 */
public class BlastFurnaceMechanic extends HandleMechanic {
    public BlastFurnaceMechanic(){
        super("blast_furnace", () -> Set.of(ShapedType.blastFurnace));
        setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.get(furnace).blockItemTag(), 1))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.forming.getTag(m), 1))
                .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreBlock.coil).itemTagKey(), 2)));
    }
}
