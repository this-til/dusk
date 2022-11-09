package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.function.Consumer;

/***
 * @author til
 */
public class FrostyManaShapedType extends ShapedType {

    public FrostyManaShapedType() {
        super("frosty_mana");
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {

    }

    @Override
    public void defaultConfig() {
        blockTagKey = new Delayed.BlockDelayed(() -> ManaLevelBlock.frostyMana.tagPackSupplier.getTagPack().blockTagKey());
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(),  "/", new String[]{name.getPath(), ItemTag.ICES.d1().location().getPath()}),
                        this, ShapedDrive.get(0), ManaLevel.t1)
                        .addInItem(ItemTag.ICES.d1(), 1)
                        .addMultipleSurplusTime(128)
                        .addMultipleOutMana(512),
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), ItemTag.SNOW_BLOCK.d1().location().getPath()}),
                        this, ShapedDrive.get(1), ManaLevel.t1)
                        .addInItem(ItemTag.SNOW_BLOCK.d1(), 1)
                        .addMultipleSurplusTime(128)
                        .addMultipleOutMana(256),
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(),  "/", new String[]{name.getPath(), ItemTag.SNOWBALL.location().getPath()}),
                        this, ShapedDrive.get(2), ManaLevel.t1)
                        .addInItem(ItemTag.SNOWBALL, 1)
                        .addMultipleSurplusTime(128)
                        .addMultipleOutMana(256),
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(),  "/", new String[]{name.getPath(), ItemTag.POWDER_SNOW_BUCKET.location().getPath()}),
                        this, ShapedDrive.get(3), ManaLevel.t1)
                        .addInItem(ItemTag.POWDER_SNOW_BUCKET, 1)
                        .addOutItem(new ItemStack(Items.BUCKET), 1d)
                        .addMultipleSurplusTime(128)
                        .addMultipleOutMana(8196)
        ));
    }
}
