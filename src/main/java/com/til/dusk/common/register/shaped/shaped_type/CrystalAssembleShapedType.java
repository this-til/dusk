package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;

public class CrystalAssembleShapedType extends ShapedType {

    public CrystalAssembleShapedType() {
        super("crystal_assemble", () -> ManaLevelBlock.crystalAssemble);
    }

    @Override
    public void registerShaped() {

    }
}
