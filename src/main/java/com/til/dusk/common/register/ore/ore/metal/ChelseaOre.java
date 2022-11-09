package com.til.dusk.common.register.ore.ore.metal;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.DecorateBlockData;
import com.til.dusk.common.register.ore.block.MineralBlockData;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.item.OreItemDust;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.DuskColor;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ChelseaOre extends Ore {

    public ChelseaOre() {
        super("chelsea");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "切尔西");
        lang.add(LangType.EN_CH, "Chelsea");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(100, 149, 237);
        manaLevel = ManaLevel.t2;
        isMetal = true;
        hasDust = true;
        isLevelAcceptable = List.of(ManaLevel.t2);
        decorateBlockData = new DecorateBlockData();
        fluidData = new FluidData();
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(name, ShapedType.blend, ShapedDrive.get(0), manaLevel)
                        .addInItem(goldenrod.get(OreItemDust.dust).itemTag(), 1)
                        .addInFluid(gas.get(OreFluid.solution).fluidTag(), 32)
                        .addOutItem(new ItemStack(get(OreItem.dust).item(), 1), 1D)
        ));
    }

}
