package com.til.dusk.common.world;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.List;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DuskTier {

    @SubscribeEvent
    public static void onEvent(FMLCommonSetupEvent event) {
        for (Ore ore : Ore.ORE.get()) {
            if (ore.armsData != null) {
                TierSortingRegistry.registerTier(ore.armsData, ore.armsData.destructionBlockTag.get().location(), List.of(Tiers.NETHERITE), List.of());
            }
        }
    }

}
