package com.til.dusk.common.register.mana_level.block.mechanic.handle_mechanic;

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
public class SlimeyManaMechanic extends ExtractManaMechanic {

    public SlimeyManaMechanic() {
        super("slimey_mana", () -> Set.of(ShapedType.slimeyMana));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "粘液转灵晶体");
        lang.add(LangType.EN_CH, "Slimey Mana Crystal");
    }

    @Override
    public void defaultConfig() {shapedTypeList = List.of(ShapedType.slimeyMana);
        extractManaColor = new DuskColor(43, 255, 33);
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(extractMana.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ItemIn(() ->ItemTag.SLIME_BALL, 32),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ItemIn(() ->ItemTag.SLIME_BLOCK.d1(), 12)));
    }
}
