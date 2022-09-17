package com.til.dusk.common.world.item;

import com.til.dusk.Dusk;
import net.minecraft.world.item.Item;

/**
 * @author til
 */
public abstract class ItemBasics extends Item {

    public ItemBasics(Properties properties) {
        super(properties.tab(Dusk.TAB));
    }
}
