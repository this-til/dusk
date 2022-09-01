package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.data.shaped.Shaped;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.util.Extension;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class WashShapedType extends ShapedType {

    public WashShapedType() {
        super("wash", () -> ManaLevelBlock.wash);
    }

    @Override
    public void registerSubsidiaryBlack() {
        for (Ore ore : Ore.screen(Ore.HAS_CRUSHED)) {
            new Shaped.ShapedOre(
                    fuseName(this, ore, OreItem.crushedPurified),
                    this,
                    ShapedDrive.get(0),
                    ore.manaLevel,
                    Map.of(ItemTags.create(fuseName(ore, OreItem.crushed)), 1),
                    Map.of(FluidTags.WATER, 1000),
                    (long) (ore.strength * 1280L),
                    (long) (ore.consume * 12L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.crushedPurified).item(), 1), 1d),
                    null);
        }
    }

}
