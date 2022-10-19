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
public class DischantmentManaMechanic extends ExtractManaMechanic {
    public DischantmentManaMechanic() {
        super("dischantment_mana", () -> Set.of(ShapedType.dischantmentMana));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "解咒转灵晶体");
        lang.add(LangType.EN_CH, "Dischantment Mana Crystal");
    }
    @Override
    public void defaultConfig() {
        extractManaColor = new DuskColor(135, 60, 168);
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(extractMana.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ItemIn(() ->ItemTag.ENCHANTING_TABLE.d1(), 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ItemIn(() ->ItemTag.ENCHANTING_BOOK, 3)));
    }
}
