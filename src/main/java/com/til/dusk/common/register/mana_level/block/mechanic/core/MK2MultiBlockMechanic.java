package com.til.dusk.common.register.mana_level.block.mechanic.core;

import com.til.dusk.common.register.mana_level.block.MKBasicsMechanic;
import com.til.dusk.common.register.multiblock.MultiBlock;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;

/**
 * @author til
 */
public class MK2MultiBlockMechanic extends MKBasicsMechanic {
    public MK2MultiBlockMechanic() {
        super("mk2");
    }

    @Override
    public void defaultConfig() {
        super.defaultConfig();
        shapedTypeList = List.of(ShapedType.mk2, ShapedType.mk1);
        multiBlock = MultiBlock.mk2;
    }
}
