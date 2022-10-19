package com.til.dusk.common.register.mana_level.block.mechanic.handle_mechanic;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelItemGroup;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import net.minecraft.tags.FluidTags;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class MakerStoneMechanic extends HandleMechanic {

    public MakerStoneMechanic() {
        super("maker_stone", () -> Set.of(ShapedType.makerStone));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "造石晶体");
        lang.add(LangType.EN_CH, "Maker Stone Crystal");
    }

    @Override
    public void defaultConfig() {
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(frameBasic.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemGroup.forming.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.FluidIn(() -> FluidTags.WATER, 16000),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.FluidIn(() -> FluidTags.LAVA, 16000)));
    }
}
