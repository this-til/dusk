package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.ExtractManaMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.DuskColor;
import net.minecraft.tags.FluidTags;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class FlameManaMechanic extends ExtractManaMechanic {

    public FlameManaMechanic() {
        super("flame_mana", () -> Set.of(ShapedType.flameMana));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "烈焰转灵晶体");
        lang.add(LangType.EN_CH, "Flame Mana Crystal");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(EXTRACT_MANA_COLOR, new DuskColor(255, 0, 0))
                .setConfig(MECHANIC_MAKE_DATA, () -> new ConfigMap()
                        .setConfigOfV(ManaLevelMakeDataConfig.ORE_CONFIG, List.of(
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(extractMana.name, 1),
                                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(FluidTags.LAVA, 32000))));
    }

}
