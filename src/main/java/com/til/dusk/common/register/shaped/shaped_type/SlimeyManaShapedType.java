package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;

/**
 * @author til
 */
public class SlimeyManaShapedType extends ShapedType{

    public SlimeyManaShapedType(){
        super("slimey_mana", () -> ManaLevelBlock.slimeyMana);
    }

    @Override
    public void registerSubsidiaryBlack() {

    }
}
