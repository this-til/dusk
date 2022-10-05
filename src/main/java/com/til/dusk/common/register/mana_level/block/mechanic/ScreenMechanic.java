package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.Set;

/**
 * @author til
 */
public class ScreenMechanic extends HandleMechanic {

    public ScreenMechanic(){
        super("screen", () -> Set.of(ShapedType.screen));
        setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.get(frameBasic).blockItemTag(), 1))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.destruction.getTag(m), 1))
                .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreItem.rotor).itemTagKey(), 2))
                .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreItem.gear).itemTagKey(), 4))
                .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreItem.string).itemTagKey(), 64)));
    }

}
