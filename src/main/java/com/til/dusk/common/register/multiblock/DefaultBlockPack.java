package com.til.dusk.common.register.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public record DefaultBlockPack(List<BlockState> blockState, List<BlockPos> pos) {
}
