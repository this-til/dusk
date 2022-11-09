package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author til
 */
public class BotanyManaShapedType extends ShapedType {

    public BotanyManaShapedType() {
        super("botany_mana");
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {

    }

    @Override
    public void defaultConfig() {
        blockTagKey = new Delayed.BlockDelayed(() -> ManaLevelBlock.botanyMana.tagPackSupplier.getTagPack().blockTagKey());
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), ItemTags.SAPLINGS.location().getPath()}),
                        this, ShapedDrive.get(0), ManaLevel.t1)
                        .addInItem(ItemTags.SAPLINGS, 1)
                        .addMultipleSurplusTime(128)
                        .addMultipleOutMana(256),
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), ItemTags.LEAVES.location().getPath()}),
                        this, ShapedDrive.get(1), ManaLevel.t1)
                        .addInItem(ItemTags.LEAVES, 1)
                        .addMultipleSurplusTime(128)
                        .addMultipleOutMana(128),
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), ItemTags.LOGS.location().getPath()}),
                        this, ShapedDrive.get(2), ManaLevel.t1)
                        .addInItem(ItemTags.LOGS, 1)
                        .addMultipleSurplusTime(128)
                        .addMultipleOutMana(512),
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), ItemTags.FLOWERS.location().getPath()}),
                        this, ShapedDrive.get(3), ManaLevel.t1)
                        .addInItem(ItemTags.FLOWERS, 1)
                        .addMultipleSurplusTime(128)
                        .addMultipleOutMana(256),
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), Tags.Items.MUSHROOMS.location().getPath()}),
                        this, ShapedDrive.get(4), ManaLevel.t1)
                        .addInItem(Tags.Items.MUSHROOMS, 1)
                        .addMultipleSurplusTime(128)
                        .addMultipleOutMana(385),
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), ItemTag.GRASS.d1().location().getPath()}),
                        this, ShapedDrive.get(5), ManaLevel.t1)
                        .addInItem(ItemTag.GRASS.d1(), 1)
                        .addMultipleSurplusTime(128)
                        .addMultipleOutMana(128)
        ));
    }
}
