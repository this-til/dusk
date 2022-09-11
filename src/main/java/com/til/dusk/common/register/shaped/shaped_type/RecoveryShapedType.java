package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;

/**
 * @author til
 */
public class RecoveryShapedType extends ShapedType{

    public RecoveryShapedType(){
        super("recovery", () ->ManaLevelBlock.recovery);
    }

    @Override
    public void registerSubsidiaryBlack() {

    }
}
