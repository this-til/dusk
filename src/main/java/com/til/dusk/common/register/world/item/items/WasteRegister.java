package com.til.dusk.common.register.world.item.items;

import com.til.dusk.client.ColorProxy;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.world.item.ItemPackRegister;
import com.til.dusk.common.world.block.DuskBlock;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.DuskColor;
import org.checkerframework.checker.units.qual.C;

/**
 * @author til
 */
public class WasteRegister extends ItemPackRegister implements DuskItem.IHasCustomColor {

    public WasteRegister() {
        super("waste");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "废料");
        lang.add(LangType.EN_CH, "Waste");
    }

    @Override
    public void itemColorBlack(ColorProxy.ItemColorPack itemColorPack) {
        itemColorPack.addColor(0, itemStack -> colorBasics);
        itemColorPack.addColor(1, itemStack -> colorEmbellishment);
    }

    @Override
    public void defaultConfig() {
        colorBasics = new DuskColor(163, 128, 100);
        colorEmbellishment = new DuskColor(246, 183, 92);
    }

    @ConfigField
    public DuskColor colorBasics;

    @ConfigField
    public DuskColor colorEmbellishment;

    @Override
    public String itemJson() {
        return "";
    }
}
