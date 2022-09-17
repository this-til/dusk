package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;

/**
 * @author til
 */
public class ExtractManaShapedType extends ShapedType {

    public ExtractManaShapedType() {
        super("extract_mana", () -> ManaLevelBlock.extractMana);
    }

    @Override
    public void registerShaped() {
        new ShapedOre(
                this,
                ShapedDrive.get(0),
                ManaLevel.t1,
                null,
                null,
                1024,
                0,
                30000,
                null,
                null
        );
    }
}
