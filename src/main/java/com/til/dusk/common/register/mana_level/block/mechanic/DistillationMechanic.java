package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class DistillationMechanic extends HandleMechanic {

    public DistillationMechanic() {
        super("distillation", () -> Set.of(ShapedType.distillation));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "蒸馏");
        lang.add(LangType.EN_CH, "Distillation Crystal");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfig(MECHANIC_MAKE_DATA, () -> new ConfigMap()
                        .setConfigOfV(ManaLevelMakeDataConfig.ORE_CONFIG, List.of(
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(blastFurnace.name, 1),
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(ManaLevelItemPack.destruction.name, 3),
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(ManaLevelItemPack.spread.name, 1))));
    }

}
