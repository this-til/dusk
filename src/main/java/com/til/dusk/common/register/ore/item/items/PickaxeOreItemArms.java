package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.ore.item.ArmsData;
import com.til.dusk.common.register.ore.item.OreItemArms;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.world.item.CapabilityShovelItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

/**
 * @author til
 */
public class PickaxeOreItemArms extends OreItemArms {

    public PickaxeOreItemArms(){
        super("pickaxe");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "镐");
        lang.add(LangType.EN_CH, "Pickaxe");
    }

    @Override
    public Item createArmsItem(Ore ore, ArmsData armsData) {
        CapabilityShovelItem item = new CapabilityShovelItem(ore, this, armsData);
        ItemTag.addTag(Tags.Items.TOOLS, item);
        ItemTag.addTag(Tags.Items.TOOLS_SHOVELS, item);
        return item;
    }
}
