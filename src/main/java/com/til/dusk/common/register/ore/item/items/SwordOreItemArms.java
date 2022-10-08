package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.ore.item.OreItemArms;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.world.item.CapabilitySwordItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

/**
 * @author til
 */
public class SwordOreItemArms extends OreItemArms {
    public SwordOreItemArms() {
        super("sword");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "剑");
        lang.add(LangType.EN_CH, "Sword");
    }

    @Override
    public Item createArmsItem(Ore ore, ConfigMap configMap) {
        CapabilitySwordItem item = new CapabilitySwordItem(ore, this, configMap);
        ItemTag.addTag(Tags.Items.TOOLS, item);
        ItemTag.addTag(Tags.Items.TOOLS_SWORDS, item);
        return item;
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return super.defaultConfigMap()
                .setConfig(IS_SWORD);
    }
}
