package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraftforge.common.Tags;

import java.util.Map;


/**
 * @author til
 */
public class ExplosiveManaShapedType extends ShapedType {
    public ExplosiveManaShapedType() {
        super("explosive_mana", () -> ManaLevelBlock.explosiveMana);
    }

    @Override
    public void registerShaped() {
        new ShapedOre(
                this,
                ShapedDrive.get(0),
                ManaLevel.t1,
                Map.of(Tags.Items.GUNPOWDER, 1),
                null,
                512,
                0,
                4096,
                null,
                null);
        new ShapedOre(
                this,
                ShapedDrive.get(1),
                ManaLevel.t1,
                Map.of(ItemTag.TNT.d1(), 1),
                null,
                4096,
                0,
                25000,
                null,
                null);
    }
}
