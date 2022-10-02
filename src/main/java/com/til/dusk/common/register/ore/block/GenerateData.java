package com.til.dusk.common.register.ore.block;

import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.world.feature.CurrencyOreFeatureConfiguration;
import com.til.dusk.util.Extension;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.jetbrains.annotations.Nullable;

public class GenerateData {

    public Ore ore;
    public int id = 0;

    /***
     * 数量，单次生成的数量
     */
    public int amount = 12;

    /***
     * 一区块中生产的数量
     */
    public int inChunkAmount = 4;

    /***
     * 最高高度
     */
    public int maxHeight = 128;

    /***
     * 最低高度
     */
    public int minHeight = -64;

    /***
     * 筛选世界
     */
    @Nullable
    public Extension.Func_1I<Level, Boolean> canInLevel;

    /***
     * 可以在某生物群系生成
     */
    @Nullable
    public Extension.Func_1I<Biome, Boolean> canInBiome;

    /***
     * 能够放置在某方块群中
     */
    @Nullable
    public Extension.Func_1I<BlockState, Boolean> canPlace;

    /***
     * 获取放置方块
     */
    public Extension.Func_3I<BlockPos, Level, BlockState, BlockState> place = (blockPos, level, blockState) -> Blocks.AIR.defaultBlockState();

    public Holder<ConfiguredFeature<CurrencyOreFeatureConfiguration, ?>> configuredFeatureHolder;

    public Holder<PlacedFeature> placedFeatureHolder;


    public GenerateData setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public GenerateData setInChunkAmount(int inChunkAmount) {
        this.inChunkAmount = inChunkAmount;
        return this;
    }

    public GenerateData setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
        return this;
    }

    public GenerateData setMinHeight(int minHeight) {
        this.minHeight = minHeight;
        return this;
    }

    public GenerateData setCanInLevel(Extension.Func_1I<Level, Boolean> canInLevel) {
        this.canInLevel = canInLevel;
        return this;
    }

    public GenerateData setCanInBiome(Extension.Func_1I<Biome, Boolean> canInBiome) {
        this.canInBiome = canInBiome;
        return this;
    }

    public GenerateData setCanPlace(Extension.Func_1I<BlockState, Boolean> canPlace) {
        this.canPlace = canPlace;
        return this;
    }

    public GenerateData setPlace(Extension.Func_3I<BlockPos, Level, BlockState, BlockState> place) {
        this.place = place;
        return this;
    }

    public GenerateData setOre(Ore ore) {
        this.ore = ore;
        return this;
    }

    public GenerateData setId(int id) {
        this.id = id;
        return this;
    }

    public GenerateData setConfiguredFeatureHolder(Holder<ConfiguredFeature<CurrencyOreFeatureConfiguration, ?>> configuredFeatureHolder) {
        this.configuredFeatureHolder = configuredFeatureHolder;
        return this;
    }

    public GenerateData setPlacedFeatureHolder(Holder<PlacedFeature> placedFeatureHolder) {
        this.placedFeatureHolder = placedFeatureHolder;
        return this;
    }
}
