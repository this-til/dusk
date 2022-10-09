package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.shapeds.Shaped;

import java.util.function.Consumer;

/**
 * @author til
 */
public class StemCellExtractShapedType extends ShapedType{
    public StemCellExtractShapedType(){
        super("stem_cell_extract", () -> ManaLevelBlock.stemCellExtract);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {

    }
}
