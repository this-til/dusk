package com.til.dusk.common.register.attribute;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.multiblock.BlockPosPack;
import com.til.dusk.util.math.INumberPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class AttributeRegisters extends RegisterBasics<AttributeRegisters> {
    public static Supplier<IForgeRegistry<AttributeRegisters>> ATTRIBUTE_REGISTERS;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ATTRIBUTE_REGISTERS = RegisterManage.create(AttributeRegisters.class, new ResourceLocation(Dusk.MOD_ID, "multi_block"), event);
    }

    public AttributeRegisters(ResourceLocation name) {
        super(name, ATTRIBUTE_REGISTERS);
    }

    public AttributeRegisters(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    /***
     * 这个属性值存在的范围
     */
    @ConfigField
    public INumberPack.Range range;

}
