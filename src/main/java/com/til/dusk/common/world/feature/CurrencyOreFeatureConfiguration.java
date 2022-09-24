package com.til.dusk.common.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.List;

public record CurrencyOreFeatureConfiguration(List<CurrencyOreFeatureDataConfiguration> list, int size) implements FeatureConfiguration {
    public static final Codec<CurrencyOreFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.list(CurrencyOreFeatureDataConfiguration.CODEC).fieldOf("cell").forGetter(c -> c.list),
            Codec.intRange(0, 64).fieldOf("size").forGetter(c -> c.size)
    ).apply(instance, CurrencyOreFeatureConfiguration::new));

}
