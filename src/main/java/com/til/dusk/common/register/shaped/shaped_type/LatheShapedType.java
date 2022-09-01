package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;

/**
 * @author til
 */
public class LatheShapedType extends ShapedType {
    public LatheShapedType() {
        super("lathe", () -> ManaLevelBlock.lathe);
    }

    @Override
    public void registerSubsidiaryBlack() {

    }
}
