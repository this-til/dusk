package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.DecorateBlockData;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class StarIronOre extends Ore {
    public StarIronOre() {
        super("star_iron");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "星云铁");
        lang.add(LangType.EN_CH, "Star Iron");
    }


    @Override
    public void defaultConfig() {
        color = new DuskColor(177, 176, 192);
        manaLevel = ManaLevel.t2;
        isMetal = true;
        hasDust = true;
        isLevelAcceptable = List.of(ManaLevel.t2);
        decorateBlockData = new DecorateBlockData();
        fluidData = new FluidData()
                .setCanCopy(true);
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(this, OreItem.dust), ShapedType.blend, ShapedDrive.get(0), manaLevel)
                        .addInItem(_void.get(OreItem.dust).itemTag(), 1)
                        .addInItem(darkGreen.get(OreItem.dust).itemTag(), 1)
                        .addOutItem(new ItemStack(this.get(OreItem.dust).item(), 2), 1D)
                        .addMultipleSurplusTime((long) consume * 2048L)
                        .addMultipleConsumeMana((long) strength * 32L)));
    }

}

