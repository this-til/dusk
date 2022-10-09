package com.til.dusk.common.config.util;

import com.til.dusk.common.config.AcceptTypeJson;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

/**
 * 筛选限定名
 *
 * @author til
 */
@AcceptTypeJson
public interface IScreen {

    /***
     * 判断接受限定名
     * @param resourceLocation 约定的限定名
     * @return 是否通过
     */
    boolean isAccept(ResourceLocation resourceLocation);

    class ResourceLocationScreen implements IScreen {

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
    }
}
