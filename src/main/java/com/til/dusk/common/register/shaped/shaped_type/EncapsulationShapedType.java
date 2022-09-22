package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;

/**
 * @author til
 */
public class EncapsulationShapedType extends ShapedType {

    public EncapsulationShapedType() {
        super("encapsulation", () -> ManaLevelBlock.encapsulation);
    }

    @Override
    public void registerShaped() {

    }
}
