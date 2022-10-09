package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.block.ExtractManaMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.DuskColor;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class EnderManaMechanic extends ExtractManaMechanic {

    public EnderManaMechanic() {
        super("ender_mana", () -> Set.of(ShapedType.enderMana));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "末影转灵晶体");
        lang.add(LangType.EN_CH, "Ender Mana Crystal");
    }

    @Override
    public void defaultConfig() {
        extractManaColor = new DuskColor(96, 22, 96);
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(extractMana.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ItemIn(() ->Tags.Items.ENDER_PEARLS, 8),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ItemIn(() ->ItemTag.ENDER_EYE, 8)));
    }
}
