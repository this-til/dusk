package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemArmor;
import net.minecraft.world.entity.EquipmentSlot;

/**
 * @author til
 */
public class HeadOreItemArmor extends OreItemArmor {
    public HeadOreItemArmor(){
        super("head", EquipmentSlot.HEAD);
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "头盔");
        lang.add(LangType.EN_CH, "Head");
    }
}
