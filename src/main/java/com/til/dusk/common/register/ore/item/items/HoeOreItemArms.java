package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.ore.item.ArmsData;
import com.til.dusk.common.register.ore.item.OreItemArms;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.world.item.CapabilityHoeItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

/**
 * @author til
 */
public class HoeOreItemArms extends OreItemArms {

    public HoeOreItemArms(){
        super("hoe");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "铲");
        lang.add(LangType.EN_CH, "Hoe");
    }

    @Override
    public Item createArmsItem(Ore ore, ArmsData armsData) {
        CapabilityHoeItem item = new CapabilityHoeItem(ore, this, armsData);
        ItemTag.addTag(Tags.Items.TOOLS, item);
        ItemTag.addTag(Tags.Items.TOOLS_SHOVELS, item);
        return item;
    }
}
