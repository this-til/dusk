package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.Set;

/**
 * @author til
 */
public class LatheMechanic extends HandleMechanic {

    public LatheMechanic(){
        super("lathe", () -> Set.of(ShapedType.lathe));
        setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.get(frameBasic).blockItemTag(), 1))
                .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreItem.file).itemTagKey(), 3))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.power.getTag(m), 3))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.instructions.getTag(m), 3))
                .addRun((s, m) -> s.addInItem(ItemTag.ANVIL.d1(), 1)));
    }

}
