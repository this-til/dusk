package com.til.dusk.common.register.attribute.entity.up;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class AttributeUp extends RegisterBasics<AttributeUp> {
    public static Supplier<IForgeRegistry<AttributeUp>> ATTRIBUTE_UP;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ATTRIBUTE_UP = RegisterManage.create(AttributeUp.class, new ResourceLocation(Dusk.MOD_ID, "attribute_up"), event);
    }

    public AttributeUp(ResourceLocation name) {
        super(name, ATTRIBUTE_UP);
    }

    public AttributeUp(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
