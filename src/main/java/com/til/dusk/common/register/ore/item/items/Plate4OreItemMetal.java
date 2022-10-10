package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.client.ColorProxy;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemMetal;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.world.item.DuskItem;

/**
 * @author til
 */
public class Plate4OreItemMetal extends OreItemMetal {
    public Plate4OreItemMetal() {
        super("plate_4");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "四重板");
        lang.add(LangType.EN_CH, "4 Plate");
    }


    @Override
    public DuskItem.ICustomModel getItemMoldMapping(Ore ore) {
        return () -> plate.name;
    }

    @Override
    public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
        itemColorPack.addColor(0, itemStack -> ore.color);
    }
}
