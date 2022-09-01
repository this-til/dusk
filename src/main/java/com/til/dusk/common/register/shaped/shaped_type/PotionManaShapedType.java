package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;

/**
 * @author til
 */
public class PotionManaShapedType extends ShapedType{

    public PotionManaShapedType(){
        super("potion_mana", () ->ManaLevelBlock.potionMana);
    }

    @Override
    public void registerSubsidiaryBlack() {

    }
}
