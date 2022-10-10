package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.ExtractManaMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.DuskColor;
import net.minecraft.tags.ItemTags;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class BotanyManaMechanic extends ExtractManaMechanic {

    public BotanyManaMechanic() {
        super("botany_mana", () -> Set.of(ShapedType.botanyMana));
    }
    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "植物转灵晶体");
        lang.add(LangType.EN_CH, "Botany Mana Crystal");
    }

    @Override
    public void defaultConfig() {
        extractManaColor = new DuskColor(7, 140, 0);
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(extractMana.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ItemIn(() ->ItemTags.FLOWERS, 256)));
    }
}
