package com.til.dusk.common.register.ore.ore.fluid.si_xiang;

import com.til.dusk.common.data.tag.FluidTag;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.pack.FluidPack;
import net.minecraft.resources.ResourceLocation;

public abstract class SiXiangBasicsOre extends Ore {
    public SiXiangBasicsOre(String name) {
        super(name);
    }

    public SiXiangBasicsOre(ResourceLocation name) {
        super(name);
    }

    @Override
    protected void registerFluid(OreFluid oreFluid, FluidPack fluidPack) {
        super.registerFluid(oreFluid, fluidPack);
        FluidTag.addTag(FluidTag.SI_XIANG, fluidPack.source());
    }
}
