package com.til.dusk.common.register.ore.block;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

/***
 * 装饰方块，包含块、楼梯、半砖、墙等
 * @author til
 */
public abstract class OreBlockDecorate extends OreBlock {
    public OreBlockDecorate(ResourceLocation name) {
        super(name);
    }

    public OreBlockDecorate(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public @Nullable BlockPack create(Ore ore) {
        if (ore.hasConfig(OreConfig.DecorateBlockConfig.DECORATE_BLOCK_CONFIG)) {
            return super.create(ore);
        }
        return null;
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return super.defaultConfigMap()
                .setConfig(IS_DECORATE);
    }
}
