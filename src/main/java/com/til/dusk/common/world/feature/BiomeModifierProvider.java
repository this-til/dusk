package com.til.dusk.common.world.feature;

import com.mojang.serialization.Codec;
import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import com.til.dusk.common.register.ore.block.GenerateData;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

import java.util.Map;

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
        BiomeGenerationSettingsBuilder generation = builder.getGenerationSettings();
        for (Map.Entry<ResourceLocation, IOrePlacedFeatureConfig> e : DuskFeature.ORE_PLACED_FEATURE_CONFIG_MAP.entrySet()) {
            Holder<PlacedFeature> holder = e.getValue().getPlacedFeature(biome);
            if (holder != null) {
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, holder);
            }
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return CODEC;
    }
}
