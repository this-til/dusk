package com.til.dusk.common.register.mana_level.mana_level.mana_levels;

import com.til.dusk.common.config.util.Delayed;
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
public class T2ManaLevel extends ManaLevel {
    public T2ManaLevel() {
        super("t2");
    }

    @Override
    public void defaultConfig() {
        level = 2;
        clock = 1280;
        parallel = 2;
        maxBind = 2;
        manaLoss = 0.09;
        maxRange = 18;
        color = new DuskColor(100, 200, 225);
        operationBasics = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.highEnergyRedStone.get(OreFluid.solution).fluidTag(), 72),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.coolant.get(OreFluid.solution).fluidTag(), 32)));
        operation = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.resistanceTag, 8),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.capacitanceTag, 8),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.diodeTag, 8),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.triodeTag, 8)));
        forming = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(Tags.Items.GEMS_AMETHYST, 4),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.elementAir.get(OreFluid.solution).fluidTag(), 128)));
        destruction = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(Tags.Items.GEMS_QUARTZ, 4),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.elementAir.get(OreFluid.solution).fluidTag(), 128)));
        gather = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(Tags.Items.GEMS_LAPIS, 4),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.sourceAir.get(OreFluid.solution).fluidTag(), 128)));
        spread = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(Tags.Items.GEMS_PRISMARINE, 4),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.sourceAir.get(OreFluid.solution).fluidTag(), 128)));
        power = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(OreItem.rotor.name, 2),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(OreBlock.coil.name, 1),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.natureAir.get(OreFluid.solution).fluidTag(), 128)));
        instructions = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.inductanceTag, 4),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.capacitanceTag, 4),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.natureAir.get(OreFluid.solution).fluidTag(), 128)));
    }
}
