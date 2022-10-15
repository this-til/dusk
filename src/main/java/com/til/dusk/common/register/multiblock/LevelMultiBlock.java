package com.til.dusk.common.register.multiblock;

import com.google.common.collect.ImmutableMap;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.util.gson.AcceptTypeJson;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author til
 */
public abstract class LevelMultiBlock extends MultiBlock<ManaLevel> {
    public LevelMultiBlock(ResourceLocation name) {
        super(name);
    }

    public LevelMultiBlock(String name) {
        super(name);
    }

    @Override
    public boolean isCompleted(Level level, BlockPos core, ManaLevel manaLevel) {
        for (BlockPosPack blockPosPack : blockPosPackList) {
            if (!blockPosPack.isCompleted(level, core, manaLevel)) {
                return false;
            }
        }
        return true;
    }

    @ConfigField
    public List<BlockPosPack> blockPosPackList;

    @AcceptTypeJson
    public static class BlockPosPack {

        public ResourceLocation acceptTag;
        public Map<String, String> stateMap;
        public List<BlockPos> pos = new ArrayList<>();

        public BlockPosPack(ResourceLocation acceptTag, Map<String, String> stateMap) {
            this.acceptTag = acceptTag;
            this.stateMap = stateMap;
        }

        /***
         * 状态严格的
         */
        public boolean stateStrict;

        public boolean isCompleted(Level level, BlockPos blockPos, ManaLevel manaLevel) {
            TagKey<Block> tagKey = manaLevel.acceptableTagPack.getTagPack(acceptTag).blockTagKey();
            for (BlockPos pos : pos) {
                BlockState blockState = level.getBlockState(blockPos.offset(pos));
                if (!blockState.is(tagKey)) {
                    return false;
                }
                if (!stateStrict) {
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

        public BlockPosPack setStateStrict(boolean stateStrict) {
            this.stateStrict = stateStrict;
            return this;
        }

        public BlockPosPack addPos(BlockPos blockPos) {
            pos.add(blockPos);
            return this;
        }
    }
}
