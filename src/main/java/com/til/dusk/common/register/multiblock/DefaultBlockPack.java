package com.til.dusk.common.register.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

/***
 * 默认方块1包，包含所有接受的方块状态和位置
 * @param blockState
 * @param pos
 */
public record DefaultBlockPack(List<BlockState> blockState, List<BlockPos> pos) {
}
