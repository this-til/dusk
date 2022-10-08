package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemMetal;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.prefab.ColorPrefab;

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
    public DuskItem.ICustomModel getItemMoldMapping(Ore ore) {
        return () -> plate.name;
    }

    @Override
    public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
        DuskColor color = ColorPrefab.GRAYSCALE_REDUCTION_2.blend(ore.getConfig(Ore.COLOR));
        itemColorPack.addColor(0, itemStack -> color);
    }
}