package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;

/**
 * @author til
 */
public class FreezingShapedType extends ShapedType{
    public FreezingShapedType(){
        super("freezing", () -> ManaLevelBlock.freezing);
    }

    @Override
    public void registerSubsidiaryBlack() {

    }
}
