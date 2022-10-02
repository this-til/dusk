package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;

/**
 * @author til
 */
public class LatheShapedType extends ShapedType {
    public LatheShapedType() {
        super("lathe", () -> ManaLevelBlock.lathe);
    }

    @Override
    public void registerShaped() {

    }
}
