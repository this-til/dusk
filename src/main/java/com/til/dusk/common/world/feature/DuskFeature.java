package com.til.dusk.common.world.feature;

import com.mojang.serialization.Codec;
import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DuskFeature {

    public static final DeferredRegister<Feature<?>> FEATURE = DeferredRegister.create(ForgeRegistries.FEATURES, Dusk.MOD_ID);
    public static final RegistryObject<CurrencyOreFeature> CURRENCY_ORE_FEATURE = FEATURE.register("currency_ore_feature", CurrencyOreFeature::new);

    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_CODEC = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Dusk.MOD_ID);
    public static final RegistryObject<Codec<BiomeModifierProvider>> BIOME_MODIFIER_PROVIDER_CODEC = BIOME_MODIFIER_CODEC.register("all", () -> BiomeModifierProvider.CODEC);

    public static final Map<ResourceLocation, IOrePlacedFeatureConfig> ORE_PLACED_FEATURE_CONFIG_MAP = new HashMap<>();


    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        FEATURE.register(Dusk.instance.modEventBus);
        BIOME_MODIFIER_CODEC.register(Dusk.instance.modEventBus);
    }

    @Deprecated
    public static void onEvent(FMLCommonSetupEvent event) {
        for (Ore ore : Ore.screen(OreConfig.MineralBlockConfig.MINERAL_BLOCK_CONFIG)) {
            ConfigMap configMap = ore.getConfig(OreConfig.MineralBlockConfig.MINERAL_BLOCK_CONFIG);
            if (configMap.containsKey(OreConfig.MineralBlockConfig.PLACED_FEATURE)) {
                for (IOrePlacedFeatureConfig iOrePlacedFeatureConfig : configMap.get(OreConfig.MineralBlockConfig.PLACED_FEATURE)) {
                    if (ORE_PLACED_FEATURE_CONFIG_MAP.containsKey(iOrePlacedFeatureConfig.name())) {
                        Dusk.instance.logger.error("重复的矿物生成[{}]", iOrePlacedFeatureConfig.name());
                    }
                    iOrePlacedFeatureConfig.init();
                    ORE_PLACED_FEATURE_CONFIG_MAP.put(iOrePlacedFeatureConfig.name(), iOrePlacedFeatureConfig);
                }
            }
        }
    }
}
