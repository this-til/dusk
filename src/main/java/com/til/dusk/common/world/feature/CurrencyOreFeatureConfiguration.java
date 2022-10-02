package com.til.dusk.common.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.block.MineralBlockData;
import com.til.dusk.common.register.ore.block.GenerateData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record CurrencyOreFeatureConfiguration(GenerateData generateData,
                                              int size) implements FeatureConfiguration {
    public static final Codec<CurrencyOreFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("ore").forGetter(c -> c.generateData.ore.name.toString()),
            Codec.INT.fieldOf("id").forGetter(c -> c.generateData.id),
            Codec.intRange(0, 64).fieldOf("size").forGetter(c -> c.size)
    ).apply(instance, (name, id, size) -> {
        Ore ore = Ore.ORE.get().getValue(new ResourceLocation(name));
        assert ore != null;
        assert ore.hasSet(Ore.MINERAL_BLOCK_DATA);
        MineralBlockData mineralBlockData = ore.getSet(Ore.MINERAL_BLOCK_DATA);
        GenerateData generateData = mineralBlockData.getOreGenerateDataByID(id);
        assert generateData != null;
        return new CurrencyOreFeatureConfiguration(generateData, size);
    }));

}
