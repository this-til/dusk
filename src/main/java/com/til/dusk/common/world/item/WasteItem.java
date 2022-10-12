package com.til.dusk.common.world.item;

import com.til.dusk.client.ColorProxy;
import com.til.dusk.util.DuskColor;
import net.minecraft.world.item.BoneMealItem;

/**
 * @author til
 */
public class WasteItem extends BoneMealItem implements DuskItem.IHasCustomColor {
    public WasteItem(Properties properties) {
        super(properties);
    }

    @Override
    public void itemColorBlack(ColorProxy.ItemColorPack itemColorPack) {
        itemColorPack.addColor(0, itemStack -> new DuskColor(163, 128, 100));
        itemColorPack.addColor(1, itemStack -> new DuskColor(246, 183, 92));
    }

}
