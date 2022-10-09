package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class CentrifugalMechanic extends HandleMechanic {
    public CentrifugalMechanic(){
        super("centrifugal", () -> Set.of(ShapedType.centrifugal));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "离心晶体");
        lang.add(LangType.EN_CH, "Centrifugal Crystal");
    }

    @Override
    public void defaultConfig() {
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(frameBasic.name, 1),
                new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.destruction.name, 1),
                new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(OreItem.rotor.name, 2),
                new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(OreItem.casing.name, 4)));
    }
}
