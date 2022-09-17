package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;

/**
 * @author til
 */
public class HighPressureFuseShapedType extends ShapedType{

    public HighPressureFuseShapedType(){
        super("high_pressure_fuse", () ->ManaLevelBlock.highPressureFuse);
    }

    @Override
    public void registerShaped() {

    }
}
