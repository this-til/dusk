package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraftforge.common.Tags;

/**
 * @author til
 */
public class EnderManaShapedType extends ShapedType {

    public EnderManaShapedType() {
        super("ender_mana", () -> ManaLevelBlock.enderMana);
    }

    @Override
    public void registerShaped() {
        new ShapedOre(this, ShapedDrive.get(0), ManaLevel.t1)
                .addInItem(Tags.Items.END_STONES, 1)
                .addMultipleSurplusTime(512)
                .addMultipleOutMana(2048);
        new ShapedOre(this, ShapedDrive.get(1), ManaLevel.t1)
                .addInItem(Tags.Items.ENDER_PEARLS, 1)
                .addMultipleSurplusTime(1024)
                .addMultipleOutMana(16384);
        new ShapedOre(this, ShapedDrive.get(2), ManaLevel.t1)
                .addInItem(ItemTag.ENDER_EYE, 1)
                .addMultipleSurplusTime(1024)
                .addMultipleOutMana(16384);
    }
}
