package com.til.dusk.common.register.world.item;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.DuskColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

/**
 * @author til
 */
public abstract class DiamondMakeItemPackRegister extends ItemPackRegister implements DuskItem.ICustomModel, DuskItem.IHasCustomColor {

    public static final ResourceLocation DIAMOND_MAKE = new ResourceLocation(Dusk.MOD_ID, "diamond_make");


    public DiamondMakeItemPackRegister(String name) {
        super(name);
    }

    public DiamondMakeItemPackRegister(ResourceLocation name) {
        super(name);
    }

    @Override
    public ResourceLocation itemModelName() {
        return DIAMOND_MAKE;
    }

    @Override
    protected Item createItem() {
        return new Item(new Item.Properties().tab(Dusk.TAB));
    }

    @Override
    public void itemColorBlack(ColorProxy.ItemColorPack itemColorPack) {
        itemColorPack.addColor(1, itemStack -> strokeColor);
        itemColorPack.addColor(2, itemStack -> coreColor);
    }

    @ConfigField
    public DuskColor strokeColor;

    @ConfigField
    public DuskColor coreColor;
}
