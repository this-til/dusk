package com.til.dusk.common.config.util;

import com.til.dusk.common.config.ConfigKey;
import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.IAcceptConfig;
import com.til.dusk.util.nbt.cell.AllNBTCell;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

/**
 * 筛选限定名
 *
 * @author til
 */
public interface IScreen extends IAcceptConfig {

    /***
     * 判断接受限定名
     * @param resourceLocation 约定的限定名
     * @return 是否通过
     */
    boolean isAccept(ResourceLocation resourceLocation);


    class ResourceLocationScreen implements IScreen {

        public static final ConfigKey<Boolean> IS_CONTAIN = new ConfigKey<>("isContain", AllNBTCell.BOOLEAN, () -> false);

        public static final ConfigKey<List<ResourceLocation>> RESOURCE_LOCATION_LIST = new ConfigKey<>("resource_location_list", AllNBTCell.RESOURCE_LOCATION.getListNBTCell(), List::of);

        /***
         * 是包含的，如果为真将返回列表是否包含，如果为否返回是否不包含
         */
        public boolean isContain;

        /***
         * 取用限定名表
         */
        public List<ResourceLocation> resourceLocationList;

        public ResourceLocationScreen(boolean isContain, List<ResourceLocation> resourceLocationList) {
            this.isContain = isContain;
            this.resourceLocationList = resourceLocationList;
        }

        public ResourceLocationScreen() {
        }

        @Override
        public boolean isAccept(ResourceLocation resourceLocation) {
            return isContain == resourceLocationList.contains(resourceLocation);
        }

        @Override
        public void init(ConfigMap configMap) {
            isContain = configMap.get(IS_CONTAIN);
            resourceLocationList = configMap.get(RESOURCE_LOCATION_LIST);
        }

        @Override
        public ConfigMap defaultConfigMap() {
            return new ConfigMap()
                    .setConfigOfV(IS_CONTAIN, isContain)
                    .setConfigOfV(RESOURCE_LOCATION_LIST, resourceLocationList);
        }
    }
}
