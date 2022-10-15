package com.til.dusk.common.register.multiblock.multiblocks;

import com.til.dusk.common.register.multiblock.LevelMultiBlock;
import com.til.dusk.common.register.ore.block.OreBlock;
import net.minecraft.core.BlockPos;

import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class T5Accelerate extends LevelMultiBlock {
    public T5Accelerate() {
        super("t5_accelerate");
    }

    @Override
    public void defaultConfig() {
        blockPosPackList = List.of(
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
}
