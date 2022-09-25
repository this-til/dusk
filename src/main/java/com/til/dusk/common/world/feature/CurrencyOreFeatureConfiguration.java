package com.til.dusk.common.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.List;

public record CurrencyOreFeatureConfiguration(OreBlock.OreGenerateData generateData,
                                              int size) implements FeatureConfiguration {
    public static final Codec<CurrencyOreFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("ore").forGetter(c -> c.generateData.ore.name.toString()),
            Codec.INT.fieldOf("id").forGetter(c -> c.generateData.id),
            Codec.intRange(0, 64).fieldOf("size").forGetter(c -> c.size)
    ).apply(instance, (name, id, size) -> {
        Ore ore = Ore.ORE.get().getValue(new ResourceLocation(name));
        assert ore != null;
        assert ore.hasSet(Ore.MINERAL_BLOCK_DATA);
        OreBlock.MineralBlockData mineralBlockData = ore.getSet(Ore.MINERAL_BLOCK_DATA);
        OreBlock.OreGenerateData oreGenerateData = mineralBlockData.getOreGenerateDataByID(id);
        assert oreGenerateData != null;
        return new CurrencyOreFeatureConfiguration(oreGenerateData, size);
    }));

}
