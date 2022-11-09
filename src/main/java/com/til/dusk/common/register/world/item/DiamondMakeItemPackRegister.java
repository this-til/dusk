package com.til.dusk.common.register.world.item;

import com.google.gson.JsonObject;
import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.ModelJsonUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

/**
 * @author til
 */
public abstract class DiamondMakeItemPackRegister extends ItemPackRegister implements DuskItem.ICustomModel<Void>, DuskItem.IHasCustomColor {

    public static final ResourceLocation DIAMOND_MAKE = new ResourceLocation(Dusk.MOD_ID, "diamond_make");


    public DiamondMakeItemPackRegister(String name) {
        super(name);
    }

    public DiamondMakeItemPackRegister(ResourceLocation name) {
        super(name);
    }

    @Override
    public void itemColorBlack(ColorProxy.ItemColorPack itemColorPack) {
        itemColorPack.addColor(1, itemStack -> strokeColor);
        itemColorPack.addColor(2, itemStack -> coreColor);
    }

    @Override
    public JsonObject createModel(Item item, Void o) {
        return ModelJsonUtil.createItemFather(DIAMOND_MAKE);
    }

    @ConfigField
    public DuskColor strokeColor;

    @ConfigField
    public DuskColor coreColor;
}
