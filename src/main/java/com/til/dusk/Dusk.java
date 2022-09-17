package com.til.dusk;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.tags.ITagManager;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

/***
 * Mod主类
 * @author til
 */
@Mod(Dusk.MOD_ID)
public class Dusk {
    public static final String MOD_ID = "dusk";
    public static Dusk instance;
    public static final CreativeModeTab TAB = new CreativeModeTab(MOD_ID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(Items.BOW);
        }
    };
    public final Logger logger = LogUtils.getLogger();
    public final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    public ITagManager<Item> ITEM_TAG;
    public ITagManager<Block> BLOCK_TAG;
    public ITagManager<Fluid> FLUID_TAG;
    public ITagManager<Potion> POTION_TAG;

    public TagKey<Item> MOD_ITEM;
    public TagKey<Block> MOD_BLOCK;
    public TagKey<Fluid> MOD_FLUID;
    public TagKey<Potion> MOD_POTION;

    public Dusk() {
        instance = this;
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(EventPriority.HIGHEST, this::newRegistryEvent);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void newRegistryEvent(NewRegistryEvent event) {
        ITEM_TAG = ForgeRegistries.ITEMS.tags();
        BLOCK_TAG = ForgeRegistries.BLOCKS.tags();
        FLUID_TAG = ForgeRegistries.FLUIDS.tags();
        POTION_TAG = ForgeRegistries.POTIONS.tags();
        MOD_ITEM = ITEM_TAG.createTagKey(new ResourceLocation(Dusk.MOD_ID, "all"));
        MOD_BLOCK = BLOCK_TAG.createTagKey(new ResourceLocation(Dusk.MOD_ID, "all"));
        MOD_FLUID = FLUID_TAG.createTagKey(new ResourceLocation(Dusk.MOD_ID, "all"));
        MOD_POTION = POTION_TAG.createTagKey(new ResourceLocation(Dusk.MOD_ID, "all"));
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void clientSetup(final FMLClientSetupEvent event) {
    }


}
