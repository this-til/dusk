package com.til.dusk.common.register.mana_level.block.mechanic.extract_mana;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.ExtractManaMechanic;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.DuskColor;
import net.minecraft.tags.ItemTags;

import java.awt.*;
import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class ElementManaMechanic extends ExtractManaMechanic {

    public ElementManaMechanic() {
        super("element_mana", () -> Set.of(ShapedType.elementMana));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "源质转灵晶体");
        lang.add(LangType.EN_CH, "Element Mana Crystal");
    }

    @Override
    public void defaultConfig() {
        shapedTypeList = List.of(ShapedType.botanyMana);
        extractManaColor = new DuskColor(229, 21, 255);
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(extractMana.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.FluidIn(() -> Ore.elementAir.get(OreFluid.solution).fluidTag(), 8000),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.FluidIn(() -> Ore.ideaAirOre.get(OreFluid.solution).fluidTag(), 8000),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.FluidIn(() -> Ore.sourceAir.get(OreFluid.solution).fluidTag(), 8000),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.FluidIn(() -> Ore.natureAir.get(OreFluid.solution).fluidTag(), 8000)));
    }
}
