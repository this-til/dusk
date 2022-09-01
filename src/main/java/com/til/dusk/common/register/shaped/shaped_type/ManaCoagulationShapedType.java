package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreFluid;
import com.til.dusk.common.data.shaped.Shaped;
import com.til.dusk.common.register.shaped.ShapedDrive;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

/**
 * @author til
 */
public class ManaCoagulationShapedType extends ShapedType{
    public ManaCoagulationShapedType(){
        super("mana_coagulation", () -> ManaLevelBlock.manaCoagulation);
    }

    @Override
    public void registerSubsidiaryBlack() {
        new Shaped.ShapedOre(
                this.name,
                this,
                ShapedDrive.get(0),
                ManaLevel.t1,
                null,
                null,
                1024L,
                32L,
                0,
                null,
                List.of(new FluidStack(Ore.mana.fluidMap.get(OreFluid.solution).source(), 1))
        );
    }

}