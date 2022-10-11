package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class HighPressureFuseMechanic extends HandleMechanic {

    public HighPressureFuseMechanic() {
        super("high_pressure_fuse", () -> Set.of(ShapedType.highPressureFuse));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "高压融合");
        lang.add(LangType.EN_CH, "High Pressure Fuse Crystal");
    }

    @Override
    public void defaultConfig() {
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(blastFurnace.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.power.name, 4),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.forming.name, 4),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(OreBlock.coil.name, 4)));
    }
}
