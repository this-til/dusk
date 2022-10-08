package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemArmor;
import net.minecraft.world.entity.EquipmentSlot;

/**
 * @author til
 */
public class ChestOreItemArmor extends OreItemArmor {
    public ChestOreItemArmor(){
        super("chest", EquipmentSlot.HEAD);
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "胸甲");
        lang.add(LangType.EN_CH, "Chest");
    }
}
