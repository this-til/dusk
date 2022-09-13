package com.til.dusk.common.register.shaped.shaped_type;


import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class HalitosisManaShapedType extends ShapedType {

    public HalitosisManaShapedType() {
        super("halitosis_mana", () -> ManaLevelBlock.halitosisMana);
    }

    @Override
    public void registerShaped() {
        new ShapedOre(
                this,
                ShapedDrive.get(0),
                ManaLevel.t1,
                Map.of(ItemTag.DRAGON_BREATH, 1),
                null,
                2049,
                0,
                65536,
                null,
                null);
    }
}
