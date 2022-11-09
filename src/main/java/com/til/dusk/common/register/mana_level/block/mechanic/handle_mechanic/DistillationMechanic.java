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
public class DistillationMechanic extends HandleMechanic {

    public DistillationMechanic() {
        super("distillation");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "蒸馏");
        lang.add(LangType.EN_CH, "Distillation Crystal");
    }

    @Override
    public void defaultConfig() {shapedTypeList = List.of(ShapedType.distillation);
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(blastFurnace.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemGroup.destruction.name, 3),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemGroup.spread.name, 1)));
    }
}
