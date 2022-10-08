package com.til.dusk.common.config.util;

import com.til.dusk.common.config.ConfigKey;
import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.IAcceptConfig;
import com.til.dusk.util.nbt.cell.AllNBTCell;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * 通过限定名进行匹配
 *
 * @author til
 */
public interface IPair {
    /***
     * 进行匹配
     * @param resourceLocation 进行匹配的名称
     * @return 匹配结果
     */
    @Nullable
    ResourceLocation pair(ResourceLocation resourceLocation);

    class ResourceLocationPair implements IPair {

        public static final ConfigKey<Map<ResourceLocation, ResourceLocation>> RESOURCE_LOCATION_MAP = new ConfigKey<>("resourceLocation_map", AllNBTCell.RESOURCE_LOCATION_MAP, Map::of);

        public static final ConfigKey<ResourceLocation> DEFAULT_RESOURCE_LOCATION = new ConfigKey<>("default_resourceLocation", AllNBTCell.RESOURCE_LOCATION, () -> null);

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

        @Override
        public void init(ConfigMap configMap) {
            resourceLocationMap = configMap.get(RESOURCE_LOCATION_MAP);
            defaultResourceLocation = configMap.get(DEFAULT_RESOURCE_LOCATION);
        }

        @Override
        public ConfigMap defaultConfigMap() {
            return new ConfigMap()
                    .setConfigOfV(RESOURCE_LOCATION_MAP, resourceLocationMap)
                    .setConfigOfV(DEFAULT_RESOURCE_LOCATION, defaultResourceLocation);
        }
    }

}
