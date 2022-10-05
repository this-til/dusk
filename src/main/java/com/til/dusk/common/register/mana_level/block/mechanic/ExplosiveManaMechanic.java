package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.block.ExtractManaMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.DuskColor;
import net.minecraftforge.common.Tags;

import java.util.Set;

/**
 * @author til
 */
public class ExplosiveManaMechanic extends ExtractManaMechanic {

    public ExplosiveManaMechanic(){
        super("explosive_mana", () -> Set.of(ShapedType.explosiveMana), new DuskColor(178, 25, 25));
        setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.get(extractMana).blockItemTag(), 1))
                .addInItem(ItemTag.TNT.d1(), 4)
                .addInItem(Tags.Items.GUNPOWDER, 16));
    }

}
