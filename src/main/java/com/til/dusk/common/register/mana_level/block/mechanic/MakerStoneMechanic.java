package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import net.minecraft.tags.FluidTags;

import java.util.List;

/**
 * @author til
 */
public class MakerStoneMechanic extends HandleMechanic {

    public MakerStoneMechanic(){
        super("maker_stone", () -> List.of(ShapedType.makerStone));
        setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.blockMap.get(frameBasic).blockItemTag(), 1))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.forming.getTag(m), 1))
                .addInFluid(FluidTags.WATER, 16000)
                .addInFluid(FluidTags.LAVA, 16000));
    }

}
