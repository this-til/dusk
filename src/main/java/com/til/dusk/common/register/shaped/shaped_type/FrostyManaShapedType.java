package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

/***
 * @author til
 */
public class FrostyManaShapedType extends ShapedType {

    public FrostyManaShapedType() {
        super("frosty_mana", () -> ManaLevelBlock.frostyMana);
    }

    @Override
    public void registerSubsidiaryBlack() {

    }
}
