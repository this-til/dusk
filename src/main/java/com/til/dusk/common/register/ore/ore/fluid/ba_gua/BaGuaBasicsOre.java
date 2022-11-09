package com.til.dusk.common.register.ore.ore.fluid.ba_gua;

import com.til.dusk.common.data.tag.FluidTag;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.pack.FluidPack;
import net.minecraft.resources.ResourceLocation;

/**
 * @author til
 */
public abstract class BaGuaBasicsOre extends Ore {
    public BaGuaBasicsOre(String name) {
        super(name);
    }

    public BaGuaBasicsOre(ResourceLocation name) {
        super(name);
    }

    @Override
    protected void registerFluid(OreFluid oreFluid, FluidPack fluidPack) {
        super.registerFluid(oreFluid, fluidPack);
        FluidTag.addTag(FluidTag.BA_GUA, fluidPack.source());
    }
}
