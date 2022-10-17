package com.til.dusk.common.register.multiblock.multiblocks;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.multiblock.LevelMultiBlock;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.util.Extension;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class AccelerateMultiBlock extends LevelMultiBlock {

    public AccelerateMultiBlock() {
        super("accelerate");
    }

    @Override
    public void defaultConfig() {
        manaLevelListMap = Map.of(
                ManaLevel.t1, getT1(),
                ManaLevel.t2, getT2(),
                ManaLevel.t3, getT3(),
                ManaLevel.t4, getT4(),
                ManaLevel.t5, getT5(),
                ManaLevel.t6, getT6(),
                ManaLevel.t7, getT7(),
                ManaLevel.t8, getT8());
    }

    @Override
    public List<BlockPosPack> getBlockPosPack(ManaLevel manaLevel) {
        return manaLevelListMap.get(manaLevel);
    }

    public List<BlockPosPack> getT1() {
        return List.of(
                new BlockPosPack(OreBlock.coil.name, null)
                        .addPos(new BlockPos(-2, 0, -2))
                        .addPos(new BlockPos(-2, 0, 2))
                        .addPos(new BlockPos(2, 0, -2))
                        .addPos(new BlockPos(2, 0, 2)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "low", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-2, 0, -1))
                        .addPos(new BlockPos(2, 0, -1)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "low", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-2, 0, 1))
                        .addPos(new BlockPos(2, 0, 1)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "none", "west", "low", "up", "true"))
                        .addPos(new BlockPos(-1, 0, -2))
                        .addPos(new BlockPos(-1, 0, 2)),
                new BlockPosPack(OreBlock.slab.name, Map.of("waterlogged", "false", "type", "bottom"))
                        .addPos(new BlockPos(-1, 0, 0))
                        .addPos(new BlockPos(0, 0, -1))
                        .addPos(new BlockPos(0, 0, 1))
                        .addPos(new BlockPos(1, 0, 0)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "none", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(1, 0, -2))
                        .addPos(new BlockPos(1, 0, 2)));
    }

    public List<BlockPosPack> getT2() {
        return List.of(
                new BlockPosPack(OreBlock.coil.name, null)
                        .addPos(new BlockPos(-2, 0, -1))
                        .addPos(new BlockPos(-2, 0, 1))
                        .addPos(new BlockPos(-1, 0, -2))
                        .addPos(new BlockPos(-1, 0, 2))
                        .addPos(new BlockPos(1, 0, -2))
                        .addPos(new BlockPos(1, 0, 2))
                        .addPos(new BlockPos(2, 0, -1))
                        .addPos(new BlockPos(2, 0, 1)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "low", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-2, 0, -2)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "none", "north", "low", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-2, 0, 2)),
                new BlockPosPack(OreBlock.slab.name, Map.of("waterlogged", "false", "type", "bottom"))
                        .addPos(new BlockPos(-1, 0, 0))
                        .addPos(new BlockPos(0, 0, -1))
                        .addPos(new BlockPos(0, 0, 1))
                        .addPos(new BlockPos(1, 0, 0)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "low", "north", "none", "west", "low", "up", "true"))
                        .addPos(new BlockPos(2, 0, -2)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "low", "west", "low", "up", "true"))
                        .addPos(new BlockPos(2, 0, 2)));
    }

    public List<BlockPosPack> getT3() {
        return List.of(
                new BlockPosPack(OreBlock.coil.name, null)
                        .addPos(new BlockPos(-3, 0, -1))
                        .addPos(new BlockPos(-3, 0, 1))
                        .addPos(new BlockPos(-1, 0, -3))
                        .addPos(new BlockPos(-1, 0, 3))
                        .addPos(new BlockPos(1, 0, -3))
                        .addPos(new BlockPos(1, 0, 3))
                        .addPos(new BlockPos(3, 0, -1))
                        .addPos(new BlockPos(3, 0, 1)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "low", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-3, 0, -2))
                        .addPos(new BlockPos(3, 0, -2)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "low", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-3, 0, 2))
                        .addPos(new BlockPos(3, 0, 2)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "none", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-2, 0, -3))
                        .addPos(new BlockPos(-2, 0, 3)),
                new BlockPosPack(OreBlock.slab.name, Map.of("waterlogged", "false", "type", "bottom"))
                        .addPos(new BlockPos(-1, 0, -1))
                        .addPos(new BlockPos(-1, 0, 0))
                        .addPos(new BlockPos(-1, 0, 1))
                        .addPos(new BlockPos(0, 0, -1))
                        .addPos(new BlockPos(0, 0, 1))
                        .addPos(new BlockPos(1, 0, -1))
                        .addPos(new BlockPos(1, 0, 0))
                        .addPos(new BlockPos(1, 0, 1)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "none", "west", "low", "up", "true"))
                        .addPos(new BlockPos(2, 0, -3))
                        .addPos(new BlockPos(2, 0, 3)));
    }

    public List<BlockPosPack> getT4() {
        return List.of(
                new BlockPosPack(OreBlock.coil.name, null)
                        .addPos(new BlockPos(-3, 0, -2))
                        .addPos(new BlockPos(-3, 0, 2))
                        .addPos(new BlockPos(-2, 0, -3))
                        .addPos(new BlockPos(-2, 0, 3))
                        .addPos(new BlockPos(2, 0, -3))
                        .addPos(new BlockPos(2, 0, 3))
                        .addPos(new BlockPos(3, 0, -2))
                        .addPos(new BlockPos(3, 0, 2)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "low", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-3, 0, -3)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "none", "north", "low", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-3, 0, 3)),
                new BlockPosPack(OreBlock.slab.name, Map.of("waterlogged", "false", "type", "bottom"))
                        .addPos(new BlockPos(-2, 0, 0))
                        .addPos(new BlockPos(0, 0, -2))
                        .addPos(new BlockPos(0, 0, 2))
                        .addPos(new BlockPos(2, 0, 0)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "outer_left", "facing", "south"))
                        .addPos(new BlockPos(-1, 0, -1)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "straight", "facing", "east"))
                        .addPos(new BlockPos(-1, 0, 0)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "outer_left", "facing", "east"))
                        .addPos(new BlockPos(-1, 0, 1)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "straight", "facing", "south"))
                        .addPos(new BlockPos(0, 0, -1)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "straight", "facing", "north"))
                        .addPos(new BlockPos(0, 0, 1)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "outer_left", "facing", "west"))
                        .addPos(new BlockPos(1, 0, -1)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "straight", "facing", "west"))
                        .addPos(new BlockPos(1, 0, 0)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "outer_right", "facing", "west"))
                        .addPos(new BlockPos(1, 0, 1)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "low", "north", "none", "west", "low", "up", "true"))
                        .addPos(new BlockPos(3, 0, -3)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "low", "west", "low", "up", "true"))
                        .addPos(new BlockPos(3, 0, 3)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-3, 1, -2))
                        .addPos(new BlockPos(-3, 1, 2))
                        .addPos(new BlockPos(-2, 1, -3))
                        .addPos(new BlockPos(-2, 1, 3))
                        .addPos(new BlockPos(2, 1, -3))
                        .addPos(new BlockPos(2, 1, 3))
                        .addPos(new BlockPos(3, 1, -2))
                        .addPos(new BlockPos(3, 1, 2)));
    }

    public List<BlockPosPack> getT5() {
        return List.of(
                new BlockPosPack(OreBlock.coil.name, null)
                        .addPos(new BlockPos(-4, 0, -3))
                        .addPos(new BlockPos(-4, 0, -2))
                        .addPos(new BlockPos(-4, 0, 2))
                        .addPos(new BlockPos(-4, 0, 3))
                        .addPos(new BlockPos(-3, 0, -4))
                        .addPos(new BlockPos(-3, 0, 4))
                        .addPos(new BlockPos(-2, 0, -4))
                        .addPos(new BlockPos(-2, 0, 4))
                        .addPos(new BlockPos(2, 0, -4))
                        .addPos(new BlockPos(2, 0, 4))
                        .addPos(new BlockPos(3, 0, -4))
                        .addPos(new BlockPos(3, 0, 4))
                        .addPos(new BlockPos(4, 0, -3))
                        .addPos(new BlockPos(4, 0, -2))
                        .addPos(new BlockPos(4, 0, 2))
                        .addPos(new BlockPos(4, 0, 3)),
                new BlockPosPack(OreBlock.block.name, null)
                        .addPos(new BlockPos(-1, 0, -1))
                        .addPos(new BlockPos(-1, 0, 1))
                        .addPos(new BlockPos(1, 0, -1))
                        .addPos(new BlockPos(1, 0, 1)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "low", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-4, 0, -4)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "none", "north", "low", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-4, 0, 4)),
                new BlockPosPack(OreBlock.slab.name, Map.of("waterlogged", "false", "type", "bottom"))
                        .addPos(new BlockPos(-3, 0, -3))
                        .addPos(new BlockPos(-3, 0, 3))
                        .addPos(new BlockPos(-2, 0, -1))
                        .addPos(new BlockPos(-2, 0, 1))
                        .addPos(new BlockPos(-1, 0, -2))
                        .addPos(new BlockPos(-1, 0, 2))
                        .addPos(new BlockPos(1, 0, -2))
                        .addPos(new BlockPos(1, 0, 2))
                        .addPos(new BlockPos(2, 0, -1))
                        .addPos(new BlockPos(2, 0, 1))
                        .addPos(new BlockPos(3, 0, -3))
                        .addPos(new BlockPos(3, 0, 3)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "low", "north", "low", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-1, 0, 0)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "low", "north", "none", "west", "low", "up", "true"))
                        .addPos(new BlockPos(0, 0, -1)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "none", "north", "low", "west", "low", "up", "true"))
                        .addPos(new BlockPos(0, 0, 1)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "low", "north", "low", "west", "low", "up", "true"))
                        .addPos(new BlockPos(1, 0, 0)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "low", "north", "none", "west", "low", "up", "true"))
                        .addPos(new BlockPos(4, 0, -4)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "low", "west", "low", "up", "true"))
                        .addPos(new BlockPos(4, 0, 4)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-4, 1, -4))
                        .addPos(new BlockPos(-4, 1, 4))
                        .addPos(new BlockPos(4, 1, -4))
                        .addPos(new BlockPos(4, 1, 4)));
    }

    public List<BlockPosPack> getT6() {
        return List.of(
                new BlockPosPack(OreBlock.coil.name, null)
                        .addPos(new BlockPos(-4, 0, -3))
                        .addPos(new BlockPos(-4, 0, -2))
                        .addPos(new BlockPos(-4, 0, 2))
                        .addPos(new BlockPos(-4, 0, 3))
                        .addPos(new BlockPos(-3, 0, -4))
                        .addPos(new BlockPos(-3, 0, 4))
                        .addPos(new BlockPos(-2, 0, -4))
                        .addPos(new BlockPos(-2, 0, 4))
                        .addPos(new BlockPos(2, 0, -4))
                        .addPos(new BlockPos(2, 0, 4))
                        .addPos(new BlockPos(3, 0, -4))
                        .addPos(new BlockPos(3, 0, 4))
                        .addPos(new BlockPos(4, 0, -3))
                        .addPos(new BlockPos(4, 0, -2))
                        .addPos(new BlockPos(4, 0, 2))
                        .addPos(new BlockPos(4, 0, 3)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "none", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-5, 0, -2))
                        .addPos(new BlockPos(-5, 0, 2)),
                new BlockPosPack(OreBlock.slab.name, Map.of("waterlogged", "false", "type", "bottom"))
                        .addPos(new BlockPos(-4, 0, -1))
                        .addPos(new BlockPos(-4, 0, 1))
                        .addPos(new BlockPos(-2, 0, -1))
                        .addPos(new BlockPos(-2, 0, 0))
                        .addPos(new BlockPos(-2, 0, 1))
                        .addPos(new BlockPos(-1, 0, -4))
                        .addPos(new BlockPos(-1, 0, -2))
                        .addPos(new BlockPos(-1, 0, 2))
                        .addPos(new BlockPos(-1, 0, 4))
                        .addPos(new BlockPos(0, 0, -2))
                        .addPos(new BlockPos(0, 0, 2))
                        .addPos(new BlockPos(1, 0, -4))
                        .addPos(new BlockPos(1, 0, -2))
                        .addPos(new BlockPos(1, 0, 2))
                        .addPos(new BlockPos(1, 0, 4))
                        .addPos(new BlockPos(2, 0, -1))
                        .addPos(new BlockPos(2, 0, 0))
                        .addPos(new BlockPos(2, 0, 1))
                        .addPos(new BlockPos(4, 0, -1))
                        .addPos(new BlockPos(4, 0, 1)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "low", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-2, 0, -5))
                        .addPos(new BlockPos(2, 0, -5)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "low", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-2, 0, 5))
                        .addPos(new BlockPos(2, 0, 5)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "outer_left", "facing", "south"))
                        .addPos(new BlockPos(-1, 0, -1)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "straight", "facing", "east"))
                        .addPos(new BlockPos(-1, 0, 0)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "outer_left", "facing", "east"))
                        .addPos(new BlockPos(-1, 0, 1)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "straight", "facing", "south"))
                        .addPos(new BlockPos(0, 0, -1)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "straight", "facing", "north"))
                        .addPos(new BlockPos(0, 0, 1)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "outer_left", "facing", "west"))
                        .addPos(new BlockPos(1, 0, -1)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "straight", "facing", "west"))
                        .addPos(new BlockPos(1, 0, 0)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "outer_right", "facing", "west"))
                        .addPos(new BlockPos(1, 0, 1)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "none", "west", "low", "up", "true"))
                        .addPos(new BlockPos(5, 0, -2))
                        .addPos(new BlockPos(5, 0, 2)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-4, 1, -2))
                        .addPos(new BlockPos(-4, 1, 2))
                        .addPos(new BlockPos(-2, 1, -4))
                        .addPos(new BlockPos(-2, 1, 4))
                        .addPos(new BlockPos(2, 1, -4))
                        .addPos(new BlockPos(2, 1, 4))
                        .addPos(new BlockPos(4, 1, -2))
                        .addPos(new BlockPos(4, 1, 2)));
    }

    public List<BlockPosPack> getT7() {
        return List.of(
                new BlockPosPack(OreBlock.coil.name, null)
                        .addPos(new BlockPos(-5, 0, -2))
                        .addPos(new BlockPos(-5, 0, 2))
                        .addPos(new BlockPos(-4, 0, -3))
                        .addPos(new BlockPos(-4, 0, 3))
                        .addPos(new BlockPos(-3, 0, -4))
                        .addPos(new BlockPos(-3, 0, 4))
                        .addPos(new BlockPos(-2, 0, -5))
                        .addPos(new BlockPos(-2, 0, 5))
                        .addPos(new BlockPos(2, 0, -5))
                        .addPos(new BlockPos(2, 0, 5))
                        .addPos(new BlockPos(3, 0, -4))
                        .addPos(new BlockPos(3, 0, 4))
                        .addPos(new BlockPos(4, 0, -3))
                        .addPos(new BlockPos(4, 0, 3))
                        .addPos(new BlockPos(5, 0, -2))
                        .addPos(new BlockPos(5, 0, 2)),
                new BlockPosPack(OreBlock.block.name, null)
                        .addPos(new BlockPos(-1, 0, 0))
                        .addPos(new BlockPos(0, 0, -1))
                        .addPos(new BlockPos(0, 0, 1))
                        .addPos(new BlockPos(1, 0, 0)),
                new BlockPosPack(OreBlock.slab.name, Map.of("waterlogged", "false", "type", "bottom"))
                        .addPos(new BlockPos(-5, 0, -1))
                        .addPos(new BlockPos(-5, 0, 1))
                        .addPos(new BlockPos(-3, 0, -3))
                        .addPos(new BlockPos(-3, 0, 3))
                        .addPos(new BlockPos(-1, 0, -5))
                        .addPos(new BlockPos(-1, 0, -1))
                        .addPos(new BlockPos(-1, 0, 1))
                        .addPos(new BlockPos(-1, 0, 5))
                        .addPos(new BlockPos(1, 0, -5))
                        .addPos(new BlockPos(1, 0, -1))
                        .addPos(new BlockPos(1, 0, 1))
                        .addPos(new BlockPos(1, 0, 5))
                        .addPos(new BlockPos(3, 0, -3))
                        .addPos(new BlockPos(3, 0, 3))
                        .addPos(new BlockPos(5, 0, -1))
                        .addPos(new BlockPos(5, 0, 1)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "low", "west", "low", "up", "true"))
                        .addPos(new BlockPos(-4, 0, -2))
                        .addPos(new BlockPos(-2, 0, -4)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "low", "north", "none", "west", "low", "up", "true"))
                        .addPos(new BlockPos(-4, 0, 2))
                        .addPos(new BlockPos(-2, 0, 4)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-2, 0, -1))
                        .addPos(new BlockPos(-2, 0, 1))
                        .addPos(new BlockPos(-1, 0, -2))
                        .addPos(new BlockPos(-1, 0, 2))
                        .addPos(new BlockPos(1, 0, -2))
                        .addPos(new BlockPos(1, 0, 2))
                        .addPos(new BlockPos(2, 0, -1))
                        .addPos(new BlockPos(2, 0, 1))
                        .addPos(new BlockPos(-4, 1, -3))
                        .addPos(new BlockPos(-4, 1, 3))
                        .addPos(new BlockPos(-3, 1, -4))
                        .addPos(new BlockPos(-3, 1, 4))
                        .addPos(new BlockPos(3, 1, -4))
                        .addPos(new BlockPos(3, 1, 4))
                        .addPos(new BlockPos(4, 1, -3))
                        .addPos(new BlockPos(4, 1, 3)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "straight", "facing", "east"))
                        .addPos(new BlockPos(-2, 0, 0)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "straight", "facing", "south"))
                        .addPos(new BlockPos(0, 0, -2)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "straight", "facing", "north"))
                        .addPos(new BlockPos(0, 0, 2)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "none", "north", "low", "west", "none", "up", "true"))
                        .addPos(new BlockPos(2, 0, -4))
                        .addPos(new BlockPos(4, 0, -2)),
                new BlockPosPack(OreBlock.stairs.name, Map.of("half", "bottom", "waterlogged", "false", "shape", "straight", "facing", "west"))
                        .addPos(new BlockPos(2, 0, 0)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "low", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(2, 0, 4))
                        .addPos(new BlockPos(4, 0, 2)));
    }

    public List<BlockPosPack> getT8() {
        return List.of(
                new BlockPosPack(OreBlock.coil.name, null)
                        .addPos(new BlockPos(-9, -1, -8))
                        .addPos(new BlockPos(-9, -1, -6))
                        .addPos(new BlockPos(-9, -1, -5))
                        .addPos(new BlockPos(-9, -1, -4))
                        .addPos(new BlockPos(-9, -1, -3))
                        .addPos(new BlockPos(-9, -1, 3))
                        .addPos(new BlockPos(-9, -1, 4))
                        .addPos(new BlockPos(-9, -1, 5))
                        .addPos(new BlockPos(-9, -1, 6))
                        .addPos(new BlockPos(-9, -1, 8))
                        .addPos(new BlockPos(-8, -1, -9))
                        .addPos(new BlockPos(-8, -1, -7))
                        .addPos(new BlockPos(-8, -1, 7))
                        .addPos(new BlockPos(-8, -1, 9))
                        .addPos(new BlockPos(-7, -1, -8))
                        .addPos(new BlockPos(-7, -1, 8))
                        .addPos(new BlockPos(-6, -1, -9))
                        .addPos(new BlockPos(-6, -1, 9))
                        .addPos(new BlockPos(-5, -1, -9))
                        .addPos(new BlockPos(-5, -1, 9))
                        .addPos(new BlockPos(-4, -1, -9))
                        .addPos(new BlockPos(-4, -1, 9))
                        .addPos(new BlockPos(-3, -1, -9))
                        .addPos(new BlockPos(-3, -1, 9))
                        .addPos(new BlockPos(-2, -1, 0))
                        .addPos(new BlockPos(-1, -1, -1))
                        .addPos(new BlockPos(-1, -1, 1))
                        .addPos(new BlockPos(0, -1, -2))
                        .addPos(new BlockPos(0, -1, 2))
                        .addPos(new BlockPos(1, -1, -1))
                        .addPos(new BlockPos(1, -1, 1))
                        .addPos(new BlockPos(2, -1, 0))
                        .addPos(new BlockPos(3, -1, -9))
                        .addPos(new BlockPos(3, -1, 9))
                        .addPos(new BlockPos(4, -1, -9))
                        .addPos(new BlockPos(4, -1, 9))
                        .addPos(new BlockPos(5, -1, -9))
                        .addPos(new BlockPos(5, -1, 9))
                        .addPos(new BlockPos(6, -1, -9))
                        .addPos(new BlockPos(6, -1, 9))
                        .addPos(new BlockPos(7, -1, -8))
                        .addPos(new BlockPos(7, -1, 8))
                        .addPos(new BlockPos(8, -1, -9))
                        .addPos(new BlockPos(8, -1, -7))
                        .addPos(new BlockPos(8, -1, 7))
                        .addPos(new BlockPos(8, -1, 9))
                        .addPos(new BlockPos(9, -1, -8))
                        .addPos(new BlockPos(9, -1, -6))
                        .addPos(new BlockPos(9, -1, -5))
                        .addPos(new BlockPos(9, -1, -4))
                        .addPos(new BlockPos(9, -1, -3))
                        .addPos(new BlockPos(9, -1, 3))
                        .addPos(new BlockPos(9, -1, 4))
                        .addPos(new BlockPos(9, -1, 5))
                        .addPos(new BlockPos(9, -1, 6))
                        .addPos(new BlockPos(9, -1, 8))
                        .addPos(new BlockPos(-9, 0, -8))
                        .addPos(new BlockPos(-9, 0, -3))
                        .addPos(new BlockPos(-9, 0, 3))
                        .addPos(new BlockPos(-9, 0, 8))
                        .addPos(new BlockPos(-8, 0, -9))
                        .addPos(new BlockPos(-8, 0, -7))
                        .addPos(new BlockPos(-8, 0, 7))
                        .addPos(new BlockPos(-8, 0, 9))
                        .addPos(new BlockPos(-7, 0, -8))
                        .addPos(new BlockPos(-7, 0, 8))
                        .addPos(new BlockPos(-3, 0, -9))
                        .addPos(new BlockPos(-3, 0, 9))
                        .addPos(new BlockPos(3, 0, -9))
                        .addPos(new BlockPos(3, 0, 9))
                        .addPos(new BlockPos(7, 0, -8))
                        .addPos(new BlockPos(7, 0, 8))
                        .addPos(new BlockPos(8, 0, -9))
                        .addPos(new BlockPos(8, 0, -7))
                        .addPos(new BlockPos(8, 0, 7))
                        .addPos(new BlockPos(8, 0, 9))
                        .addPos(new BlockPos(9, 0, -8))
                        .addPos(new BlockPos(9, 0, -3))
                        .addPos(new BlockPos(9, 0, 3))
                        .addPos(new BlockPos(9, 0, 8)),
                new BlockPosPack(OreBlock.block.name, null)
                        .addPos(new BlockPos(-9, -1, -2))
                        .addPos(new BlockPos(-9, -1, 2))
                        .addPos(new BlockPos(-8, -1, -3))
                        .addPos(new BlockPos(-8, -1, 3))
                        .addPos(new BlockPos(-4, -1, -3))
                        .addPos(new BlockPos(-4, -1, 0))
                        .addPos(new BlockPos(-4, -1, 3))
                        .addPos(new BlockPos(-3, -1, -10))
                        .addPos(new BlockPos(-3, -1, -8))
                        .addPos(new BlockPos(-3, -1, -4))
                        .addPos(new BlockPos(-3, -1, -2))
                        .addPos(new BlockPos(-3, -1, -1))
                        .addPos(new BlockPos(-3, -1, 0))
                        .addPos(new BlockPos(-3, -1, 1))
                        .addPos(new BlockPos(-3, -1, 2))
                        .addPos(new BlockPos(-3, -1, 4))
                        .addPos(new BlockPos(-3, -1, 8))
                        .addPos(new BlockPos(-3, -1, 10))
                        .addPos(new BlockPos(-2, -1, -9))
                        .addPos(new BlockPos(-2, -1, -3))
                        .addPos(new BlockPos(-2, -1, -2))
                        .addPos(new BlockPos(-2, -1, 2))
                        .addPos(new BlockPos(-2, -1, 3))
                        .addPos(new BlockPos(-2, -1, 9))
                        .addPos(new BlockPos(-1, -1, -3))
                        .addPos(new BlockPos(-1, -1, 3))
                        .addPos(new BlockPos(0, -1, -4))
                        .addPos(new BlockPos(0, -1, -3))
                        .addPos(new BlockPos(0, -1, 0))
                        .addPos(new BlockPos(0, -1, 3))
                        .addPos(new BlockPos(0, -1, 4))
                        .addPos(new BlockPos(1, -1, -3))
                        .addPos(new BlockPos(1, -1, 3))
                        .addPos(new BlockPos(2, -1, -9))
                        .addPos(new BlockPos(2, -1, -3))
                        .addPos(new BlockPos(2, -1, -2))
                        .addPos(new BlockPos(2, -1, 2))
                        .addPos(new BlockPos(2, -1, 3))
                        .addPos(new BlockPos(2, -1, 9))
                        .addPos(new BlockPos(3, -1, -10))
                        .addPos(new BlockPos(3, -1, -8))
                        .addPos(new BlockPos(3, -1, -4))
                        .addPos(new BlockPos(3, -1, -2))
                        .addPos(new BlockPos(3, -1, -1))
                        .addPos(new BlockPos(3, -1, 0))
                        .addPos(new BlockPos(3, -1, 1))
                        .addPos(new BlockPos(3, -1, 2))
                        .addPos(new BlockPos(3, -1, 4))
                        .addPos(new BlockPos(3, -1, 8))
                        .addPos(new BlockPos(3, -1, 10))
                        .addPos(new BlockPos(4, -1, -3))
                        .addPos(new BlockPos(4, -1, 0))
                        .addPos(new BlockPos(4, -1, 3))
                        .addPos(new BlockPos(8, -1, -3))
                        .addPos(new BlockPos(8, -1, 3))
                        .addPos(new BlockPos(9, -1, -2))
                        .addPos(new BlockPos(9, -1, 2))
                        .addPos(new BlockPos(10, -1, -3))
                        .addPos(new BlockPos(10, -1, 3)),
                new BlockPosPack(OreBlock.slab.name, Map.of("waterlogged", "false", "type", "bottom"))
                        .addPos(new BlockPos(-9, -1, -9))
                        .addPos(new BlockPos(-9, -1, -7))
                        .addPos(new BlockPos(-9, -1, 7))
                        .addPos(new BlockPos(-9, -1, 9))
                        .addPos(new BlockPos(-8, -1, -10))
                        .addPos(new BlockPos(-8, -1, -5))
                        .addPos(new BlockPos(-8, -1, -4))
                        .addPos(new BlockPos(-8, -1, -2))
                        .addPos(new BlockPos(-8, -1, 2))
                        .addPos(new BlockPos(-8, -1, 4))
                        .addPos(new BlockPos(-8, -1, 5))
                        .addPos(new BlockPos(-8, -1, 10))
                        .addPos(new BlockPos(-7, -1, -10))
                        .addPos(new BlockPos(-7, -1, -9))
                        .addPos(new BlockPos(-7, -1, 9))
                        .addPos(new BlockPos(-7, -1, 10))
                        .addPos(new BlockPos(-6, -1, -10))
                        .addPos(new BlockPos(-6, -1, 10))
                        .addPos(new BlockPos(-5, -1, -10))
                        .addPos(new BlockPos(-5, -1, -8))
                        .addPos(new BlockPos(-5, -1, -3))
                        .addPos(new BlockPos(-5, -1, -2))
                        .addPos(new BlockPos(-5, -1, -1))
                        .addPos(new BlockPos(-5, -1, 0))
                        .addPos(new BlockPos(-5, -1, 1))
                        .addPos(new BlockPos(-5, -1, 2))
                        .addPos(new BlockPos(-5, -1, 3))
                        .addPos(new BlockPos(-5, -1, 8))
                        .addPos(new BlockPos(-5, -1, 10))
                        .addPos(new BlockPos(-4, -1, -10))
                        .addPos(new BlockPos(-4, -1, -8))
                        .addPos(new BlockPos(-4, -1, -4))
                        .addPos(new BlockPos(-4, -1, -2))
                        .addPos(new BlockPos(-4, -1, -1))
                        .addPos(new BlockPos(-4, -1, 1))
                        .addPos(new BlockPos(-4, -1, 2))
                        .addPos(new BlockPos(-4, -1, 4))
                        .addPos(new BlockPos(-4, -1, 8))
                        .addPos(new BlockPos(-4, -1, 10))
                        .addPos(new BlockPos(-3, -1, -5))
                        .addPos(new BlockPos(-3, -1, 5))
                        .addPos(new BlockPos(-2, -1, -10))
                        .addPos(new BlockPos(-2, -1, -8))
                        .addPos(new BlockPos(-2, -1, -5))
                        .addPos(new BlockPos(-2, -1, -4))
                        .addPos(new BlockPos(-2, -1, 4))
                        .addPos(new BlockPos(-2, -1, 5))
                        .addPos(new BlockPos(-2, -1, 8))
                        .addPos(new BlockPos(-2, -1, 10))
                        .addPos(new BlockPos(-1, -1, -5))
                        .addPos(new BlockPos(-1, -1, -4))
                        .addPos(new BlockPos(-1, -1, 4))
                        .addPos(new BlockPos(-1, -1, 5))
                        .addPos(new BlockPos(0, -1, -5))
                        .addPos(new BlockPos(0, -1, 5))
                        .addPos(new BlockPos(1, -1, -5))
                        .addPos(new BlockPos(1, -1, -4))
                        .addPos(new BlockPos(1, -1, 4))
                        .addPos(new BlockPos(1, -1, 5))
                        .addPos(new BlockPos(2, -1, -10))
                        .addPos(new BlockPos(2, -1, -8))
                        .addPos(new BlockPos(2, -1, -5))
                        .addPos(new BlockPos(2, -1, -4))
                        .addPos(new BlockPos(2, -1, 4))
                        .addPos(new BlockPos(2, -1, 5))
                        .addPos(new BlockPos(2, -1, 8))
                        .addPos(new BlockPos(2, -1, 10))
                        .addPos(new BlockPos(3, -1, -5))
                        .addPos(new BlockPos(3, -1, 5))
                        .addPos(new BlockPos(4, -1, -10))
                        .addPos(new BlockPos(4, -1, -8))
                        .addPos(new BlockPos(4, -1, -4))
                        .addPos(new BlockPos(4, -1, -2))
                        .addPos(new BlockPos(4, -1, -1))
                        .addPos(new BlockPos(4, -1, 1))
                        .addPos(new BlockPos(4, -1, 2))
                        .addPos(new BlockPos(4, -1, 4))
                        .addPos(new BlockPos(4, -1, 8))
                        .addPos(new BlockPos(4, -1, 10))
                        .addPos(new BlockPos(5, -1, -10))
                        .addPos(new BlockPos(5, -1, -8))
                        .addPos(new BlockPos(5, -1, -3))
                        .addPos(new BlockPos(5, -1, -2))
                        .addPos(new BlockPos(5, -1, -1))
                        .addPos(new BlockPos(5, -1, 0))
                        .addPos(new BlockPos(5, -1, 1))
                        .addPos(new BlockPos(5, -1, 2))
                        .addPos(new BlockPos(5, -1, 3))
                        .addPos(new BlockPos(5, -1, 8))
                        .addPos(new BlockPos(5, -1, 10))
                        .addPos(new BlockPos(6, -1, -10))
                        .addPos(new BlockPos(6, -1, 10))
                        .addPos(new BlockPos(7, -1, -10))
                        .addPos(new BlockPos(7, -1, -9))
                        .addPos(new BlockPos(7, -1, 9))
                        .addPos(new BlockPos(7, -1, 10))
                        .addPos(new BlockPos(8, -1, -10))
                        .addPos(new BlockPos(8, -1, -5))
                        .addPos(new BlockPos(8, -1, -4))
                        .addPos(new BlockPos(8, -1, -2))
                        .addPos(new BlockPos(8, -1, 2))
                        .addPos(new BlockPos(8, -1, 4))
                        .addPos(new BlockPos(8, -1, 5))
                        .addPos(new BlockPos(8, -1, 10))
                        .addPos(new BlockPos(9, -1, -9))
                        .addPos(new BlockPos(9, -1, -7))
                        .addPos(new BlockPos(9, -1, 7))
                        .addPos(new BlockPos(9, -1, 9))
                        .addPos(new BlockPos(10, -1, -8))
                        .addPos(new BlockPos(10, -1, -7))
                        .addPos(new BlockPos(10, -1, -6))
                        .addPos(new BlockPos(10, -1, -5))
                        .addPos(new BlockPos(10, -1, -4))
                        .addPos(new BlockPos(10, -1, -2))
                        .addPos(new BlockPos(10, -1, 2))
                        .addPos(new BlockPos(10, -1, 4))
                        .addPos(new BlockPos(10, -1, 5))
                        .addPos(new BlockPos(10, -1, 6))
                        .addPos(new BlockPos(10, -1, 7))
                        .addPos(new BlockPos(10, -1, 8))
                        .addPos(new BlockPos(-3, 0, -2))
                        .addPos(new BlockPos(-3, 0, 2))
                        .addPos(new BlockPos(-2, 0, -3))
                        .addPos(new BlockPos(-2, 0, 0))
                        .addPos(new BlockPos(-2, 0, 3))
                        .addPos(new BlockPos(-1, 0, -1))
                        .addPos(new BlockPos(-1, 0, 1))
                        .addPos(new BlockPos(0, 0, -2))
                        .addPos(new BlockPos(0, 0, 2))
                        .addPos(new BlockPos(1, 0, -1))
                        .addPos(new BlockPos(1, 0, 1))
                        .addPos(new BlockPos(2, 0, -3))
                        .addPos(new BlockPos(2, 0, 0))
                        .addPos(new BlockPos(2, 0, 3))
                        .addPos(new BlockPos(3, 0, -2))
                        .addPos(new BlockPos(3, 0, 2)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "tall", "waterlogged", "false", "south", "tall", "north", "tall", "west", "tall", "up", "true"))
                        .addPos(new BlockPos(-8, -1, -8))
                        .addPos(new BlockPos(-8, -1, 8))
                        .addPos(new BlockPos(8, -1, -8))
                        .addPos(new BlockPos(8, -1, 8)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "low", "west", "low", "up", "true"))
                        .addPos(new BlockPos(-8, -1, -6))
                        .addPos(new BlockPos(-6, -1, -8)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "low", "north", "none", "west", "low", "up", "true"))
                        .addPos(new BlockPos(-8, -1, 6))
                        .addPos(new BlockPos(-6, -1, 8)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "low", "north", "low", "west", "low", "up", "true"))
                        .addPos(new BlockPos(-3, -1, -3))
                        .addPos(new BlockPos(-3, -1, 3))
                        .addPos(new BlockPos(-2, -1, -1))
                        .addPos(new BlockPos(-2, -1, 1))
                        .addPos(new BlockPos(-1, -1, -2))
                        .addPos(new BlockPos(-1, -1, 2))
                        .addPos(new BlockPos(1, -1, -2))
                        .addPos(new BlockPos(1, -1, 2))
                        .addPos(new BlockPos(2, -1, -1))
                        .addPos(new BlockPos(2, -1, 1))
                        .addPos(new BlockPos(3, -1, -3))
                        .addPos(new BlockPos(3, -1, 3))
                        .addPos(new BlockPos(-8, 0, -8))
                        .addPos(new BlockPos(-8, 0, 8))
                        .addPos(new BlockPos(8, 0, -8))
                        .addPos(new BlockPos(8, 0, 8)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "tall", "waterlogged", "false", "south", "low", "north", "low", "west", "low", "up", "true"))
                        .addPos(new BlockPos(-1, -1, 0)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "tall", "north", "low", "west", "low", "up", "true"))
                        .addPos(new BlockPos(0, -1, -1)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "low", "north", "tall", "west", "low", "up", "true"))
                        .addPos(new BlockPos(0, -1, 1)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "low", "north", "low", "west", "tall", "up", "true"))
                        .addPos(new BlockPos(1, -1, 0)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "none", "north", "low", "west", "none", "up", "true"))
                        .addPos(new BlockPos(6, -1, -8))
                        .addPos(new BlockPos(8, -1, -6))
                        .addPos(new BlockPos(7, 0, -7)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "low", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(6, -1, 8))
                        .addPos(new BlockPos(8, -1, 6)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "tall", "waterlogged", "false", "south", "none", "north", "tall", "west", "none", "up", "true"))
                        .addPos(new BlockPos(7, -1, -7)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-9, 0, -6))
                        .addPos(new BlockPos(-9, 0, 6))
                        .addPos(new BlockPos(-6, 0, -9))
                        .addPos(new BlockPos(-6, 0, 9))
                        .addPos(new BlockPos(-3, 0, -3))
                        .addPos(new BlockPos(-3, 0, 3))
                        .addPos(new BlockPos(-2, 0, -1))
                        .addPos(new BlockPos(-2, 0, 1))
                        .addPos(new BlockPos(-1, 0, -2))
                        .addPos(new BlockPos(-1, 0, 2))
                        .addPos(new BlockPos(1, 0, -2))
                        .addPos(new BlockPos(1, 0, 2))
                        .addPos(new BlockPos(2, 0, -1))
                        .addPos(new BlockPos(2, 0, 1))
                        .addPos(new BlockPos(3, 0, -3))
                        .addPos(new BlockPos(3, 0, 3))
                        .addPos(new BlockPos(6, 0, -9))
                        .addPos(new BlockPos(6, 0, 9))
                        .addPos(new BlockPos(9, 0, -6))
                        .addPos(new BlockPos(9, 0, 6))
                        .addPos(new BlockPos(-9, 1, -3))
                        .addPos(new BlockPos(-9, 1, 3))
                        .addPos(new BlockPos(-8, 1, -8))
                        .addPos(new BlockPos(-8, 1, 8))
                        .addPos(new BlockPos(-3, 1, -9))
                        .addPos(new BlockPos(-3, 1, -3))
                        .addPos(new BlockPos(-3, 1, 3))
                        .addPos(new BlockPos(-3, 1, 9))
                        .addPos(new BlockPos(-2, 1, -1))
                        .addPos(new BlockPos(-2, 1, 1))
                        .addPos(new BlockPos(-1, 1, -2))
                        .addPos(new BlockPos(-1, 1, 2))
                        .addPos(new BlockPos(1, 1, -2))
                        .addPos(new BlockPos(1, 1, 2))
                        .addPos(new BlockPos(2, 1, -1))
                        .addPos(new BlockPos(2, 1, 1))
                        .addPos(new BlockPos(3, 1, -9))
                        .addPos(new BlockPos(3, 1, -3))
                        .addPos(new BlockPos(3, 1, 3))
                        .addPos(new BlockPos(3, 1, 9))
                        .addPos(new BlockPos(8, 1, -8))
                        .addPos(new BlockPos(8, 1, 8))
                        .addPos(new BlockPos(9, 1, -3))
                        .addPos(new BlockPos(9, 1, 3))
                        .addPos(new BlockPos(-9, 2, -3))
                        .addPos(new BlockPos(-9, 2, 3))
                        .addPos(new BlockPos(-8, 2, -8))
                        .addPos(new BlockPos(-8, 2, 8))
                        .addPos(new BlockPos(-3, 2, -9))
                        .addPos(new BlockPos(-3, 2, -3))
                        .addPos(new BlockPos(-3, 2, 3))
                        .addPos(new BlockPos(-3, 2, 9))
                        .addPos(new BlockPos(3, 2, -9))
                        .addPos(new BlockPos(3, 2, -3))
                        .addPos(new BlockPos(3, 2, 3))
                        .addPos(new BlockPos(3, 2, 9))
                        .addPos(new BlockPos(8, 2, -8))
                        .addPos(new BlockPos(8, 2, 8))
                        .addPos(new BlockPos(9, 2, -3))
                        .addPos(new BlockPos(9, 2, 3))
                        .addPos(new BlockPos(-8, 3, -8))
                        .addPos(new BlockPos(-8, 3, 8))
                        .addPos(new BlockPos(8, 3, -8))
                        .addPos(new BlockPos(8, 3, 8)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "low", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-9, 0, -4))
                        .addPos(new BlockPos(-9, 0, 2))
                        .addPos(new BlockPos(-3, 0, -10))
                        .addPos(new BlockPos(-3, 0, 8))
                        .addPos(new BlockPos(0, 0, -1))
                        .addPos(new BlockPos(3, 0, -10))
                        .addPos(new BlockPos(3, 0, 8))
                        .addPos(new BlockPos(9, 0, -4))
                        .addPos(new BlockPos(9, 0, 2)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "low", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-9, 0, -2))
                        .addPos(new BlockPos(-9, 0, 4))
                        .addPos(new BlockPos(-3, 0, -8))
                        .addPos(new BlockPos(-3, 0, 10))
                        .addPos(new BlockPos(0, 0, 1))
                        .addPos(new BlockPos(3, 0, -8))
                        .addPos(new BlockPos(3, 0, 10))
                        .addPos(new BlockPos(9, 0, -2))
                        .addPos(new BlockPos(9, 0, 4)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "none", "waterlogged", "false", "south", "none", "north", "none", "west", "low", "up", "true"))
                        .addPos(new BlockPos(-8, 0, -3))
                        .addPos(new BlockPos(-8, 0, 3))
                        .addPos(new BlockPos(-2, 0, -9))
                        .addPos(new BlockPos(-2, 0, 9))
                        .addPos(new BlockPos(1, 0, 0))
                        .addPos(new BlockPos(4, 0, -9))
                        .addPos(new BlockPos(4, 0, 9))
                        .addPos(new BlockPos(10, 0, -3))
                        .addPos(new BlockPos(10, 0, 3)),
                new BlockPosPack(OreBlock.wall.name, Map.of("east", "low", "waterlogged", "false", "south", "none", "north", "none", "west", "none", "up", "true"))
                        .addPos(new BlockPos(-4, 0, -9))
                        .addPos(new BlockPos(-4, 0, 9))
                        .addPos(new BlockPos(-1, 0, 0))
                        .addPos(new BlockPos(2, 0, -9))
                        .addPos(new BlockPos(2, 0, 9))
                        .addPos(new BlockPos(8, 0, -3))
                        .addPos(new BlockPos(8, 0, 3)));
    }

    @ConfigField
    public Map<ManaLevel, List<BlockPosPack>> manaLevelListMap;

}
