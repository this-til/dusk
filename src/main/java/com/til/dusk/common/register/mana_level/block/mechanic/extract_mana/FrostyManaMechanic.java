package com.til.dusk.common.register.mana_level.block.mechanic.extract_mana;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.block.ExtractManaMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.DuskColor;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class FrostyManaMechanic extends ExtractManaMechanic {
    public FrostyManaMechanic() {
        super("frosty_mana", () -> Set.of(ShapedType.frostyMana));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "寒霜转灵晶体");
        lang.add(LangType.EN_CH, "Frosty Mana Crystal");
    }

    @Override
    public void defaultConfig() {
        extractManaColor = new DuskColor(29, 237, 255);
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(extractMana.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ItemIn(() ->ItemTag.ICES.d1(), 32),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ItemIn(() ->ItemTag.SNOW_BLOCK.d1(), 32)));
    }
}
