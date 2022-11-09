package com.til.dusk.common.register.mana_level.block.mechanic.handle_mechanic;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelItemGroup;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import net.minecraftforge.common.Tags;

import java.util.List;

/**
 * @author til
 */
public class GrindMechanic extends HandleMechanic {

    public GrindMechanic() {
        super("grind");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "研磨晶体");
        lang.add(LangType.EN_CH, "Grind Crystal");
    }

    @Override
    public void defaultConfig() {shapedTypeList = List.of(ShapedType.grind);
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(frameBasic.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ItemIn(() ->Tags.Items.GEMS_DIAMOND, 2),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemGroup.destruction.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(OreItem.gear.name, 2),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(OreItem.rotor.name, 1)));
    }
}
