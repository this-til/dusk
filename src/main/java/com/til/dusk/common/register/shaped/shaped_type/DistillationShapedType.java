package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;

/**
 * @author til
 */
public class DistillationShapedType extends ShapedType{

    public DistillationShapedType(){
        super("distillation", () -> ManaLevelBlock.distillation);
    }

    @Override
    public void registerShaped() {

    }
}
