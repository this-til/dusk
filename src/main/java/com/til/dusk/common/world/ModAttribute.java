package com.til.dusk.common.world;

import com.til.dusk.Dusk;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModAttribute {

    public static final DeferredRegister<Attribute> ATTRIBUTE = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Dusk.MOD_ID);

    public static final RegistryObject<Attribute> EMPTY = ATTRIBUTE.register("empty", () -> new RangedAttribute("empty", 0, 0, 0));

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ATTRIBUTE.register(Dusk.instance.modEventBus);
    }


}
