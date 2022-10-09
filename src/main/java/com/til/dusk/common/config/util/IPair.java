package com.til.dusk.common.config.util;

import com.til.dusk.common.config.AcceptTypeJson;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * 通过限定名进行匹配
 *
 * @author til
 */
@AcceptTypeJson
public interface IPair {
    /***
     * 进行匹配
     * @param resourceLocation 进行匹配的名称
     * @return 匹配结果
     */
    @Nullable
    ResourceLocation pair(ResourceLocation resourceLocation);

    class ResourceLocationPair implements IPair {

        /***
         * 匹配表
         */
        public Map<ResourceLocation, ResourceLocation> resourceLocationMap;

        /***
         * 默认
         */
        public ResourceLocation defaultResourceLocation;

        public ResourceLocationPair() {
        }

        public ResourceLocationPair(Map<ResourceLocation, ResourceLocation> resourceLocationMap, ResourceLocation defaultResourceLocation) {
            this.resourceLocationMap = resourceLocationMap;
            this.defaultResourceLocation = defaultResourceLocation;
        }

        @Nullable
        @Override
        public ResourceLocation pair(ResourceLocation resourceLocation) {
            return resourceLocationMap.containsKey(resourceLocation) ? resourceLocationMap.get(resourceLocation) : defaultResourceLocation;
        }

    }

}
