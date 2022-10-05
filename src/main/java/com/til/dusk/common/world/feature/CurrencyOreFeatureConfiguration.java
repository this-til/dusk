package com.til.dusk.common.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record CurrencyOreFeatureConfiguration(IOrePlacedFeatureConfig iOrePlacedFeatureConfig,
                                              int size) implements FeatureConfiguration {
    public static final Codec<CurrencyOreFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter(c -> c.iOrePlacedFeatureConfig.name().toString()),
            Codec.intRange(0, 64).fieldOf("size").forGetter(c -> c.size)
    ).apply(instance, (name, size) -> new CurrencyOreFeatureConfiguration(DuskFeature.ORE_PLACED_FEATURE_CONFIG_MAP.get(new ResourceLocation(name)), size)));

}
