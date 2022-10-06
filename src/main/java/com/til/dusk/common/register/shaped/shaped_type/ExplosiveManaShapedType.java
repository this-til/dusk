package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraftforge.common.Tags;


/**
 * @author til
 */
public class ExplosiveManaShapedType extends ShapedType {
    public ExplosiveManaShapedType() {
        super("explosive_mana", () -> ManaLevelBlock.explosiveMana);
    }

    @Override
    public void registerShaped() {
        new ShapedOre(this, ShapedDrive.get(0), ManaLevel.t1)
                .addInItem(Tags.Items.GUNPOWDER, 1)
                .addMultipleSurplusTime(512)
                .addMultipleOutMana(4096);
        new ShapedOre(this, ShapedDrive.get(1), ManaLevel.t1)
                .addInItem(ItemTag.TNT.d1(), 1)
                .addMultipleSurplusTime(4096)
                .addMultipleOutMana(25000);
    }
}
