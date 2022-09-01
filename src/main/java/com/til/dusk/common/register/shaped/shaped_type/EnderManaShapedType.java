package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.data.shaped.Shaped;
import com.til.dusk.common.register.shaped.ShapedDrive;
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
    public void registerSubsidiaryBlack() {
        new Shaped.ShapedOre(
                fuseName(name, Tags.Items.END_STONES.location()),
                this,
                ShapedDrive.get(0),
                ManaLevel.t1,
                Map.of(Tags.Items.END_STONES, 1),
                null,
                512,
                0,
                2048,
                (List<ItemStack>) null,
                null);
        new Shaped.ShapedOre(
                fuseName(name, Tags.Items.ENDER_PEARLS.location()),
                this,
                ShapedDrive.get(1),
                ManaLevel.t1,
                Map.of(Tags.Items.ENDER_PEARLS, 1),
                null,
                512,
                0,
                32768L,
                (List<ItemStack>) null,
                null);
        new Shaped.ShapedOre(
                fuseName(name, ItemTag.ENDER_EYE.location()),
                this,
                ShapedDrive.get(1),
                ManaLevel.t1,
                Map.of(ItemTag.ENDER_EYE, 1),
                null,
                512,
                0,
                32768L,
                (List<ItemStack>) null,
                null);
    }
}
