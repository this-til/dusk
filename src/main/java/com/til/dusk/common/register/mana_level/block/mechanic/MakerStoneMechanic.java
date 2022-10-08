package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import net.minecraft.tags.FluidTags;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class MakerStoneMechanic extends HandleMechanic {

    public MakerStoneMechanic(){
        super("maker_stone", () -> Set.of(ShapedType.makerStone));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "造石晶体");
        lang.add(LangType.EN_CH, "Maker Stone Crystal");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfig(MECHANIC_MAKE_DATA, () -> new ConfigMap()
                        .setConfigOfV(ManaLevelMakeDataConfig.ORE_CONFIG, List.of(
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(frameBasic.name, 1),
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(ManaLevelItemPack.forming.name, 1),
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(FluidTags.WATER, 16000),
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(FluidTags.LAVA, 16000))));
    }

}
