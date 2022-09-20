package com.til.dusk.common.world.item;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.util.DuskColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

/**
 * @author til
 */
public class DiamondMakeItem extends ItemBasics implements ModItem.ICustomModel, ModItem.IHasCustomColor {

    public static final ResourceLocation DIAMOND_MAKE = new ResourceLocation(Dusk.MOD_ID, "diamond_make");

    public final DuskColor strokeColor;
    public final DuskColor coreColor;

    public DiamondMakeItem(Properties properties, DuskColor strokeColor, DuskColor coreColor) {
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
        itemColorPack.addColor(1, itemStack -> coreColor);
        itemColorPack.addColor(2, itemStack -> strokeColor);
    }
}
