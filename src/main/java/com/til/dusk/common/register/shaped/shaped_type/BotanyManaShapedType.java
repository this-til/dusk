package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

import java.util.Map;

/**
 * @author til
 */
public class BotanyManaShapedType extends ShapedType {

    public BotanyManaShapedType() {
        super("botany_mana", () -> ManaLevelBlock.botanyMana);
    }

    @Override
    public void registerShaped() {
        new ShapedOre(
                this,
                ShapedDrive.get(0),
                ManaLevel.t1,
                Map.of(ItemTags.SAPLINGS, 1),
                null,
                128L,
                0,
                256L,
                null,
                null
        );
        new ShapedOre(
                this,
                ShapedDrive.get(1),
                ManaLevel.t1,
                Map.of(ItemTags.LEAVES, 1),
                null,
                128L,
                0,
                128L,
                null,
                null
        );
        new ShapedOre(
                this,
                ShapedDrive.get(2),
                ManaLevel.t1,
                Map.of(ItemTags.LOGS, 1),
                null,
                128L,
                0,
                512L,
                null,
                null
        );
        new ShapedOre(
                this,
                ShapedDrive.get(3),
                ManaLevel.t1,
                Map.of(ItemTags.FLOWERS, 1),
                null,
                128L,
                0,
                256L,
                null,
                null
        );
        new ShapedOre(
                this,
                ShapedDrive.get(4),
                ManaLevel.t1,
                Map.of(Tags.Items.MUSHROOMS, 1),
                null,
                128L,
                0,
                385L,
                null,
                null
        );
        new ShapedOre(
                this,
                ShapedDrive.get(5),
                ManaLevel.t1,
                Map.of(ItemTag.GRASS.d1(), 1),
                null,
                128L,
                0,
                128L,
                null,
                null
        );
    }
}
