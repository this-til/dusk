package com.til.dusk.common.register.ore.ore.fluid.liang_yi;

import com.til.dusk.common.data.tag.FluidTag;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.pack.FluidPack;
import net.minecraft.resources.ResourceLocation;

/**
 * @author til
 */
public abstract class LiangYiBasicsOre extends Ore {
    public LiangYiBasicsOre(String name) {
        super(name);
    }

    public LiangYiBasicsOre(ResourceLocation name) {
        super(name);
    }

    @Override
    protected void registerFluid(OreFluid oreFluid, FluidPack fluidPack) {
        super.registerFluid(oreFluid, fluidPack);
        FluidTag.addTag(FluidTag.LIANG_YI, fluidPack.source());
    }
}
