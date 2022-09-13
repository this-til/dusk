package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;

/**
 * @author til
 */
public class CarvingShapedType extends ShapedType{

    public CarvingShapedType(){
        super("carving", () -> ManaLevelBlock.carving);
    }

    @Override
    public void registerShaped() {

    }
}
