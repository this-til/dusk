package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public class FormingShapedType extends ShapedType {

    public FormingShapedType() {
        super("forming", () -> ManaLevelBlock.shaping);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.ARMS_DATA)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(0),
                    ore.manaLevel,
                    Map.of(
                            ore.itemMap.get(OreItem.plate_4).itemTag(), 2,
                            ore.itemMap.get(OreItem.plate_3).itemTag(), 4,
                            ore.itemMap.get(OreItem.plate_2).itemTag(), 8
                    ),
                    null,
                    (long) (ore.strength * 16384L * 2),
                    (long) (ore.consume * 12L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.swordBasics).item(), 1), 1d),
                    null
            );
            new ShapedOre(
                    this,
                    ShapedDrive.get(1),
                    ore.manaLevel,
                    Map.of(
                            ore.itemMap.get(OreItem.plate_4).itemTag(), 1,
                            ore.itemMap.get(OreItem.plate_3).itemTag(), 2,
                            ore.itemMap.get(OreItem.plate_2).itemTag(), 4
                    ),
                    null,
                    (long) (ore.strength * 16384L),
                    (long) (ore.consume * 12L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.shovelBasics).item(), 1), 1d),
                    null
            );
            new ShapedOre(
                    this,
                    ShapedDrive.get(2),
                    ore.manaLevel,
                    Map.of(
                            ore.itemMap.get(OreItem.plate_4).itemTag(), 3,
                            ore.itemMap.get(OreItem.plate_3).itemTag(), 6,
                            ore.itemMap.get(OreItem.plate_2).itemTag(), 12
                    ),
                    null,
                    (long) (ore.strength * 16384L * 3),
                    (long) (ore.consume * 12L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.pickaxeBasics).item(), 1), 1d),
                    null
            );
            new ShapedOre(
                    this,
                    ShapedDrive.get(3),
                    ore.manaLevel,
                    Map.of(
                            ore.itemMap.get(OreItem.plate_4).itemTag(), 3,
                            ore.itemMap.get(OreItem.plate_3).itemTag(), 6,
                            ore.itemMap.get(OreItem.plate_2).itemTag(), 12
                    ),
                    null,
                    (long) (ore.strength * 16384L * 3),
                    (long) (ore.consume * 12L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.axeBasics).item(), 1), 1d),
                    null
            );
            new ShapedOre(
                    this,
                    ShapedDrive.get(4),
                    ore.manaLevel,
                    Map.of(
                            ore.itemMap.get(OreItem.plate_4).itemTag(), 2,
                            ore.itemMap.get(OreItem.plate_3).itemTag(), 4,
                            ore.itemMap.get(OreItem.plate_2).itemTag(), 8
                    ),
                    null,
                    (long) (ore.strength * 16384L * 2),
                    (long) (ore.consume * 12L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.hoeBasics).item(), 1), 1d),
                    null
            );
        }
    }
}
