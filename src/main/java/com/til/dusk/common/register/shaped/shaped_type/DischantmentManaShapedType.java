package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;

/**
 * @author til
 */
public class DischantmentManaShapedType extends ShapedType{
    public DischantmentManaShapedType(){
        super("dischantment_mana", () -> ManaLevelBlock.dischantmentMana);
    }

    @Override
    public void registerSubsidiaryBlack() {

    }
}
