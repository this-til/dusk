package com.til.dusk.common.world.feature;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
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
    public static final List<OreBlock.OreGenerateData> CAN_FEATURE_ORE = new ArrayList<>();
    public static final RegistryObject<CurrencyOreFeature> CURRENCY_ORE_FEATURE = FEATURE.register("currency_ore_feature", CurrencyOreFeature::new);


    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        FEATURE.register(Dusk.instance.modEventBus);
    }

    @SubscribeEvent
    public static void onEvent(FMLCommonSetupEvent event) {
        for (Ore ore : Ore.screen(Ore.MINERAL_BLOCK_DATA)) {
            OreBlock.MineralBlockData mineralBlockData = ore.getSet(Ore.MINERAL_BLOCK_DATA);
            if (mineralBlockData.oreGenerateDataList == null) {
                continue;
            }
            CAN_FEATURE_ORE.addAll(mineralBlockData.oreGenerateDataList);
        }
        Holder<ConfiguredFeature<CurrencyOreFeatureConfiguration, ?>> currencyOreFeatureConfigurationHolder = FeatureUtils.register("configured", CURRENCY_ORE_FEATURE.get(),
                new CurrencyOreFeatureConfiguration(new ArrayList<>(), 12));
        for (OreBlock.OreGenerateData oreGenerateData : CAN_FEATURE_ORE) {
            ResourceLocation oreName = oreGenerateData.ore.name;
            PlacementUtils.register(new ResourceLocation(oreName.getNamespace(), oreName.getPath() + "_placed_" + oreGenerateData.id).toString(), currencyOreFeatureConfigurationHolder,
                    List.of(CountPlacement.of(oreGenerateData.inChunkAmount), InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(oreGenerateData.minHeight), VerticalAnchor.absolute(oreGenerateData.maxHeight)), BiomeFilter.biome()));
        }
    }
}
