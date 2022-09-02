package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;

import java.util.Map;

/***
 * @author til
 */
public class FrostyManaShapedType extends ShapedType {

    public FrostyManaShapedType() {
        super("frosty_mana", () -> ManaLevelBlock.frostyMana);
    }

    @Override
    public void registerSubsidiaryBlack() {
        new ShapedOre(
                this,
                ShapedDrive.get(0),
                ManaLevel.t1,
                Map.of(ItemTag.ICE.d1(), 1),
                null,
                128,
                0,
                512,
                null,
                null);
        new ShapedOre(
                this,
                ShapedDrive.get(1),
                ManaLevel.t1,
                Map.of(ItemTag.PACKED_ICE.d1(), 1),
                null,
                128,
                0,
                512,
                null,
                null);
        new ShapedOre(
                this,
                ShapedDrive.get(2),
                ManaLevel.t1,
                Map.of(ItemTag.SNOW_BLOCK.d1(), 1),
                null,
                128,
                0,
                256,
                null,
                null);
        new ShapedOre(
                this,
                ShapedDrive.get(3),
                ManaLevel.t1,
                Map.of(ItemTag.SNOWBALL, 1),
                null,
                128,
                0,
                256,
                null,
                null);
        new ShapedOre(
                this,
                ShapedDrive.get(4),
                ManaLevel.t1,
                Map.of(ItemTag.POWDER_SNOW_BUCKET, 1),
                null,
                128,
                0,
                8192,
                null,
                null);
    }
}
