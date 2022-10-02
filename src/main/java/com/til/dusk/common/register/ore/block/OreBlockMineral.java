package com.til.dusk.common.register.ore.block;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

/**
 * @author til
 */
public abstract class OreBlockMineral extends OreBlock {
    public OreBlockMineral(ResourceLocation name) {
        super(name);
        setSet(OreBlock.IS_MINERAL);
    }

    public OreBlockMineral(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public @Nullable BlockPack create(Ore ore) {
        if (ore.hasSet(Ore.MINERAL_BLOCK_DATA)) {
            return super.create(ore);
        }
        return null;
    }
}
