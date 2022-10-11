package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author til
 */
public class EnderManaShapedType extends ShapedType {

    public EnderManaShapedType() {
        super("ender_mana", () -> ManaLevelBlock.enderMana);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {

    }

    @Override
    public void defaultConfig() {
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), Tags.Items.END_STONES.location().getPath()}),
                        this, ShapedDrive.get(0), ManaLevel.t1)
                        .addInItem(Tags.Items.END_STONES, 1)
                        .addMultipleSurplusTime(512)
                        .addMultipleOutMana(2048),
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), Tags.Items.ENDER_PEARLS.location().getPath()}),
                        this, ShapedDrive.get(1), ManaLevel.t1)
                        .addInItem(Tags.Items.ENDER_PEARLS, 1)
                        .addMultipleSurplusTime(1024)
                        .addMultipleOutMana(16384),
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), ItemTag.ENDER_EYE.location().getPath()}),
                        this, ShapedDrive.get(2), ManaLevel.t1)
                        .addInItem(ItemTag.ENDER_EYE, 1)
                        .addMultipleSurplusTime(1024)
                        .addMultipleOutMana(16384)
        ));
    }
}
