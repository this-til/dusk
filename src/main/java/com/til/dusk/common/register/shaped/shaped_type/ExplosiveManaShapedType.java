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
public class ExplosiveManaShapedType extends ShapedType {
    public ExplosiveManaShapedType() {
        super("explosive_mana", () -> ManaLevelBlock.explosiveMana);
    }

    @Override
    public void registerSubsidiaryBlack() {
        new Shaped.ShapedOre(
                fuseName(name, Tags.Items.GUNPOWDER.location()),
                this,
                ShapedDrive.get(0),
                ManaLevel.t1,
                Map.of(Tags.Items.GUNPOWDER, 1),
                null,
                512,
                0,
                8192,
                (List<ItemStack>) null,
                null);
        new Shaped.ShapedOre(
                fuseName(name, ItemTag.TNT.d1().location()),
                this,
                ShapedDrive.get(0),
                ManaLevel.t1,
                Map.of(ItemTag.TNT.d1(), 1),
                null,
                4096,
                0,
                50000,
                (List<ItemStack>) null,
                null);
    }
}
