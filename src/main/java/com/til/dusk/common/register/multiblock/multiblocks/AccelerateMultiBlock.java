package com.til.dusk.common.register.multiblock.multiblocks;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.multiblock.DefaultBlockPack;
import com.til.dusk.common.register.multiblock.IMultiBlockPack;
import com.til.dusk.common.register.multiblock.LevelMultiBlock;
import com.til.dusk.common.register.ore.block.OreBlock;
import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.HashMap;
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
    public List<DefaultBlockPack> asDefaultBlockPack(ManaLevel manaLevel) {
        return defaultBlockPackMap.get(manaLevel);
    }

    @Override
    public List<IMultiBlockPack<ManaLevel>> getMultiBlockPack(ManaLevel manaLevel) {
        return manaLevelListMap.get(manaLevel);
    }

    @Override
    protected void registerBlackToBack() {
        defaultBlockPackMap = new HashMap<>();
        for (Map.Entry<ManaLevel, List<IMultiBlockPack<ManaLevel>>> entry : manaLevelListMap.entrySet()) {
            List<DefaultBlockPack> defaultBlockPacks = new ArrayList<>(entry.getValue().size());
            for (IMultiBlockPack<ManaLevel> manaLevelMultiBlockPack : entry.getValue()) {
                defaultBlockPacks.add(manaLevelMultiBlockPack.ofDefaultBlockPack(entry.getKey()));
            }
            defaultBlockPackMap.put(entry.getKey(), defaultBlockPacks);
        }
    }

    public List<IMultiBlockPack<ManaLevel>> getT1() {
        return List.of(
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.coil.name,null)
                        .addPos(new BlockPos(-2, 0, -2))
                        .addPos(new BlockPos(-2, 0, 2))
                        .addPos(new BlockPos(2, 0, -2))
                        .addPos(new BlockPos(2, 0, 2)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","low","west","none","up","true"))
                        .addPos(new BlockPos(-2, 0, -1))
                        .addPos(new BlockPos(2, 0, -1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","low","north","none","west","none","up","true"))
                        .addPos(new BlockPos(-2, 0, 1))
                        .addPos(new BlockPos(2, 0, 1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","none","west","low","up","true"))
                        .addPos(new BlockPos(-1, 0, -2))
                        .addPos(new BlockPos(-1, 0, 2)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.slab.name,Map.of("half","true","waterlogged","false","facing","down"))
                        .addPos(new BlockPos(-1, 0, 0))
                        .addPos(new BlockPos(0, 0, -1))
                        .addPos(new BlockPos(0, 0, 1))
                        .addPos(new BlockPos(1, 0, 0)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","none","north","none","west","none","up","true"))
                        .addPos(new BlockPos(1, 0, -2))
                        .addPos(new BlockPos(1, 0, 2)));
    }

    public List<IMultiBlockPack<ManaLevel>> getT2() {
        return  List.of(
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.coil.name,null)
                        .addPos(new BlockPos(-2, 0, -1))
                        .addPos(new BlockPos(-2, 0, 1))
                        .addPos(new BlockPos(-1, 0, -2))
                        .addPos(new BlockPos(-1, 0, 2))
                        .addPos(new BlockPos(1, 0, -2))
                        .addPos(new BlockPos(1, 0, 2))
                        .addPos(new BlockPos(2, 0, -1))
                        .addPos(new BlockPos(2, 0, 1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","low","north","none","west","none","up","true"))
                        .addPos(new BlockPos(-2, 0, -2)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","none","north","low","west","none","up","true"))
                        .addPos(new BlockPos(-2, 0, 2)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.slab.name,Map.of("half","true","waterlogged","false","facing","down"))
                        .addPos(new BlockPos(-1, 0, 0))
                        .addPos(new BlockPos(0, 0, -1))
                        .addPos(new BlockPos(0, 0, 1))
                        .addPos(new BlockPos(1, 0, 0)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","low","north","none","west","low","up","true"))
                        .addPos(new BlockPos(2, 0, -2)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","low","west","low","up","true"))
                        .addPos(new BlockPos(2, 0, 2)));
    }

    public List<IMultiBlockPack<ManaLevel>> getT3() {
        return List.of(
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.coil.name,null)
                        .addPos(new BlockPos(-3, 0, -1))
                        .addPos(new BlockPos(-3, 0, 1))
                        .addPos(new BlockPos(-1, 0, -3))
                        .addPos(new BlockPos(-1, 0, 3))
                        .addPos(new BlockPos(1, 0, -3))
                        .addPos(new BlockPos(1, 0, 3))
                        .addPos(new BlockPos(3, 0, -1))
                        .addPos(new BlockPos(3, 0, 1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","low","north","none","west","none","up","true"))
                        .addPos(new BlockPos(-3, 0, -2))
                        .addPos(new BlockPos(3, 0, -2)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","low","west","none","up","true"))
                        .addPos(new BlockPos(-3, 0, 2))
                        .addPos(new BlockPos(3, 0, 2)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","none","north","none","west","none","up","true"))
                        .addPos(new BlockPos(-2, 0, -3))
                        .addPos(new BlockPos(-2, 0, 3)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.slab.name,Map.of("half","true","waterlogged","false","facing","down"))
                        .addPos(new BlockPos(-1, 0, -1))
                        .addPos(new BlockPos(-1, 0, 0))
                        .addPos(new BlockPos(-1, 0, 1))
                        .addPos(new BlockPos(0, 0, -1))
                        .addPos(new BlockPos(0, 0, 1))
                        .addPos(new BlockPos(1, 0, -1))
                        .addPos(new BlockPos(1, 0, 0))
                        .addPos(new BlockPos(1, 0, 1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","none","west","low","up","true"))
                        .addPos(new BlockPos(2, 0, -3))
                        .addPos(new BlockPos(2, 0, 3)));
    }

    public List<IMultiBlockPack<ManaLevel>> getT4() {
        return List.of(
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.coil.name,null)
                        .addPos(new BlockPos(-3, 0, -2))
                        .addPos(new BlockPos(-3, 0, 2))
                        .addPos(new BlockPos(-2, 0, -3))
                        .addPos(new BlockPos(-2, 0, 3))
                        .addPos(new BlockPos(2, 0, -3))
                        .addPos(new BlockPos(2, 0, 3))
                        .addPos(new BlockPos(3, 0, -2))
                        .addPos(new BlockPos(3, 0, 2)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","low","north","none","west","none","up","true"))
                        .addPos(new BlockPos(-3, 0, -3)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","none","north","low","west","none","up","true"))
                        .addPos(new BlockPos(-3, 0, 3)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.slab.name,Map.of("half","true","waterlogged","false","facing","down"))
                        .addPos(new BlockPos(-2, 0, 0))
                        .addPos(new BlockPos(0, 0, -2))
                        .addPos(new BlockPos(0, 0, 2))
                        .addPos(new BlockPos(2, 0, 0)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","outer_left","facing","south"))
                        .addPos(new BlockPos(-1, 0, -1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","straight","facing","east"))
                        .addPos(new BlockPos(-1, 0, 0)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","outer_left","facing","east"))
                        .addPos(new BlockPos(-1, 0, 1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","straight","facing","south"))
                        .addPos(new BlockPos(0, 0, -1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","straight","facing","north"))
                        .addPos(new BlockPos(0, 0, 1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","outer_left","facing","west"))
                        .addPos(new BlockPos(1, 0, -1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","straight","facing","west"))
                        .addPos(new BlockPos(1, 0, 0)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","outer_right","facing","west"))
                        .addPos(new BlockPos(1, 0, 1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","low","north","none","west","low","up","true"))
                        .addPos(new BlockPos(3, 0, -3)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","low","west","low","up","true"))
                        .addPos(new BlockPos(3, 0, 3)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","none","west","none","up","true"))
                        .addPos(new BlockPos(-3, 1, -2))
                        .addPos(new BlockPos(-3, 1, 2))
                        .addPos(new BlockPos(-2, 1, -3))
                        .addPos(new BlockPos(-2, 1, 3))
                        .addPos(new BlockPos(2, 1, -3))
                        .addPos(new BlockPos(2, 1, 3))
                        .addPos(new BlockPos(3, 1, -2))
                        .addPos(new BlockPos(3, 1, 2)));
    }

    public List<IMultiBlockPack<ManaLevel>> getT5() {
        return List.of(
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.coil.name,null)
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
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.block.name,null)
                        .addPos(new BlockPos(-1, 0, -1))
                        .addPos(new BlockPos(-1, 0, 1))
                        .addPos(new BlockPos(1, 0, -1))
                        .addPos(new BlockPos(1, 0, 1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","low","north","none","west","none","up","true"))
                        .addPos(new BlockPos(-4, 0, -4)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","none","north","low","west","none","up","true"))
                        .addPos(new BlockPos(-4, 0, 4)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.slab.name,Map.of("half","true","waterlogged","false","facing","down"))
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
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","low","north","low","west","none","up","true"))
                        .addPos(new BlockPos(-1, 0, 0)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","low","north","none","west","low","up","true"))
                        .addPos(new BlockPos(0, 0, -1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","none","north","low","west","low","up","true"))
                        .addPos(new BlockPos(0, 0, 1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","low","north","low","west","low","up","true"))
                        .addPos(new BlockPos(1, 0, 0)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","low","north","none","west","low","up","true"))
                        .addPos(new BlockPos(4, 0, -4)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","low","west","low","up","true"))
                        .addPos(new BlockPos(4, 0, 4)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","none","west","none","up","true"))
                        .addPos(new BlockPos(-4, 1, -4))
                        .addPos(new BlockPos(-4, 1, 4))
                        .addPos(new BlockPos(4, 1, -4))
                        .addPos(new BlockPos(4, 1, 4)));
    }

    public List<IMultiBlockPack<ManaLevel>> getT6() {
        return List.of(
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.coil.name,null)
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
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","none","north","none","west","none","up","true"))
                        .addPos(new BlockPos(-5, 0, -2))
                        .addPos(new BlockPos(-5, 0, 2)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.slab.name,Map.of("half","true","waterlogged","false","facing","down"))
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
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","low","north","none","west","none","up","true"))
                        .addPos(new BlockPos(-2, 0, -5))
                        .addPos(new BlockPos(2, 0, -5)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","low","west","none","up","true"))
                        .addPos(new BlockPos(-2, 0, 5))
                        .addPos(new BlockPos(2, 0, 5)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","outer_left","facing","south"))
                        .addPos(new BlockPos(-1, 0, -1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","straight","facing","east"))
                        .addPos(new BlockPos(-1, 0, 0)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","outer_left","facing","east"))
                        .addPos(new BlockPos(-1, 0, 1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","straight","facing","south"))
                        .addPos(new BlockPos(0, 0, -1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","straight","facing","north"))
                        .addPos(new BlockPos(0, 0, 1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","outer_left","facing","west"))
                        .addPos(new BlockPos(1, 0, -1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","straight","facing","west"))
                        .addPos(new BlockPos(1, 0, 0)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","outer_right","facing","west"))
                        .addPos(new BlockPos(1, 0, 1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","none","west","low","up","true"))
                        .addPos(new BlockPos(5, 0, -2))
                        .addPos(new BlockPos(5, 0, 2)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","none","west","none","up","true"))
                        .addPos(new BlockPos(-4, 1, -2))
                        .addPos(new BlockPos(-4, 1, 2))
                        .addPos(new BlockPos(-2, 1, -4))
                        .addPos(new BlockPos(-2, 1, 4))
                        .addPos(new BlockPos(2, 1, -4))
                        .addPos(new BlockPos(2, 1, 4))
                        .addPos(new BlockPos(4, 1, -2))
                        .addPos(new BlockPos(4, 1, 2)));
    }

    public List<IMultiBlockPack<ManaLevel>> getT7() {
        return List.of(
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.coil.name,null)
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
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.block.name,null)
                        .addPos(new BlockPos(-1, 0, 0))
                        .addPos(new BlockPos(0, 0, -1))
                        .addPos(new BlockPos(0, 0, 1))
                        .addPos(new BlockPos(1, 0, 0)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.slab.name,Map.of("half","true","waterlogged","false","facing","down"))
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
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","low","west","low","up","true"))
                        .addPos(new BlockPos(-4, 0, -2))
                        .addPos(new BlockPos(-2, 0, -4)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","low","north","none","west","low","up","true"))
                        .addPos(new BlockPos(-4, 0, 2))
                        .addPos(new BlockPos(-2, 0, 4)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","none","west","none","up","true"))
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
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","straight","facing","east"))
                        .addPos(new BlockPos(-2, 0, 0)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","straight","facing","south"))
                        .addPos(new BlockPos(0, 0, -2)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","straight","facing","north"))
                        .addPos(new BlockPos(0, 0, 2)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","none","north","low","west","none","up","true"))
                        .addPos(new BlockPos(2, 0, -4))
                        .addPos(new BlockPos(4, 0, -2)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.stairs.name,Map.of("half","bottom","waterlogged","false","shape","straight","facing","west"))
                        .addPos(new BlockPos(2, 0, 0)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","low","north","none","west","none","up","true"))
                        .addPos(new BlockPos(2, 0, 4))
                        .addPos(new BlockPos(4, 0, 2)));
    }

    public List<IMultiBlockPack<ManaLevel>> getT8() {
        return List.of(
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.block.name,null)
                        .addPos(new BlockPos(-10, -1, -3))
                        .addPos(new BlockPos(-10, -1, 3))
                        .addPos(new BlockPos(-9, -1, -2))
                        .addPos(new BlockPos(-9, -1, 2))
                        .addPos(new BlockPos(-8, -1, -3))
                        .addPos(new BlockPos(-8, -1, 3))
                        .addPos(new BlockPos(-4, -1, -3))
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
                        .addPos(new BlockPos(0, -1, -3))
                        .addPos(new BlockPos(0, -1, 0))
                        .addPos(new BlockPos(0, -1, 3))
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
                        .addPos(new BlockPos(4, -1, 3))
                        .addPos(new BlockPos(8, -1, -3))
                        .addPos(new BlockPos(8, -1, 3))
                        .addPos(new BlockPos(9, -1, -2))
                        .addPos(new BlockPos(9, -1, 2))
                        .addPos(new BlockPos(10, -1, -3))
                        .addPos(new BlockPos(10, -1, 3)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.coil.name,null)
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
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.slab.name,Map.of("half","false","waterlogged","false","facing","north"))
                        .addPos(new BlockPos(-9, -1, -7))
                        .addPos(new BlockPos(-8, -1, 4))
                        .addPos(new BlockPos(-8, -1, 5))
                        .addPos(new BlockPos(9, -1, -7)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.slab.name,Map.of("half","true","waterlogged","false","facing","down"))
                        .addPos(new BlockPos(-10, -1, -8))
                        .addPos(new BlockPos(-10, -1, -7))
                        .addPos(new BlockPos(-10, -1, -6))
                        .addPos(new BlockPos(-10, -1, -5))
                        .addPos(new BlockPos(-10, -1, -4))
                        .addPos(new BlockPos(-10, -1, -2))
                        .addPos(new BlockPos(-10, -1, 2))
                        .addPos(new BlockPos(-10, -1, 4))
                        .addPos(new BlockPos(-10, -1, 5))
                        .addPos(new BlockPos(-10, -1, 6))
                        .addPos(new BlockPos(-10, -1, 7))
                        .addPos(new BlockPos(-10, -1, 8))
                        .addPos(new BlockPos(-9, -1, -9))
                        .addPos(new BlockPos(-9, -1, 7))
                        .addPos(new BlockPos(-9, -1, 9))
                        .addPos(new BlockPos(-8, -1, -10))
                        .addPos(new BlockPos(-8, -1, -5))
                        .addPos(new BlockPos(-8, -1, -4))
                        .addPos(new BlockPos(-8, -1, -2))
                        .addPos(new BlockPos(-8, -1, 2))
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
                        .addPos(new BlockPos(-4, -1, 0))
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
                        .addPos(new BlockPos(0, -1, -4))
                        .addPos(new BlockPos(0, -1, 4))
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
                        .addPos(new BlockPos(4, -1, 0))
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
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","tall","waterlogged","false","south","tall","north","tall","west","tall","up","true"))
                        .addPos(new BlockPos(-8, -1, -8))
                        .addPos(new BlockPos(-8, -1, 8))
                        .addPos(new BlockPos(8, -1, -8))
                        .addPos(new BlockPos(8, -1, 8)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","low","west","low","up","true"))
                        .addPos(new BlockPos(-8, -1, -6))
                        .addPos(new BlockPos(-6, -1, -8)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","low","north","none","west","low","up","true"))
                        .addPos(new BlockPos(-8, -1, 6))
                        .addPos(new BlockPos(-6, -1, 8)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","low","north","low","west","low","up","true"))
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
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","tall","waterlogged","false","south","low","north","low","west","low","up","true"))
                        .addPos(new BlockPos(-1, -1, 0)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","tall","north","low","west","low","up","true"))
                        .addPos(new BlockPos(0, -1, -1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","low","north","tall","west","low","up","true"))
                        .addPos(new BlockPos(0, -1, 1)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","low","north","low","west","tall","up","true"))
                        .addPos(new BlockPos(1, -1, 0)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","none","north","low","west","none","up","true"))
                        .addPos(new BlockPos(6, -1, -8))
                        .addPos(new BlockPos(8, -1, -6))
                        .addPos(new BlockPos(7, 0, -7)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","low","north","none","west","none","up","true"))
                        .addPos(new BlockPos(6, -1, 8))
                        .addPos(new BlockPos(8, -1, 6)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","tall","waterlogged","false","south","none","north","tall","west","none","up","true"))
                        .addPos(new BlockPos(7, -1, -7)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","low","waterlogged","false","south","none","north","none","west","none","up","true"))
                        .addPos(new BlockPos(-10, 0, -3))
                        .addPos(new BlockPos(-10, 0, 3))
                        .addPos(new BlockPos(-4, 0, -9))
                        .addPos(new BlockPos(-4, 0, 9))
                        .addPos(new BlockPos(-1, 0, 0))
                        .addPos(new BlockPos(2, 0, -9))
                        .addPos(new BlockPos(2, 0, 9))
                        .addPos(new BlockPos(8, 0, -3))
                        .addPos(new BlockPos(8, 0, 3)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","none","west","none","up","true"))
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
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","low","north","none","west","none","up","true"))
                        .addPos(new BlockPos(-9, 0, -4))
                        .addPos(new BlockPos(-9, 0, 2))
                        .addPos(new BlockPos(-3, 0, -10))
                        .addPos(new BlockPos(-3, 0, 8))
                        .addPos(new BlockPos(0, 0, -1))
                        .addPos(new BlockPos(3, 0, -10))
                        .addPos(new BlockPos(3, 0, 8))
                        .addPos(new BlockPos(9, 0, -4))
                        .addPos(new BlockPos(9, 0, 2)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","low","west","none","up","true"))
                        .addPos(new BlockPos(-9, 0, -2))
                        .addPos(new BlockPos(-9, 0, 4))
                        .addPos(new BlockPos(-3, 0, -8))
                        .addPos(new BlockPos(-3, 0, 10))
                        .addPos(new BlockPos(0, 0, 1))
                        .addPos(new BlockPos(3, 0, -8))
                        .addPos(new BlockPos(3, 0, 10))
                        .addPos(new BlockPos(9, 0, -2))
                        .addPos(new BlockPos(9, 0, 4)),
                new IMultiBlockPack.ManaLevelAcceptableBlockPack(OreBlock.wall.name,Map.of("east","none","waterlogged","false","south","none","north","none","west","low","up","true"))
                        .addPos(new BlockPos(-8, 0, -3))
                        .addPos(new BlockPos(-8, 0, 3))
                        .addPos(new BlockPos(-2, 0, -9))
                        .addPos(new BlockPos(-2, 0, 9))
                        .addPos(new BlockPos(1, 0, 0))
                        .addPos(new BlockPos(4, 0, -9))
                        .addPos(new BlockPos(4, 0, 9))
                        .addPos(new BlockPos(10, 0, -3))
                        .addPos(new BlockPos(10, 0, 3)));
    }

    public Map<ManaLevel, List<DefaultBlockPack>> defaultBlockPackMap;

    @ConfigField
    public Map<ManaLevel, List<IMultiBlockPack<ManaLevel>>> manaLevelListMap;

}
