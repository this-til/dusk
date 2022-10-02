package com.til.dusk.common.register.shaped.shaped_type;


import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;

/**
 * @author til
 */
public class HalitosisManaShapedType extends ShapedType {

    public HalitosisManaShapedType() {
        super("halitosis_mana", () -> ManaLevelBlock.halitosisMana);
    }

    @Override
    public void registerShaped() {
        new ShapedOre(this, ShapedDrive.get(0), ManaLevel.t1)
                .addInItem(ItemTag.DRAGON_BREATH, 1)
                .addMultipleSurplusTime(2049)
                .addMultipleOutMana(65536);
    }
}
