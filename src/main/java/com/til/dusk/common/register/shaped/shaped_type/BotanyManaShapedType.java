package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;

/**
 * @author til
 */
public class BotanyManaShapedType extends ShapedType{

    public BotanyManaShapedType(){
        super("botany_mana", () -> ManaLevelBlock.botanyMana);
    }

    @Override
    public void registerSubsidiaryBlack() {

    }
}
