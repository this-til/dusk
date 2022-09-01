package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.register.mana_level.ManaLevel;
import net.minecraft.resources.ResourceLocation;

/**
 * @author til
 */
public abstract class Mechanic extends ManaLevelBlock {
    public Mechanic(ResourceLocation name) {
        super(name);
        addTag(NEED_FRAME_UP);
    }

    public Mechanic(String name) {
        super(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
        itemColorPack.addClock(0, itemStack -> manaLevel.color);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.BlockColorPack blockColorPack) {
        blockColorPack.addClock(0, (blockState, blockAndTintGetter, blockPos) -> manaLevel.color);
    }

}