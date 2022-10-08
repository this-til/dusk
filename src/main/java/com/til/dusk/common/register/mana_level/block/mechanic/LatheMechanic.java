package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class LatheMechanic extends HandleMechanic {

    public LatheMechanic(){
        super("lathe", () -> Set.of(ShapedType.lathe));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "车床晶体 ");
        lang.add(LangType.EN_CH, "Lathe Crystal");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfig(MECHANIC_MAKE_DATA, () -> new ConfigMap()
                        .setConfigOfV(ManaLevelMakeDataConfig.ORE_CONFIG, List.of(
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(frameBasic.name, 1),
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(ManaLevelItemPack.instructions.name, 3),
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(ManaLevelItemPack.power.name, 3),
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(OreItem.file.name, 3),
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.ANVIL.d1(), 1))));
    }

}
