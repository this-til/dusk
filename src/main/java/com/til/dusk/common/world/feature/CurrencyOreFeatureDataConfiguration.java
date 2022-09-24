package com.til.dusk.common.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import net.minecraft.resources.ResourceLocation;

public record CurrencyOreFeatureDataConfiguration(OreBlock.OreGenerateData generateData) {
    public static final Codec<CurrencyOreFeatureDataConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("ore").forGetter(c -> c.generateData.ore.name.toString()),
            Codec.INT.fieldOf("id").forGetter(c -> c.generateData.id)
    ).apply(instance, (name, id) -> {
        Ore ore = Ore.ORE.get().getValue(new ResourceLocation(name));
        assert ore != null;
        assert ore.hasSet(Ore.MINERAL_BLOCK_DATA);
        OreBlock.MineralBlockData mineralBlockData = ore.getSet(Ore.MINERAL_BLOCK_DATA);
        OreBlock.OreGenerateData oreGenerateData = mineralBlockData.getOreGenerateDataByID(id);
        assert oreGenerateData != null;
        return new CurrencyOreFeatureDataConfiguration(oreGenerateData);
    }));


}
