package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreFluid;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.pack.DataPack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author til
 */
public class HighPressureFuseShapedType extends ShapedType {

    public HighPressureFuseShapedType() {
        super("high_pressure_fuse", () -> ManaLevelBlock.highPressureFuse);
    }

    @Override
    public void registerShaped() {
    }
}
