package com.til.dusk.common.config.util;

import com.til.dusk.Dusk;
import com.til.dusk.util.gson.AcceptTypeJson;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.block.OreBlockMineral;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.world.feature.CurrencyOreFeatureConfiguration;
import com.til.dusk.common.world.feature.DuskFeature;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 放置功能设置
 *
 * @author til
 */
@AcceptTypeJson
public interface IOrePlacedFeatureConfig {
    /***
     * 获取矿物生产配置
     * @return 矿物上场配置
     */
    @Nullable
    Holder<PlacedFeature> getPlacedFeature(Holder<Biome> biome);

    @Nullable
    BlockState placed(Level level, BlockState blockState, BlockPos blockPos);

    /***
     * 初始化生成内容
     */
    void init();

    ResourceLocation name();

    /**
     * @author til
     */
    @AcceptTypeJson
    class GenerateData implements IOrePlacedFeatureConfig {

        public ResourceLocation name;
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
        public IScreen<Level> canInLevel;

        /***
         * 可以在某生物群系生成
         */
        @Nullable
        public IScreen<Biome> canInBiome;

        /***
         * 获取放置方块
         */
        public Delayed<IPair<Block, Block>> place;

        private Holder<PlacedFeature> holder;

        public GenerateData useDefaultConfig(Ore ore, int amount, int inChunkAmount) {
            this.amount = amount;
            this.inChunkAmount = inChunkAmount;
            this.name = ore.name;
            place = new Delayed<>(() -> {
                Map<Block, Block> map = new HashMap<>();
                for (Map.Entry<OreBlock, BlockPack> entry : ore.blockEntrySet()) {
                    if (!(entry.getKey() instanceof OreBlockMineral mineral)) {
                        continue;
                    }
                    if (mineral.replaceBasicsBlock == null) {
                        continue;
                    }
                    map.put(mineral.replaceBasicsBlock.get(), entry.getValue().block());
                }
                return new IPair.BlockPair(map, null);
            }) {
            };
            return this;
        }

        @Override
        public ResourceLocation name() {
            return name;
        }

        @Override
        public void init() {
            ResourceLocation name = name();
            Holder<ConfiguredFeature<CurrencyOreFeatureConfiguration, ?>> currencyOreFeatureConfigurationHolder = FeatureUtils.register(
                    new ResourceLocation(Dusk.MOD_ID, name.getPath() + "_configured").toString(),
                    DuskFeature.CURRENCY_ORE_FEATURE.get(),
                    new CurrencyOreFeatureConfiguration(this, 12));
            holder = PlacementUtils.register(new ResourceLocation(name.getNamespace(), name.getPath() + "_placed").toString(),
                    currencyOreFeatureConfigurationHolder,
                    List.of(CountPlacement.of(inChunkAmount),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(
                                    VerticalAnchor.absolute(minHeight),
                                    VerticalAnchor.absolute(maxHeight)
                            ),
                            BiomeFilter.biome()));
        }

        @Nullable
        @Override
        public Holder<PlacedFeature> getPlacedFeature(Holder<Biome> biome) {
            if (canInBiome == null || canInBiome.isAccept(biome.get())) {
                return holder;
            }
            return null;
        }

        @Nullable
        @Override
        public BlockState placed(Level level, BlockState blockState, BlockPos blockPos) {
            if (canInLevel != null && !canInLevel.isAccept(level)) {
                return null;
            }
            if (place != null) {
                Block block = place.get().pair(blockState.getBlock());
                if (block == null) {
                    return null;
                }
                return block.defaultBlockState();
            }
            return null;
        }


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
    }
}
