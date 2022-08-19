package com.til.dusk;

import com.mojang.logging.LogUtils;
import com.til.dusk.register.Ore;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.NewRegistryEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/***
 * Mod主类
 * @author til
 */
@Mod(Dusk.MOD_ID)
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Dusk {
    public static final String MOD_ID = "dusk";
    public static Dusk instance;
    public static CreativeModeTab TAB = new CreativeModeTab(MOD_ID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(Items.BOW);
        }
    };
    public final Logger logger = LogUtils.getLogger();

    public Dusk() {
        instance = this;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void clientSetup(final FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public static void onEvent(ServerStartingEvent event) {
        LootTables lootTables = event.getServer().getLootTables();
        Method method = null;
        try {
            method = BlockLoot.class.getDeclaredMethod("createOreDrop",Block.class,  Item.class);
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            Dusk.instance.logger.error("", e);
            return;
        }
        try {
            Field field = lootTables.getClass().getDeclaredField("tables");
            field.setAccessible(true);
            Map<ResourceLocation, LootTable> oldMap = (Map<ResourceLocation, LootTable>) field.get(lootTables);
            Map<ResourceLocation, LootTable> map = new HashMap<>();
            Method finalMethod = method;
            for (Ore o : Ore.ORE.get()) {
                for (BlockItem b : o.blockMap.values()) {
                    ResourceLocation blockName = ForgeRegistries.BLOCKS.getKey(b.getBlock());
                    if (blockName == null) {
                        continue;
                    }
                    LootTable.Builder builder = (LootTable.Builder) finalMethod.invoke(null, b.getBlock(), b);
                    map.put(new ResourceLocation(blockName.getNamespace(), "blocks/" + blockName.getPath()), builder.build());
                }
            }
            map.putAll(oldMap);
            field.set(lootTables, map);
        } catch (NoSuchFieldException | IllegalAccessException | RuntimeException | InvocationTargetException e) {
            Dusk.instance.logger.error("", e);
        }

        ForgeRegistries.BLOCKS.tags().

    }


}
