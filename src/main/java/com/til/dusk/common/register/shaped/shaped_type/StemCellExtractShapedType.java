package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;

/**
 * @author til
 */
public class StemCellExtractShapedType extends ShapedType{
    public StemCellExtractShapedType(){
        super("stem_cell_extract", () -> ManaLevelBlock.stemCellExtract);
    }

    @Override
    public void registerShaped() {

    }
}
