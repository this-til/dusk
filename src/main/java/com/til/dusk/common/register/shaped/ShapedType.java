package com.til.dusk.common.register.shaped;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ShapedType extends RegisterBasics<ShapedType> {

    public static Supplier<IForgeRegistry<ShapedType>> SHAPED_TYPE;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        SHAPED_TYPE = event.create(new RegistryBuilder<ShapedType>().setName(new ResourceLocation(Dusk.MOD_ID, "shaped_type")));
    }

    public ShapedType(ResourceLocation name) {
        super(name, SHAPED_TYPE);
    }
}
