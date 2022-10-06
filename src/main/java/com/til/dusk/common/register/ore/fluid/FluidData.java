package com.til.dusk.common.register.ore.fluid;

import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.pack.DataPack;
import org.jetbrains.annotations.Nullable;

@Deprecated
public class FluidData {
    public final Ore ore;

    /***
     * 能不能被uu复制
     */
    public boolean canCopy = false;

    /***
     * 能够裂解
     */
    @Nullable
    public SplittingData splitting;

    public FluidData(Ore ore) {
        this.ore = ore;
    }

    public FluidData setCanCopy(boolean canCopy) {
        this.canCopy = canCopy;
        return this;
    }

    public FluidData setSplitting(SplittingData splittingData) {
        this.splitting = splittingData;
        return this;
    }

    public static class SplittingData {
        @Nullable
        public SplittingDataPack sunlightSplitting;
        @Nullable
        public SplittingDataPack moonlightSplitting;
        @Nullable
        public SplittingDataPack rainSplitting;

        public SplittingData setSunlightSplitting(SplittingDataPack sunlightSplitting) {
            this.sunlightSplitting = sunlightSplitting;
            return this;
        }

        public SplittingData setMoonlightSplitting(SplittingDataPack moonlightSplitting) {
            this.moonlightSplitting = moonlightSplitting;
            return this;
        }

        public SplittingData setRainSplitting(SplittingDataPack rainSplitting) {
            this.rainSplitting = rainSplitting;
            return this;
        }
    }

    public static class SplittingDataPack extends DataPack<SplittingDataPack, Void> {
    }
}
