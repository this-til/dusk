package com.til.dusk.common.register.mana_level.block.mechanic;

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
public class HalitosisManaMechanic extends ExtractManaMechanic {

    public HalitosisManaMechanic(){
        super("halitosis_mana", () -> Set.of(ShapedType.halitosisMana));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "口臭转灵晶体");
        lang.add(LangType.EN_CH, "Halitosis Mana Crystal");
    }

    @Override
    public void defaultConfig() {
        extractManaColor = new DuskColor(229, 45, 136);
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(extractMana.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ItemIn(() ->ItemTag.DRAGON_BREATH, 16)));
    }
}
