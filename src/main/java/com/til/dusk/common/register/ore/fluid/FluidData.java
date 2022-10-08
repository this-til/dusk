package com.til.dusk.common.register.ore.fluid;

import com.til.dusk.common.config.AcceptTypeJson;
import com.til.dusk.common.config.util.IShapedOreConfig;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author til
 */
@AcceptTypeJson
public class FluidData {

    /***
     * 能不能被uu复制
     */
    public boolean canCopy = false;

    /***
     * 能够裂解
     */
    @Nullable
    public SplittingData splitting;

    public FluidData setCanCopy(boolean canCopy) {
        this.canCopy = canCopy;
        return this;
    }

    public FluidData setSplitting(SplittingData splittingData) {
        this.splitting = splittingData;
        return this;
    }

    @AcceptTypeJson
    public static class SplittingData {
        @Nullable
        public List<IShapedOreConfig<Void>> sunlightSplitting;
        @Nullable
        public List<IShapedOreConfig<Void>> moonlightSplitting;
        @Nullable
        public List<IShapedOreConfig<Void>> rainSplitting;

        public SplittingData addSunlightSplitting(IShapedOreConfig<Void> config) {
            if (sunlightSplitting == null) {
                sunlightSplitting = new ArrayList<>();
            }
            sunlightSplitting.add(config);
            return this;
        }

        public SplittingData addMoonlightSplitting(IShapedOreConfig<Void> config) {
            if (moonlightSplitting == null) {
                moonlightSplitting = new ArrayList<>();
            }
            moonlightSplitting.add(config);
            return this;
        }

        public SplittingData addRainSplitting(IShapedOreConfig<Void> config) {
            if (rainSplitting == null) {
                rainSplitting = new ArrayList<>();
            }
            rainSplitting.add(config);
            return this;
        }
    }

}
