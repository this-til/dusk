package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.ore.item.OreItemArms;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.world.item.CapabilityAxeItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

/**
 * @author til
 */
public class AxeOreItemArms extends OreItemArms {

    public AxeOreItemArms(){
        super("axe");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "斧");
        lang.add(LangType.EN_CH, "Axe");
    }

    @Override
    public Item createArmsItem(Ore ore, ConfigMap armsData) {
        CapabilityAxeItem item = new CapabilityAxeItem(ore, this, armsData);
        ItemTag.addTag(Tags.Items.TOOLS, item);
        ItemTag.addTag(Tags.Items.TOOLS_SHOVELS, item);
        return item;
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return super.defaultConfigMap()
                .setConfig(IS_AXE);
    }
}
