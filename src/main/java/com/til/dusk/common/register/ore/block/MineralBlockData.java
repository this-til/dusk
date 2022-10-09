package com.til.dusk.common.register.ore.block;

import com.til.dusk.common.config.AcceptTypeJson;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import com.til.dusk.common.config.util.IShapedOreConfig;
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
    public List<IShapedOreConfig<Void>> washByproduct;

    /***
     * 离心副产物
     */
    @Nullable
    public List<IShapedOreConfig<Void>> centrifugeByproduct;

    /***
     * 筛选副产物
     */
    @Nullable
    public List<IShapedOreConfig<Void>> screenByproduct;

    @Nullable
    @ConfigField
    public List<IOrePlacedFeatureConfig> orePlacedFeatureConfigList;

    public MineralBlockData addWashByproduct(IShapedOreConfig<Void> config) {
        if (washByproduct == null) {
            washByproduct = new ArrayList<>();
        }
        washByproduct.add(config);
        return this;
    }

    public MineralBlockData addCentrifugeByproduct(IShapedOreConfig<Void> config) {
        if (centrifugeByproduct == null) {
            centrifugeByproduct = new ArrayList<>();
        }
        centrifugeByproduct.add(config);
        return this;
    }

    public MineralBlockData addScreenByproduct(IShapedOreConfig<Void> config) {
        if (screenByproduct == null) {
            screenByproduct = new ArrayList<>();
        }
        screenByproduct.add(config);
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
