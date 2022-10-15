package com.til.dusk.common.config.util;

import com.til.dusk.util.gson.AcceptTypeJson;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * 通过限定名进行匹配
 *
 * @author til
 */
@AcceptTypeJson
public interface IPair<K, V> {
    /***
     * 进行匹配
     * @param resourceLocation 进行匹配的名称
     * @return 匹配结果
     */
    @Nullable
    K pair(V resourceLocation);

    class ResourceLocationPair implements IPair<ResourceLocation, ResourceLocation> {

        /***
         * 匹配表
         */
        public Map<ResourceLocation, ResourceLocation> map;

        /***
         * 默认
         */
        public ResourceLocation defaultE;

        public ResourceLocationPair() {
        }

        public ResourceLocationPair(Map<ResourceLocation, ResourceLocation> map, ResourceLocation defaultE) {
            this.map = map;
            this.defaultE = defaultE;
        }

        @Nullable
        @Override
        public ResourceLocation pair(ResourceLocation resourceLocation) {
            return map.containsKey(resourceLocation) ? map.get(resourceLocation) : defaultE;
        }

    }

    class BlockPair implements IPair<Block, Block> {

        /***
         * 匹配表
         */
        public Map<Block, Block> map;

        /***
         * 默认
         */
        public Block defaultE;

        public BlockPair() {
        }

        public BlockPair(Map<Block, Block> map, Block defaultE) {
            this.map = map;
            this.defaultE = defaultE;
        }

        @Nullable
        @Override
        public Block pair(Block resourceLocation) {
            return map.containsKey(resourceLocation) ? map.get(resourceLocation) : defaultE;

        }
    }

}
