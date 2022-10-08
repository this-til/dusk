package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.item.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class CrystallizingMechanic extends HandleMechanic {
    public CrystallizingMechanic() {
        super("crystallizing", () -> Set.of(ShapedType.crystallizing));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "结晶晶体");
        lang.add(LangType.EN_CH, "Crystallizing Crystal");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfig(MECHANIC_MAKE_DATA, () -> new ConfigMap()
                        .setConfigOfV(ManaLevelMakeDataConfig.ORE_CONFIG, List.of(
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(pack.name, 1),
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(ManaLevelItemPack.forming.name, 1),
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.CAULDRON.d1(), 1),
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(OreItem.crystalSeed.tagPackSupplier.getTagPack().itemTagKey(), 12))));
    }
}
