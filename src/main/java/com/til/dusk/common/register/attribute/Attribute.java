package com.til.dusk.common.register.attribute;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.util.math.INumberPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Attribute extends RegisterBasics<Attribute> {
    public static Supplier<IForgeRegistry<Attribute>> ATTRIBUTE_REGISTERS;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ATTRIBUTE_REGISTERS = RegisterManage.create(Attribute.class, new ResourceLocation(Dusk.MOD_ID, "attribute"), event);
    }

    public Attribute(ResourceLocation name, INumberPack.Range range) {
        super(name, ATTRIBUTE_REGISTERS);
        this.range = range;
    }

    public Attribute(String name, INumberPack.Range range) {
        this(new ResourceLocation(Dusk.MOD_ID, name), range);
    }

    @Override
    public void defaultConfig() {

    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /***
     * 这个属性值存在的范围
     */
    @ConfigField
    public INumberPack.Range range;

}
