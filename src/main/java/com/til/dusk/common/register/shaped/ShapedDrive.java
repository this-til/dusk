package com.til.dusk.common.register.shaped;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ShapedDrive extends RegisterBasics<ShapedDrive> {

    public static Supplier<IForgeRegistry<ShapedDrive>> SHAPED_DRIVE;
    public final static Map<Integer, ShapedDrive> SHAPED_DRIVE_MAP = new HashMap<>();

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        SHAPED_DRIVE = event.create(new RegistryBuilder<ShapedDrive>().setName(new ResourceLocation(Dusk.MOD_ID, "shaped_drive")));
        for (int i = 0; i < 16; i++) {
            new ShapedDrive(i);
        }
    }

    public ShapedDrive(int id) {
        this(new ResourceLocation(Dusk.MOD_ID, "shaped_drive_" + id), id);
    }

    public ShapedDrive(ResourceLocation name, int id) {
        super(name, SHAPED_DRIVE);
        SHAPED_DRIVE_MAP.put(id, this);
    }
}
