package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;

public class CuttingShapedType extends ShapedType{

    public CuttingShapedType() {
        super("cutting", () ->ManaLevelBlock.cutting);
    }

    @Override
    public void registerSubsidiaryBlack() {

    }
}
