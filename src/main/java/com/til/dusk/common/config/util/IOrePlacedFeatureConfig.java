package com.til.dusk.common.config.util;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigKey;
import com.til.dusk.common.config.IAcceptConfigMap;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.block.OreBlockMineral;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.world.feature.CurrencyOreFeatureConfiguration;
import com.til.dusk.common.world.feature.DuskFeature;
import com.til.dusk.util.Util;
import com.til.dusk.util.nbt.cell.AllNBTCell;
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
public interface IOrePlacedFeatureConfig extends IAcceptConfigMap {
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

    class OrePlacedFeatureConfig extends RetainConfigMap implements IOrePlacedFeatureConfig {

        public static final ConfigKey<ResourceLocation> NAME = new ConfigKey<>("placed.name", AllNBTCell.RESOURCE_LOCATION, () -> new ResourceLocation(Dusk.MOD_ID, "null"));

        /***
         * 数量，单次生成的数量
         */
        public static final ConfigKey<Integer> AMOUNT = new ConfigKey<>("placed.amount", AllNBTCell.INT, () -> 12);

        /***
         * 一区块中生产的数量
         */
        public static final ConfigKey<Integer> IN_CHUNK_AMOUNT = new ConfigKey<>("placed.in_chunk_amount", AllNBTCell.INT, () -> 4);

        /***
         * 最高高度
         */
        public static final ConfigKey<Integer> MAX_HEIGHT = new ConfigKey<>("placed.max_height", AllNBTCell.INT, () -> 128);

        /***
         * 最低高度
         */
        public static final ConfigKey<Integer> MIN_HEIGHT = new ConfigKey<>("placed.min_height", AllNBTCell.INT, () -> -64);

        /***
         * 筛选世界
         */
        public static final ConfigKey<IScreen> SCREEN_WORLD = new ConfigKey<>("placed.screen_world", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP), () -> null);

        /***
         * 筛选生物群系
         */
        public static final ConfigKey<IScreen> SCREEN_BIOME = new ConfigKey<>("placed.screen_biome", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP), () -> null);

        /***
         * 方块匹配
         */
        public static final ConfigKey<IPair> BLOCK_PAIR = new ConfigKey<>("placed.block_pair", Util.forcedConversion(AllNBTCell.I_ACCEPT_CONFIG_MAP), () -> null);

        Holder<ConfiguredFeature<CurrencyOreFeatureConfiguration, ?>> currencyOreFeatureConfigurationHolder;
        Holder<PlacedFeature> holder;

        public OrePlacedFeatureConfig() {
        }

        public OrePlacedFeatureConfig(ResourceLocation name) {
            configMap.setConfigOfV(NAME, name);
        }

        @Override
        public List<ConfigKey<?>> defaultKey() {
            return List.of(NAME, AMOUNT, IN_CHUNK_AMOUNT, MAX_HEIGHT, MIN_HEIGHT);
        }

        public OrePlacedFeatureConfig useDefaultConfig(Ore ore, int amount, int inChunkAmount) {
            configMap.setConfigOfV(AMOUNT, amount)
                    .setConfigOfV(NAME, ore.name)
                    .setConfigOfV(IN_CHUNK_AMOUNT, inChunkAmount)
                    .setConfig(BLOCK_PAIR, () -> {
                        Map<ResourceLocation, ResourceLocation> map = new HashMap<>();
                        for (Map.Entry<OreBlock, BlockPack> entry : ore.blockEntrySet()) {
                            if (!(entry.getKey() instanceof OreBlockMineral mineral)) {
                                continue;
                            }
                            if (mineral.replaceBasicsBlock == null) {
                                continue;
                            }
                            map.put(ForgeRegistries.BLOCKS.getKey(mineral.replaceBasicsBlock.get()), ForgeRegistries.BLOCKS.getKey(entry.getValue().block()));
                        }
                        return new IPair.ResourceLocationPair(map, null);
                    });
            return this;
        }

        @Override
        public ResourceLocation name() {
            return configMap.get(NAME);
        }

        @Override
        public void init() {
            ResourceLocation name = name();
            currencyOreFeatureConfigurationHolder = FeatureUtils.register(
                    new ResourceLocation(Dusk.MOD_ID, name.getPath() + "_configured").toString(),
                    DuskFeature.CURRENCY_ORE_FEATURE.get(),
                    new CurrencyOreFeatureConfiguration(this, 12));
            PlacementUtils.register(new ResourceLocation(name.getNamespace(), name.getPath() + "_placed").toString(),
                    currencyOreFeatureConfigurationHolder,
                    List.of(CountPlacement.of(configMap.get(IN_CHUNK_AMOUNT)),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(
                                    VerticalAnchor.absolute(configMap.get(MIN_HEIGHT)),
                                    VerticalAnchor.absolute(configMap.get(MAX_HEIGHT))),
                            BiomeFilter.biome()));
        }

        @Nullable
        @Override
        public Holder<PlacedFeature> getPlacedFeature(Holder<Biome> biome) {
            if (!configMap.containsKey(SCREEN_BIOME) || configMap.get(SCREEN_BIOME).isAccept(ForgeRegistries.BIOMES.getKey(biome.get()))) {
                return holder;
            }
            return null;
        }

        @org.jetbrains.annotations.Nullable
        @Override
        public BlockState placed(Level level, BlockState blockState, BlockPos blockPos) {
            if (configMap.containsKey(SCREEN_WORLD) && !configMap.get(SCREEN_WORLD).isAccept(level.dimension().location())) {
                return null;
            }
            if (configMap.containsKey(BLOCK_PAIR)) {
                Block block = ForgeRegistries.BLOCKS.getValue(configMap.get(BLOCK_PAIR).pair(ForgeRegistries.BLOCKS.getKey(blockState.getBlock())));
                if (block == null) {
                    return null;
                }
                return block.defaultBlockState();
            }
            return null;
        }
    }
}
