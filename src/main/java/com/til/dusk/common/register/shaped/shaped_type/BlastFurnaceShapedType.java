package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.data.shaped.Shaped;
import com.til.dusk.common.register.shaped.ShapedDrive;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class BlastFurnaceShapedType extends ShapedType {
    public BlastFurnaceShapedType() {
        super("blast_furnace", () -> ManaLevelBlock.blastFurnace);
    }

    @Override
    public void registerSubsidiaryBlack() {
        for (Ore ore : Ore.screen(Ore.IS_METAL, Ore.HAS_DUST)) {
            new Shaped.ShapedOre(
                    fuseName(this, ore, OreItem.ingot),
                    this,
                    ShapedDrive.get(0),
                    ore.manaLevel,
                    Map.of(ItemTags.create(fuseName(ore, OreItem.dust)), 1),
                    null,
                    (long) (ore.strength * 1024L),
                    (long) (ore.consume * 32L),
                    0,
                    List.of(new ItemStack(ore.itemMap.get(OreItem.ingot).item(), 1)),
                    null
            );
        }
    }

}
