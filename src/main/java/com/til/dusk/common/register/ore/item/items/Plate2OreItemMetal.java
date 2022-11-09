package com.til.dusk.common.register.ore.item.items;

import com.google.gson.JsonObject;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemMetal;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.ModelJsonUtil;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.world.item.Item;

/**
 * @author til
 */
public class Plate2OreItemMetal extends OreItemMetal {
    public Plate2OreItemMetal() {
        super("plate_2");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "二重板");
        lang.add(LangType.EN_CH, "2 Plate");
    }

    @Override
    public JsonObject createModel(Item item, Ore ore) {
        return ModelJsonUtil.createItemFather(plate.name);
    }

    @Override
    public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
        DuskColor color = ColorPrefab.GRAYSCALE_REDUCTION_1.blend(ore.color);
        itemColorPack.addColor(0, itemStack -> color);
    }
}
