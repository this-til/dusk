package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;

/**
 * @author til
 */
public class UUGenerateShapedType extends ShapedType{

    public UUGenerateShapedType(){
        super("uu_generate", () -> ManaLevelBlock.uuGenerate);
    }

    @Override
    public void registerShaped() {

    }
}
