package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.ResourceLocationUtil;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author til
 */
public class SlimeyManaShapedType extends ShapedType {

    public SlimeyManaShapedType() {
        super("slimey_mana");
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
    }

    @Override
    public void defaultConfig() {
        blockTagKey = new Delayed.BlockDelayed(() -> ManaLevelBlock.slimeyMana.tagPackSupplier.getTagPack().blockTagKey());
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(),  "/", new String[]{name.getPath(), ItemTag.SLIME_BALL.location().getPath()}),
                        this, ShapedDrive.get(0), ManaLevel.t1)
                        .addInItem(ItemTag.SLIME_BALL, 1)
                        .addMultipleSurplusTime(512)
                        .addMultipleOutMana(2048),
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), ItemTag.SLIME_BLOCK.d1().location().getPath()}),
                        this, ShapedDrive.get(1), ManaLevel.t1)
                        .addInItem(ItemTag.SLIME_BLOCK.d1(), 1)
                        .addMultipleSurplusTime(512)
                        .addMultipleOutMana(2048)
        ));
    }
}
