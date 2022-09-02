package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.common.Tags;

import java.util.Map;

/**
 * @author til
 */
public class FlameManaShapedType extends ShapedType {
    public FlameManaShapedType() {
        super("flame_mana", () -> ManaLevelBlock.flameMana);
    }

    @Override
    public void registerSubsidiaryBlack() {
        new ShapedOre(
                this,
                ShapedDrive.get(0),
                ManaLevel.t1,
                null,
                Map.of(FluidTags.WATER, 10),
                512,
                0,
                8192,
                null,
                null);
        new ShapedOre(
                this,
                ShapedDrive.get(1),
                ManaLevel.t1,
                Map.of(ItemTag.BLAZE_POWDER, 1),
                null,
                512,
                0,
                2048,
                null,
                null);
        new ShapedOre(
                this,
                ShapedDrive.get(2),
                ManaLevel.t1,
                Map.of(Tags.Items.RODS_BLAZE, 1),
                null,
                512,
                0,
                4096,
                null,
                null);

    }
}
