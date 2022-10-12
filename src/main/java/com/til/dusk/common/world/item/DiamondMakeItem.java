package com.til.dusk.common.world.item;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.util.DuskColor;
import net.minecraft.resources.ResourceLocation;

/**
 * @author til
 */
public class DiamondMakeItem extends ItemBasics implements DuskItem.ICustomModel, DuskItem.IHasCustomColor {

    public static final ResourceLocation DIAMOND_MAKE = new ResourceLocation(Dusk.MOD_ID, "diamond_make");

    public Delayed<DuskColor> strokeColor;
    public Delayed<DuskColor> coreColor;

    public DiamondMakeItem(Properties properties, Delayed<DuskColor> strokeColor, Delayed<DuskColor> coreColor) {
        super(properties);
        this.strokeColor = strokeColor;
        this.coreColor = coreColor;
    }

    @Override
    public ResourceLocation itemModelName() {
        return DIAMOND_MAKE;
    }

    @Override
    public void itemColorBlack(ColorProxy.ItemColorPack itemColorPack) {

        itemColorPack.addColor(1, itemStack -> strokeColor.get());
        itemColorPack.addColor(2, itemStack -> coreColor.get());
    }
}
