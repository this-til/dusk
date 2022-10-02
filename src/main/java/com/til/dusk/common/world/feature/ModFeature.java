package com.til.dusk.common.world.feature;

import com.mojang.serialization.Codec;
import com.til.dusk.Dusk;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.block.MineralBlockData;
import com.til.dusk.common.register.ore.block.GenerateData;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeature {

    public static final DeferredRegister<Feature<?>> FEATURE = DeferredRegister.create(ForgeRegistries.FEATURES, Dusk.MOD_ID);
    public static final List<GenerateData> CAN_FEATURE_ORE = new ArrayList<>();
    public static final RegistryObject<CurrencyOreFeature> CURRENCY_ORE_FEATURE = FEATURE.register("currency_ore_feature", CurrencyOreFeature::new);

    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_CODEC = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Dusk.MOD_ID);
    public static final RegistryObject<Codec<BiomeModifierProvider>> BIOME_MODIFIER_PROVIDER_CODEC = BIOME_MODIFIER_CODEC.register("all", () -> BiomeModifierProvider.CODEC);


    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        FEATURE.register(Dusk.instance.modEventBus);
        BIOME_MODIFIER_CODEC.register(Dusk.instance.modEventBus);
    }

    @SubscribeEvent
    public static void onEvent(FMLCommonSetupEvent event) {
        for (Ore ore : Ore.screen(Ore.MINERAL_BLOCK_DATA)) {
            MineralBlockData mineralBlockData = ore.getSet(Ore.MINERAL_BLOCK_DATA);
            if (mineralBlockData.generateDataList == null) {
                continue;
            }
            CAN_FEATURE_ORE.addAll(mineralBlockData.generateDataList);
        }
        for (GenerateData generateData : CAN_FEATURE_ORE) {
            ResourceLocation oreName = generateData.ore.name;
            Holder<ConfiguredFeature<CurrencyOreFeatureConfiguration, ?>> currencyOreFeatureConfigurationHolder = FeatureUtils.register(
                    new ResourceLocation(Dusk.MOD_ID, oreName.getPath() + "_configured_" + generateData.id).toString(),
                    CURRENCY_ORE_FEATURE.get(),
                    new CurrencyOreFeatureConfiguration(generateData, 12));
            Holder<PlacedFeature> holder = PlacementUtils.register(new ResourceLocation(oreName.getNamespace(), oreName.getPath() + "_placed_" + generateData.id).toString(),
                    currencyOreFeatureConfigurationHolder,
                    List.of(CountPlacement.of(generateData.inChunkAmount),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(
                                    VerticalAnchor.absolute(generateData.minHeight),
                                    VerticalAnchor.absolute(generateData.maxHeight)),
                            BiomeFilter.biome()));
            generateData.setConfiguredFeatureHolder(currencyOreFeatureConfigurationHolder);
            generateData.setPlacedFeatureHolder(holder);
        }
    }
}
