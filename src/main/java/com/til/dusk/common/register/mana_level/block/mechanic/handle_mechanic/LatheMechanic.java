package com.til.dusk.common.register.mana_level.block.mechanic.handle_mechanic;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelItemGroup;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;

/**
 * @author til
 */
public class LatheMechanic extends HandleMechanic {

    public LatheMechanic() {
        super("lathe");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "车床晶体 ");
        lang.add(LangType.EN_CH, "Lathe Crystal");
    }

    @Override
    public void defaultConfig() {shapedTypeList = List.of(ShapedType.lathe);
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(stampingMachine.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemGroup.instructions.name, 3),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemGroup.power.name, 3),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(OreItem.file.name, 3),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ItemIn(() ->ItemTag.ANVIL.d1(), 1)));
    }
}
