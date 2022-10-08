package com.til.dusk.common.register.mana_level.mana_level.mana_levels;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;
import net.minecraftforge.common.Tags;

import java.util.List;

/**
 * @author til
 */
public class T1ManaLevel extends ManaLevel {
    public T1ManaLevel() {
        super("t1");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(ManaLevel.LEVEL, 1)
                .setConfigOfV(ManaLevel.CLOCK, 2560)
                .setConfigOfV(ManaLevel.PARALLEL, 1)
                .setConfigOfV(ManaLevel.MAX_BIND, 2)
                .setConfigOfV(ManaLevel.MANA_LOSS, 0.1)
                .setConfigOfV(ManaLevel.MAX_RANGE, 16)
                .setConfigOfV(ManaLevel.COLOR, new DuskColor(50, 255, 255))
                .setConfig(ManaLevel.OPERATION_BASICS, () -> List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.highEnergyRedStone.get(OreFluid.solution).fluidTag(), 72)))
                .setConfig(ManaLevel.OPERATION, () -> List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.resistanceTag, 4),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.capacitanceTag, 4),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.diodeTag, 4),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.triodeTag, 4)))
                .setConfig(ManaLevel.FORMING, () -> List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(Tags.Items.GEMS_AMETHYST, 2)))
                .setConfig(ManaLevel.DESTRUCTION, () -> List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(Tags.Items.GEMS_QUARTZ, 2)))
                .setConfig(ManaLevel.GATHER, () -> List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(Tags.Items.GEMS_LAPIS, 2)))
                .setConfig(ManaLevel.SPREAD, () -> List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(Tags.Items.GEMS_PRISMARINE, 2)))
                .setConfig(ManaLevel.POWER, () -> List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(OreItem.rotor.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(OreBlock.coil.name, 1)))
                .setConfig(ManaLevel.INSTRUCTIONS, () -> List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.inductanceTag, 2),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.capacitanceTag, 2)
                ));
    }
}
