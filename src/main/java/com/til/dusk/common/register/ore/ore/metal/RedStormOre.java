package com.til.dusk.common.register.ore.ore.metal;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.DecorateBlockData;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.DuskColor;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * @author til
 */
public class RedStormOre extends Ore {

    public RedStormOre() {
        super("red_storm");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "红色风暴");
        lang.add(LangType.EN_CH, "Red Storm");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(255, 0, 0);
        manaLevel = ManaLevel.t3;
        isMetal = true;
        hasDust = true;
        isLevelAcceptable = List.of(ManaLevel.t3);
        decorateBlockData = new DecorateBlockData();
        fluidData = new FluidData();
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(name, ShapedType.blend, ShapedDrive.get(0), manaLevel)
                        .addInItem(chelsea.get(OreItem.dust).itemTag(), 1)
                        .addInItem(cinnabar.get(OreItem.dust).itemTag(), 1)
                        .addInFluid(highEnergyGas.get(OreFluid.solution).fluidTag(), 16)
                        .addOutItem(new ItemStack(get(OreItem.dust).item(), 2), 1D)
        ));
    }
}
