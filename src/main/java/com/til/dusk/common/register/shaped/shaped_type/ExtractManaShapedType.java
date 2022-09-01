package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

/**
 * @author til
 */
public class ExtractManaShapedType extends ShapedType {

    public ExtractManaShapedType() {
        super("extract_mana", () -> ManaLevelBlock.extractMana);
    }

    @Override
    public void registerSubsidiaryBlack() {

    }
}
