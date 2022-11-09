package com.til.dusk.common.register.ore.item.items;

import com.google.gson.JsonObject;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemMetal;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.ModelJsonUtil;
import net.minecraft.world.item.Item;

/**
 * @author til
 */
public class Plate3OreItemMetal extends OreItemMetal {
    public Plate3OreItemMetal() {
        super("plate_3");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "三重板");
        lang.add(LangType.EN_CH, "3 Plate");
    }

    @Override
    public JsonObject createModel(Item item, Ore ore) {
        return ModelJsonUtil.createItemFather(plate.name);
    }
    @Override
    public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
        itemColorPack.addColor(0, itemStack -> ore.color);
    }
}
