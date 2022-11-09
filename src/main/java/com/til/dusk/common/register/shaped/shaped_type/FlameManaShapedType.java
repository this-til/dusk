package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author til
 */
public class FlameManaShapedType extends ShapedType {
    public FlameManaShapedType() {
        super("flame_mana");
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {


    }

    @Override
    public void defaultConfig() {
        blockTagKey =new Delayed.BlockDelayed(() ->  ManaLevelBlock.flameMana.tagPackSupplier.getTagPack().blockTagKey());
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(),  "/", new String[]{name.getPath(), FluidTags.LAVA.location().getPath()}),
                        this, ShapedDrive.get(0), ManaLevel.t1)
                        .addInFluid(FluidTags.LAVA, 100)
                        .addMultipleSurplusTime(258)
                        .addMultipleOutMana(2048),
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(),  "/", new String[]{name.getPath(), ItemTag.BLAZE_POWDER.location().getPath()}),
                        this, ShapedDrive.get(1), ManaLevel.t1)
                        .addInItem(ItemTag.BLAZE_POWDER, 1)
                        .addMultipleSurplusTime(512)
                        .addMultipleOutMana(2048),
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(),  "/", new String[]{name.getPath(), Tags.Items.RODS_BLAZE.location().getPath()}),
                        this, ShapedDrive.get(2), ManaLevel.t1)
                        .addInItem(Tags.Items.RODS_BLAZE, 1)
                        .addMultipleSurplusTime(512)
                        .addMultipleOutMana(4096)
        ));
    }
}
