package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.register.mana_level.block.ExtractManaMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.DuskColor;
import net.minecraft.tags.FluidTags;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class FlameManaMechanic extends ExtractManaMechanic {

    public FlameManaMechanic(){
        super("flame_mana", () -> Set.of(ShapedType.flameMana), new DuskColor(255, 0, 0));setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.blockMap.get(extractMana).blockItemTag(), 1))
                .addInFluid(FluidTags.LAVA, 32000));
    }

}
