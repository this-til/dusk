package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemArmor;
import net.minecraft.world.entity.EquipmentSlot;

/**
 * @author til
 */
public class LegsOreItemArmor extends OreItemArmor {
    public LegsOreItemArmor(){
        super("legs", EquipmentSlot.HEAD);
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "护腿");
        lang.add(LangType.EN_CH, "Legs");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return super.defaultConfigMap()
                .setConfig(IS_LEGS);
    }
}
