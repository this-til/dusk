package com.til.dusk.common.register.shaped.shaped_type;


import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.data.shaped.Shaped;
import com.til.dusk.common.register.shaped.ShapedDrive;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class MakerStoneShapedType extends ShapedType{

    public MakerStoneShapedType(){
        super("maker_stone", () -> ManaLevelBlock.makerStone);
    }

    @Override
    public void registerSubsidiaryBlack() {
        new Shaped.ShapedOre(
                fuseName(name, Tags.Items.STONE.location()),
                this,
                ShapedDrive.get(0),
                ManaLevel.t1,
                null,
                Map.of(FluidTags.WATER, 10, FluidTags.LAVA, 1),
                128,
                4L,
                0,
                List.of(new ItemStack(Blocks.STONE)),
                null);

        new Shaped.ShapedOre(
                fuseName(name, Tags.Items.COBBLESTONE.location()),
                this,
                ShapedDrive.get(1),
                ManaLevel.t1,
                null,
                Map.of(FluidTags.WATER, 10, FluidTags.LAVA, 1),
                128,
                4L,
                0,
                List.of(new ItemStack(Blocks.COBBLESTONE)),
                null);

        new Shaped.ShapedOre(
                fuseName(name, Tags.Items.OBSIDIAN.location()),
                this,
                ShapedDrive.get(1),
                ManaLevel.t1,
                null,
                Map.of(FluidTags.WATER, 1000, FluidTags.LAVA, 1000),
                512L,
                4L,
                0,
                List.of(new ItemStack(Blocks.OBSIDIAN)),
                null);
    }
}
