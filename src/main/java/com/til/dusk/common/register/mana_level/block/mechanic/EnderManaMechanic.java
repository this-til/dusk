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
public class EnderManaMechanic extends ExtractManaMechanic {

    public EnderManaMechanic(){
        super("ender_mana", () -> Set.of(ShapedType.enderMana), new DuskColor(96, 22, 96));
        setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.get(extractMana).blockItemTag(), 1))
                .addInItem(Tags.Items.ENDER_PEARLS, 8)
                .addInItem(ItemTag.ENDER_EYE, 8));

    }

}
