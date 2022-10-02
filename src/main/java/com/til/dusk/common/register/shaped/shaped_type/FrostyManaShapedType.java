package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/***
 * @author til
 */
public class FrostyManaShapedType extends ShapedType {

    public FrostyManaShapedType() {
        super("frosty_mana", () -> ManaLevelBlock.frostyMana);
    }

    @Override
    public void registerShaped() {
        new ShapedOre(this, ShapedDrive.get(0), ManaLevel.t1)
                .addInItem(ItemTag.ICES.d1(), 1)
                .addMultipleSurplusTime(128)
                .addMultipleOutMana(512);
        new ShapedOre(this, ShapedDrive.get(1), ManaLevel.t1)
                .addInItem(ItemTag.SNOW_BLOCK.d1(), 1)
                .addMultipleSurplusTime(128)
                .addMultipleOutMana(256);
        new ShapedOre(this, ShapedDrive.get(2), ManaLevel.t1)
                .addInItem(ItemTag.SNOWBALL, 1)
                .addMultipleSurplusTime(128)
                .addMultipleOutMana(256);
        new ShapedOre(this, ShapedDrive.get(3), ManaLevel.t1)
                .addInItem(ItemTag.POWDER_SNOW_BUCKET, 1)
                .addOutItem(new ItemStack(Items.BUCKET), 1d)
                .addMultipleSurplusTime(128)
                .addMultipleOutMana(8196);
    }
}
