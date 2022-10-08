package com.til.dusk.common.register.ore.block;

import com.til.dusk.common.config.AcceptTypeJson;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import com.til.dusk.util.pack.DataPack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author til
 */
@AcceptTypeJson
public class MineralBlockData {

    /***
     * 洗矿副产物
     */
    @Nullable
    public DataPack.OreDataPack washByproduct;

    /***
     * 离心副产物
     */
    @Nullable
    public DataPack.OreDataPack centrifugeByproduct;

    /***
     * 筛选副产物
     */
    @Nullable
    public DataPack.OreDataPack screenByproduct;

    @Nullable
    @ConfigField
    public List<IOrePlacedFeatureConfig> orePlacedFeatureConfigList;

    public MineralBlockData setWashByproduct(DataPack.OreDataPack washByproduct) {
        this.washByproduct = washByproduct;
        return this;
    }

    public MineralBlockData setCentrifugeByproduct(DataPack.OreDataPack centrifugeByproduct) {
        this.centrifugeByproduct = centrifugeByproduct;
        return this;
    }

    public MineralBlockData setScreenByproduct(DataPack.OreDataPack screenByproduct) {
        this.screenByproduct = screenByproduct;
        return this;
    }

    public MineralBlockData addOrePlacedFeatureConfig(IOrePlacedFeatureConfig orePlacedFeatureConfig) {
        if (orePlacedFeatureConfigList == null) {
            orePlacedFeatureConfigList = new ArrayList<>();
        }
        orePlacedFeatureConfigList.add(orePlacedFeatureConfig);
        return this;
    }


}
