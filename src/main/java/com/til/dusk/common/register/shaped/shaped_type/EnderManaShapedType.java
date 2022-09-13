package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class EnderManaShapedType extends ShapedType {

    public EnderManaShapedType() {
        super("ender_mana", () -> ManaLevelBlock.enderMana);
    }

    @Override
    public void registerShaped() {
        new ShapedOre(
                this,
                ShapedDrive.get(0),
                ManaLevel.t1,
                Map.of(Tags.Items.END_STONES, 1),
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
                Map.of(Tags.Items.ENDER_PEARLS, 1),
                null,
                1024,
                0,
                16384,
                null,
                null);
        new ShapedOre(
                this,
                ShapedDrive.get(2),
                ManaLevel.t1,
                Map.of(ItemTag.ENDER_EYE, 1),
                null,
                1024,
                0,
                16384,
                null,
                null);
    }
}
