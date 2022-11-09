package com.til.dusk.common.register.mana_level.block.mechanic.handle_mechanic;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelItemGroup;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;

/**
 * @author til
 */
public class DissolutionMechanic extends HandleMechanic {
    public DissolutionMechanic() {
        super("dissolution");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "溶解");
        lang.add(LangType.EN_CH, "Dissolution Crystal");
    }

    @Override
    public void defaultConfig() { shapedTypeList = List.of(ShapedType.dissolution);
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(blastFurnace.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemGroup.destruction.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(OreBlock.coil.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ItemIn(() ->ItemTag.CAULDRON.d1(), 1)));
    }
}
