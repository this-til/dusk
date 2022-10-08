package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.config.ConfigMap;
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
public class CarvingMechanic extends HandleMechanic {

    public CarvingMechanic() {
        super("carving", () -> Set.of(ShapedType.carving));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "雕刻");
        lang.add(LangType.EN_CH, "Carving Crystal");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfig(MECHANIC_MAKE_DATA, () -> new ConfigMap()
                        .setConfigOfV(ManaLevelMakeDataConfig.ORE_CONFIG, List.of(
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(assemble.name, 1),
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(ManaLevelItemPack.instructions.name, 1),
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(OreItem.file.name, 1))));
    }

}
