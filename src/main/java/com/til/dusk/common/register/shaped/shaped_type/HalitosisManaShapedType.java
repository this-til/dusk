package com.til.dusk.common.register.shaped.shaped_type;


import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;

public class HalitosisManaShapedType extends ShapedType{

    public HalitosisManaShapedType(){
        super("halitosis_mana", () ->ManaLevelBlock.halitosisMana);
    }

    @Override
    public void registerSubsidiaryBlack() {

    }
}
