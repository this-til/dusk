package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class StampingMachineShapedType extends ShapedType {

    public StampingMachineShapedType() {
        super("stamping_machine", () -> ManaLevelBlock.stampingMachine);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(0),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.ingot).itemTag(), 1),
                    null,
                    (long) (ore.strength * 1024L),
                    (long) (ore.consume * 16L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.plate).item(), 1), 1D),
                    null
            );
        }
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(1),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.plate).itemTag(), 2),
                    null,
                    (long) (ore.strength * 1024L),
                    (long) (ore.consume * 32L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.plate_2).item(), 1), 1D),
                    null
            );
        }
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(2),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.plate_2).itemTag(), 2),
                    null,
                    (long) (ore.strength * 1024L),
                    (long) (ore.consume * 64L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.plate_3).item(), 1), 1D),
                    null
            );
        }
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(3),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.plate_3).itemTag(), 2),
                    null,
                    (long) (ore.strength * 1024L),
                    (long) (ore.consume * 128L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.plate_4).item(), 1), 1D),
                    null
            );
        }
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(4),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.plate).itemTag(), 1),
                    null,
                    (long) (ore.strength * 512L),
                    (long) (ore.consume * 16L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.casing).item(), 1), 1D),
                    null
            );
        }
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(5),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.plate).itemTag(), 1),
                    null,
                    (long) (ore.strength * 2048L),
                    (long) (ore.consume * 12L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.foil).item(), 1), 1D),
                    null
            );
        }

        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(6),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.stick).itemTag(), 2),
                    null,
                    (long) (ore.strength * 512L),
                    (long) (ore.consume * 8L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.stick_long).item(), 1), 1D),
                    null
            );
        }
    }
}
