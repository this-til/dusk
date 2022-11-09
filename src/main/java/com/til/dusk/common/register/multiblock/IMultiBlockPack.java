package com.til.dusk.common.register.multiblock;

import com.google.common.collect.ImmutableMap;
import com.til.dusk.Dusk;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.util.BlockStateUtil;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Util;
import com.til.dusk.util.gson.AcceptTypeJson;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.registries.tags.ITag;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author til
 */
@AcceptTypeJson
public interface IMultiBlockPack<D> {
    /***
     * 转为默认方块包
     */
    @Nullable
    DefaultBlockPack ofDefaultBlockPack(D d);

    /***
     * 时候成型
     */
    boolean isCompleted(Level level, BlockPos blockPos, D d);

    class ManaLevelAcceptableBlockPack implements IMultiBlockPack<ManaLevel> {

        public ResourceLocation acceptTag;
        public Map<String, String> stateMap;
        public List<BlockPos> pos = new ArrayList<>();

        public ManaLevelAcceptableBlockPack() {
        }

        public ManaLevelAcceptableBlockPack(ResourceLocation acceptTag, Map<String, String> stateMap) {
            this.acceptTag = acceptTag;
            this.stateMap = stateMap;
        }

        /***
         * 状态严格的
         */
        public boolean stateStrict;

        @Override
        public boolean isCompleted(Level level, BlockPos blockPos, ManaLevel manaLevel) {
            TagKey<Block> tagKey = manaLevel.acceptableTagPack.getTagPack(acceptTag).blockTagKey();
            for (BlockPos pos : pos) {
                BlockState blockState = level.getBlockState(blockPos.offset(pos));
                if (!blockState.is(tagKey)) {
                    return false;
                }
                if (stateStrict) {
                    for (Map.Entry<Property<?>, Comparable<?>> entry : blockState.getValues().entrySet()) {
                        String name = entry.getKey().getName();
                        if (!stateMap.containsKey(name)) {
                            continue;
                        }
                        String v = stateMap.get(name);
                        Optional<?> blockV = entry.getKey().getValue(v);
                        if (blockV.isEmpty()) {
                            continue;
                        }
                        if (!entry.getValue().equals(blockV.get())) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        @Override
        public DefaultBlockPack ofDefaultBlockPack(ManaLevel manaLevel) {
            TagKey<Block> tagKey = manaLevel.acceptableTagPack.getTagPack(acceptTag).blockTagKey();
            ITag<Block> blocks = Dusk.instance.BLOCK_TAG.getTag(tagKey);
            if (blocks.isEmpty()) {
                return null;
            }
            List<BlockState> blockStates = new ArrayList<>();
            for (Block block : blocks) {
                blockStates.add(BlockStateUtil.create(block, stateMap));
            }
            return new DefaultBlockPack(blockStates, new ArrayList<>(pos));
        }

        public ManaLevelAcceptableBlockPack setStateStrict(boolean stateStrict) {
            this.stateStrict = stateStrict;
            return this;
        }

        public ManaLevelAcceptableBlockPack addPos(BlockPos blockPos) {
            pos.add(blockPos);
            return this;
        }
    }

    class BlockStateBlockPack<N> implements IMultiBlockPack<N> {
        public Delayed<BlockState> blockState;
        public List<BlockPos> pos = new ArrayList<>();

        /***
         * 状态严格的
         */
        public boolean stateStrict;

        public BlockStateBlockPack(Supplier<BlockState> blockState) {
            this.blockState = new Delayed.BlockStateDelayed(blockState);
        }

        @Override
        public DefaultBlockPack ofDefaultBlockPack(N o) {
            return new DefaultBlockPack(List.of(blockState.get()), pos);
        }

        @Override
        public boolean isCompleted(Level level, BlockPos blockPos, N o) {
            for (BlockPos po : pos) {
                BlockState worldBlockStack = level.getBlockState(blockPos.offset(po));
                if (stateStrict) {
                    if (!blockState.get().equals(worldBlockStack)) {
                        return false;
                    }
                } else {
                    return blockState.get().is(worldBlockStack.getBlock());
                }
            }
            return true;
        }

        public BlockStateBlockPack<N> addPos(BlockPos blockPos) {
            pos.add(blockPos);
            return this;
        }

        public BlockStateBlockPack<N> setStateStrict(boolean stateStrict) {
            this.stateStrict = stateStrict;
            return this;
        }
    }

}
