package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

/**
 * @author til
 */
public class BotanyManaShapedType extends ShapedType {

    public BotanyManaShapedType() {
        super("botany_mana", () -> ManaLevelBlock.botanyMana);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        new ShapedOre(this, ShapedDrive.get(0), ManaLevel.t1)
                .addInItem(ItemTags.SAPLINGS, 1)
                .addMultipleSurplusTime(128)
                .addMultipleOutMana(256);
        new ShapedOre(this, ShapedDrive.get(1), ManaLevel.t1)
                .addInItem(ItemTags.LEAVES, 1)
                .addMultipleSurplusTime(128)
                .addMultipleOutMana(128);
        new ShapedOre(this, ShapedDrive.get(2), ManaLevel.t1)
                .addInItem(ItemTags.LOGS, 1)
                .addMultipleSurplusTime(128)
                .addMultipleOutMana(512);
        new ShapedOre(this, ShapedDrive.get(3), ManaLevel.t1)
                .addInItem(ItemTags.FLOWERS, 1)
                .addMultipleSurplusTime(128)
                .addMultipleOutMana(256);
        new ShapedOre(this, ShapedDrive.get(4), ManaLevel.t1)
                .addInItem(Tags.Items.MUSHROOMS, 1)
                .addMultipleSurplusTime(128)
                .addMultipleOutMana(385);
        new ShapedOre(this, ShapedDrive.get(5), ManaLevel.t1)
                .addInItem(ItemTag.GRASS.d1(), 1)
                .addMultipleSurplusTime(128)
                .addMultipleOutMana(128);
    }
}
