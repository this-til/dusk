package com.til.dusk.common.register.mana_level.block;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import net.minecraft.resources.ResourceLocation;

/**
 * @author til
 */
public abstract class Mechanic extends ManaLevelBlock {
    public Mechanic(ResourceLocation name) {
        super(name);
    }

    public Mechanic(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
        itemColorPack.addColor(0, itemStack -> manaLevel.color);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.BlockColorPack blockColorPack) {
        blockColorPack.addColor(0, (blockState, blockAndTintGetter, blockPos) -> manaLevel.color);
    }

}
