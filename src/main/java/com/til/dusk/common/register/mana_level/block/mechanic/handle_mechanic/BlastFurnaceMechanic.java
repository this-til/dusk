package com.til.dusk.common.register.mana_level.block.mechanic.handle_mechanic;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelItemGroup;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;

/**
 * @author til
 */
public class BlastFurnaceMechanic extends HandleMechanic {
    public BlastFurnaceMechanic() {
        super("blast_furnace");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "高炉晶体");
        lang.add(LangType.EN_CH, "Blast Crystal");
    }

    @Override
    public void defaultConfig() {
        shapedTypeList = List.of(ShapedType.blastFurnace);
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(furnace.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemGroup.forming.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(OreBlock.coil.name, 2)));
    }
}
