package com.til.dusk.common.register.mana_level.block.mechanic.core;

import com.til.dusk.common.register.mana_level.block.MKBasicsMechanic;
import com.til.dusk.common.register.multiblock.MultiBlock;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;

/**
 * @author til
 */
public class MK3MultiBlockMechanic extends MKBasicsMechanic {
    public MK3MultiBlockMechanic() {
        super("mk3");
    }

    @Override
    public void defaultConfig() {
        super.defaultConfig();
        shapedTypeList = List.of(ShapedType.mk3, ShapedType.mk2, ShapedType.mk1);
        multiBlock = MultiBlock.mk3;
    }
}
