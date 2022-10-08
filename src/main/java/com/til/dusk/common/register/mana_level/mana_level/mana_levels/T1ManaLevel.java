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
public class T1ManaLevel extends ManaLevel {
    public T1ManaLevel() {
        super("t1");
    }

    @Override
    public void defaultConfig() {
        level = 1;
        clock = 2560;
        parallel = 1;
        maxBind = 2;
        manaLoss = 0.1;
        maxRange = 16;
        color = new DuskColor(50, 255, 255);
        operationBasics = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.highEnergyRedStone.get(OreFluid.solution).fluidTag(), 72)));
        operation = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.resistanceTag, 4),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.capacitanceTag, 4),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.diodeTag, 4),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.triodeTag, 4)));
        forming = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(Tags.Items.GEMS_AMETHYST, 2)));
        destruction = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(Tags.Items.GEMS_QUARTZ, 2)));
        gather = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(Tags.Items.GEMS_LAPIS, 2)));
        spread = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(Tags.Items.GEMS_PRISMARINE, 2)));
        power = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(OreItem.rotor.name, 1), new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelAcceptItemIn(OreBlock.coil.name, 1)));
        instructions = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.inductanceTag, 2), new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemIn(ItemTag.capacitanceTag, 2)));

    }
}
