package com.til.dusk.common.world.item;

import com.til.dusk.Dusk;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author til
 */
public abstract class ItemBasics extends Item {

    public ItemBasics(Properties properties) {
        super(properties.tab(Dusk.TAB));
    }
}
