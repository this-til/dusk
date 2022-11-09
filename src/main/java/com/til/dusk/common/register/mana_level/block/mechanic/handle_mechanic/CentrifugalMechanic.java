package com.til.dusk.common.register.mana_level.block.mechanic.handle_mechanic;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelItemGroup;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;

/**
 * @author til
 */
public class CentrifugalMechanic extends HandleMechanic {
    public CentrifugalMechanic(){
        super("centrifugal");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "离心晶体");
        lang.add(LangType.EN_CH, "Centrifugal Crystal");
    }

    @Override
    public void defaultConfig() {        shapedTypeList = List.of(ShapedType.centrifugal);
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(frameBasic.name, 1),
                new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemGroup.destruction.name, 1),
                new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(OreItem.rotor.name, 2),
                new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(OreItem.casing.name, 4)));
    }
}
