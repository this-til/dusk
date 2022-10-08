package com.til.dusk.common.register.mana_level.block;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.util.DuskColor;
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
        DuskColor color = manaLevel.getConfig(ManaLevel.color);
        itemColorPack.addColor(0, itemStack -> color);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.BlockColorPack blockColorPack) {
        DuskColor color = manaLevel.getConfig(ManaLevel.color);
        blockColorPack.addColor(0, (blockState, blockAndTintGetter, blockPos) -> color);
    }

}
