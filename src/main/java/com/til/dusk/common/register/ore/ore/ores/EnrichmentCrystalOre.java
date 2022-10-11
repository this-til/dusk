package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.DecorateBlockData;
import com.til.dusk.common.register.ore.block.MineralBlockData;
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

/**
 * @author til
 */
public class EnrichmentCrystalOre extends Ore {
    public EnrichmentCrystalOre() {
        super("enrichment_crystal");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "富集西伯利亚蓝色晶体");
        lang.add(LangType.EN_CH, "Enrichment Crystal");
    }


    @Override
    public void defaultConfig() {
        color = new DuskColor(235, 225, 125);
        manaLevel = ManaLevel.t1;
        isCrysta = true;
        hasDust = true;
        isLevelAcceptable = List.of(ManaLevel.t2);
        mineralBlockData = new MineralBlockData()
                .addOrePlacedFeatureConfig(new IOrePlacedFeatureConfig.GenerateData().useDefaultConfig(this, 12, 4));
        decorateBlockData = new DecorateBlockData();
        fluidData = new FluidData();
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(this, OreItem.dust), ShapedType.blend, ShapedDrive.get(0), manaLevel)
                        .addInItem(crystal.get(OreItem.dust).itemTag(), 1)
                        .addInItem(goldenrod.get(OreItem.dust).itemTag(), 1)
                        .addInItem(cotinusCoggygria.get(OreItem.dust).itemTag(), 1)
                        .addOutItem(new ItemStack(this.get(OreItem.dust).item(), 3), 1d)
                        .addMultipleSurplusTime((long) (751L * strength))
                        .addMultipleConsumeMana((long) (15L * consume))));
    }

}

