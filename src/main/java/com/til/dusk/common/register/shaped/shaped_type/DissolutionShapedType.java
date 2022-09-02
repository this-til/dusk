package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreFluid;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class DissolutionShapedType extends ShapedType{

    public DissolutionShapedType(){
        super("dissolution", () ->ManaLevelBlock.dissolution);
    }

    @Override
    public void registerSubsidiaryBlack() {
        for (Ore ore : Ore.screen(Ore.HAS_FLUID, Ore.IS_METAL)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(0),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.ingot).itemTag(), 1),
                    null,
                    (long) (ore.strength * 512),
                    (long) (ore.consume * 128L),
                    0,
                    null,
                    Map.of(new FluidStack(ore.fluidMap.get(OreFluid.solution).source(), 144), 1d));
        }
        for (Ore ore : Ore.screen(Ore.HAS_FLUID, Ore.IS_CRYSTA)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(1),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.crystal).itemTag(), 1),
                    null,
                    (long) (ore.strength * 512),
                    (long) (ore.consume * 128L),
                    0,
                    null,
                    Map.of(new FluidStack(ore.fluidMap.get(OreFluid.solution).source(), 144), 1d));
        }
        for (Ore ore : Ore.screen(Ore.HAS_FLUID, Ore.HAS_DUST)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(2),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.dust).itemTag(), 1),
                    null,
                    (long) (ore.strength * 512),
                    (long) (ore.consume * 128L),
                    0,
                    null,
                    Map.of(new FluidStack(ore.fluidMap.get(OreFluid.solution).source(), 144), 1d));
        }
    }

}
