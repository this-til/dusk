package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;

/**
 * @author til
 */
public class CellCultureShapedType extends ShapedType{

    public CellCultureShapedType(){
        super("cell_culture", () -> ManaLevelBlock.cellCulture);
    }
    @Override
    public void registerShaped() {

    }
}
