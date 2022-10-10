package com.til.dusk.common.register.shaped.shaped_type;


import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author til
 */
public class MakerStoneShapedType extends ShapedType {

    public MakerStoneShapedType() {
        super("maker_stone", () -> ManaLevelBlock.makerStone);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {

    }

    @Override
    public void defaultConfig() {
        relevantShaped = new Delayed<>(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(),  "stone"),
                        this, ShapedDrive.get(0), ManaLevel.t1)
                        .addInFluid(FluidTags.WATER, 10)
                        .addInFluid(FluidTags.LAVA, 1)
                        .addOutItem(new ItemStack(Blocks.STONE), 1d)
                        .addMultipleSurplusTime(128)
                        .addMultipleConsumeMana(4),
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(), "cobblestone"),
                        this, ShapedDrive.get(1), ManaLevel.t1)
                        .addInFluid(FluidTags.WATER, 10)
                        .addInFluid(FluidTags.LAVA, 1)
                        .addOutItem(new ItemStack(Blocks.COBBLESTONE), 1d)
                        .addMultipleSurplusTime(128)
                        .addMultipleConsumeMana(4),
                new ShapedOre(ResourceLocationUtil.fuseName(name.getNamespace(),  "obsidian"),
                        this, ShapedDrive.get(2), ManaLevel.t1)
                        .addInFluid(FluidTags.WATER, 1000)
                        .addInFluid(FluidTags.LAVA, 1000)
                        .addOutItem(new ItemStack(Blocks.OBSIDIAN), 1d)
                        .addMultipleSurplusTime(512)
                        .addMultipleConsumeMana(4)
        ));
    }
}
