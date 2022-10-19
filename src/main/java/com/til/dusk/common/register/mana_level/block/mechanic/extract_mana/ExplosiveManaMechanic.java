package com.til.dusk.common.register.mana_level.block.mechanic.extract_mana;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
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
public class ExplosiveManaMechanic extends ExtractManaMechanic {

    public ExplosiveManaMechanic() {
        super("explosive_mana", () -> Set.of(ShapedType.explosiveMana));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "爆破转灵晶体");
        lang.add(LangType.EN_CH, "Explosive Mana Crystal");
    }

    @Override
    public void defaultConfig() {
        extractManaColor = new DuskColor(178, 25, 25);
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(extractMana.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ItemIn(() ->ItemTag.TNT.d1(), 4),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ItemIn(() ->Tags.Items.GUNPOWDER, 16)));
    }
}
