package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.register.mana_level.block.ExtractManaMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.DuskColor;
import net.minecraft.tags.ItemTags;

import java.util.List;

/**
 * @author til
 */
public class BotanyManaMechanic extends ExtractManaMechanic {

    public BotanyManaMechanic() {
        super("botany_mana", () -> List.of(ShapedType.botanyMana), new DuskColor(7, 140, 0));
        setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                .addInItem(ItemTags.FLOWERS, 256));
    }

}
