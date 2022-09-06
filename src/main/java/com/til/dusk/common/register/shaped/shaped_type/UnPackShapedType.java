package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class UnPackShapedType extends ShapedType {

    public UnPackShapedType() {
        super("unpack", () -> ManaLevelBlock.unpack);
    }

    @Override
    public void registerSubsidiaryBlack() {
        for (Ore ore : Ore.screen(Ore.HAS_BLOCK, Ore.IS_METAL)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(0),
                    ore.manaLevel,
                    Map.of(ore.blockMap.get(OreBlock.block).blockItemTag(), 1),
                    null,
                    (long) (ore.strength * 128L),
                    (long) (ore.consume * 4L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.ingot).item(), 9), 1d),
                    null);
        }

        for (Ore ore : Ore.screen(Ore.HAS_BLOCK, Ore.IS_CRYSTA)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(1),
                    ore.manaLevel,
                    Map.of(ore.blockMap.get(OreBlock.block).blockItemTag(), 1),
                    null,
                    (long) (ore.strength * 128L),
                    (long) (ore.consume * 4L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.crystal).item(), 9), 1d),
                    null);
        }

        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(2),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.ingot).itemTag(), 1),
                    null,
                    (long) (ore.strength * 128L),
                    (long) (ore.consume * 4L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.nuggets).item(), 9), 1d),
                    null);
        }

        for (Ore ore : Ore.ORE.get()) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(3),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.dust).itemTag(), 1),
                    null,
                    (long) (ore.strength * 128L),
                    (long) (ore.consume * 4L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.dustTiny).item(), 9), 1d),
                    null);
        }


    }

}
