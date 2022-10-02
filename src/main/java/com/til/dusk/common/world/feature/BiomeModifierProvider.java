package com.til.dusk.common.world.feature;

import com.mojang.serialization.Codec;
import com.til.dusk.common.register.ore.block.GenerateData;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

/**
 * @author til
 */
public class BiomeModifierProvider implements BiomeModifier {

    public static final BiomeModifierProvider BIOME_MODIFIER_PROVIDER = new BiomeModifierProvider();
    public static final Codec<BiomeModifierProvider> CODEC = Codec.unit(() -> BIOME_MODIFIER_PROVIDER);

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase != Phase.ADD) {
            return;
        }
        for (GenerateData generateData : ModFeature.CAN_FEATURE_ORE) {
            if (generateData.canInBiome != null && !generateData.canInBiome.func(biome.get())) {
                return;
            }
            BiomeGenerationSettingsBuilder generation = builder.getGenerationSettings();
            generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, generateData.placedFeatureHolder);
        }

    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return CODEC;
    }
}
