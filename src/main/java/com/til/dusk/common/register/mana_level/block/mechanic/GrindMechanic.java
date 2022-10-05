package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import net.minecraftforge.common.Tags;

import java.util.Set;

/**
 * @author til
 */
public class GrindMechanic extends HandleMechanic {

    public GrindMechanic() {
        super("grind", () -> Set.of(ShapedType.grind));
        setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addInItem(Tags.Items.GEMS_DIAMOND, 2)
                .addRun((s, m) -> s.addInItem(m.get(frameBasic).blockItemTag(), 1))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.destruction.getTag(m), 1))
                .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreItem.gear).itemTagKey(), 2))
                .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreItem.rotor).itemTagKey(), 1)));
    }

}
