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
public class SlimeyManaShapedType extends ShapedType {

    public SlimeyManaShapedType() {
        super("slimey_mana", () -> ManaLevelBlock.slimeyMana);
    }

    @Override
    public void registerSubsidiaryBlack() {
        new ShapedOre(
                this,
                ShapedDrive.get(0),
                ManaLevel.t1,
                Map.of(ItemTag.SLIME_BALL, 1),
                null,
                512,
                0,
                2048,
                null,
                null);
        new ShapedOre(
                this,
                ShapedDrive.get(1),
                ManaLevel.t1,
                Map.of(ItemTag.SLIME_BLOCK.d1(), 1),
                null,
                512,
                0,
                8194,
                null,
                null);
/*        new ShapedOre(
                this,
                ShapedDrive.get(1),
                ManaLevel.t1,
                null,
                Map.of(Tags.Fluids.MILK, 100),
                2048,
                0,
                16384,
                null,
                null);*/
    }
}
