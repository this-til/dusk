package com.til.dusk.common.config.util;

import com.til.dusk.util.gson.AcceptTypeJson;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

/**
 * 筛选限定名
 *
 * @author til
 */
@AcceptTypeJson
public interface IScreen<T> {

    /***
     * 判断接受限定名
     * @param resourceLocation 约定的限定名
     * @return 是否通过
     */
    boolean isAccept(T resourceLocation);

    class ResourceLocationScreen implements IScreen<ResourceLocation> {

        /***
         * 是包含的，如果为真将返回列表是否包含，如果为否返回是否不包含
         */
        public boolean isContain;

        /***
         * 取用限定名表
         */
        public List<ResourceLocation> list;

        public ResourceLocationScreen(boolean isContain, List<ResourceLocation> list) {
            this.isContain = isContain;
            this.list = list;
        }

        public ResourceLocationScreen() {
        }

        @Override
        public boolean isAccept(ResourceLocation resourceLocation) {
            return isContain == list.contains(resourceLocation);
        }
    }

    class BiomeScreen implements IScreen<Biome> {
        /***
         * 是包含的，如果为真将返回列表是否包含，如果为否返回是否不包含
         */
        public boolean isContain;

        public List<Biome> list;

        public BiomeScreen(boolean isContain, List<Biome> list) {
            this.isContain = isContain;
            this.list = list;
        }

        public BiomeScreen() {
        }

        @Override
        public boolean isAccept(Biome biome) {
            return isContain == list.contains(biome);
        }
    }

    class WorldScreen implements IScreen<Level> {
        /***
         * 是包含的，如果为真将返回列表是否包含，如果为否返回是否不包含
         */
        public boolean isContain;

        public List<ResourceLocation> list;

        public WorldScreen(boolean isContain, List<ResourceLocation> list) {
            this.isContain = isContain;
            this.list = list;
        }

        public WorldScreen() {
        }

        @Override
        public boolean isAccept(Level level) {
            return isContain == list.contains(level.dimension().location());
        }
    }
}
