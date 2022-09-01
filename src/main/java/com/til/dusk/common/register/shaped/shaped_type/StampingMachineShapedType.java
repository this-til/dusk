package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.data.shaped.Shaped;
import com.til.dusk.common.register.shaped.ShapedDrive;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class StampingMachineShapedType extends ShapedType{

    public StampingMachineShapedType(){
        super("stamping_machine", () -> ManaLevelBlock.stampingMachine);
    }

    @Override
    public void registerSubsidiaryBlack() {
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new Shaped.ShapedOre(
                    fuseName(this, ore, OreItem.plate),
                    this,
                    ShapedDrive.get(0),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.ingot).itemTag(), 1),
                    null,
                    (long) (ore.strength * 1024L),
                    (long) (ore.consume * 16L),
                    0,
                    List.of(new ItemStack(ore.itemMap.get(OreItem.plate).item(), 1)),
                    null
            );
        }
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new Shaped.ShapedOre(
                    fuseName(this, ore, OreItem.casing),
                    this,
                    ShapedDrive.get(1),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.plate).itemTag(), 1),
                    null,
                    (long) (ore.strength * 512L),
                    (long) (ore.consume * 16L),
                    0,
                    List.of(new ItemStack(ore.itemMap.get(OreItem.casing).item(), 1)),
                    null
            );
        }
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new Shaped.ShapedOre(
                    fuseName(this, ore, OreItem.foil),
                    this,
                    ShapedDrive.get(2),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.plate).itemTag(), 1),
                    null,
                    (long) (ore.strength * 2048L),
                    (long) (ore.consume * 12L),
                    0,
                    List.of(new ItemStack(ore.itemMap.get(OreItem.foil).item(), 1)),
                    null
            );
        }
    }
}
