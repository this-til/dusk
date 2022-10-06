package com.til.dusk.common.register.ore.block;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

/**
 * @author til
 */
public abstract class OreBlockMetal extends OreBlock{
    public OreBlockMetal(ResourceLocation name) {
        super(name);
    }

    public OreBlockMetal(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public @Nullable BlockPack create(Ore ore) {
        if (ore.hasConfig(OreConfig.IS_METAL)) {
            return super.create(ore);
        }
        return null;
    }
}
