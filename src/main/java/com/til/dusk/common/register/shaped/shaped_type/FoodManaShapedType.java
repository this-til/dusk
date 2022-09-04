package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;

/**
 * @author til
 */
public class FoodManaShapedType extends ShapedType{

    public FoodManaShapedType(){
        super("food_mana", () -> ManaLevelBlock.foodMana);
    }

    @Override
    public void registerSubsidiaryBlack() {

    }
}
