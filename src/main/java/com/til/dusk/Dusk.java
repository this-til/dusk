package com.til.dusk;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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
    public static CreativeModeTab TAB = new CreativeModeTab(MOD_ID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(Items.BOW);
        }
    };
    public final Logger logger = LogUtils.getLogger();
    public final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    public Dusk() {
        instance = this;
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void clientSetup(final FMLClientSetupEvent event) {
    }




}
