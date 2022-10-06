package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;

/**
 * @author til
 */
public class SlimeyManaShapedType extends ShapedType {

    public SlimeyManaShapedType() {
        super("slimey_mana", () -> ManaLevelBlock.slimeyMana);
    }

    @Override
    public void registerShaped() {
        new ShapedOre(this, ShapedDrive.get(0), ManaLevel.t1)
                .addInItem(ItemTag.SLIME_BALL, 1)
                .addMultipleSurplusTime(512)
                .addMultipleOutMana(2048);
        new ShapedOre(this, ShapedDrive.get(1), ManaLevel.t1)
                .addInItem(ItemTag.SLIME_BLOCK.d1(), 1)
                .addMultipleSurplusTime(512)
                .addMultipleOutMana(2048);
    }
}
