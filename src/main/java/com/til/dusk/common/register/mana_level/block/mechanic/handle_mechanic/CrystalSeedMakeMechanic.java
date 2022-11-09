package com.til.dusk.common.register.mana_level.block.mechanic.handle_mechanic;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelItemGroup;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;

/**
 * @author til
 */
public class CrystalSeedMakeMechanic extends HandleMechanic {
    public CrystalSeedMakeMechanic() {
        super("crystal_seed_make");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "种子制造");
        lang.add(LangType.EN_CH, "Crystal Seed Make Crystal");
    }

    @Override
    public void defaultConfig() {shapedTypeList = List.of(ShapedType.crystalSeedMake);
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(unpack.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemGroup.forming.name, 1)));
    }
}
