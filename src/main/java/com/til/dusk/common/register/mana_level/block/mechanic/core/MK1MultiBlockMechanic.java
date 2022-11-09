package com.til.dusk.common.register.mana_level.block.mechanic.core;

import com.til.dusk.common.register.mana_level.block.MKBasicsMechanic;
import com.til.dusk.common.register.multiblock.MultiBlock;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author til
 */
public class MK1MultiBlockMechanic extends MKBasicsMechanic {
    public MK1MultiBlockMechanic() {
        super("mk1");
    }

    @Override
    public void defaultConfig() {
        super.defaultConfig();
        shapedTypeList = List.of(ShapedType.mk1);
        multiBlock = MultiBlock.mk1;
    }
}
